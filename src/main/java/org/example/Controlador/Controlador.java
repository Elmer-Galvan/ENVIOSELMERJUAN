package org.example.Controlador;

import org.example.Vista.View;
import org.example.Vista.VistaEnvio;
import org.example.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador con implementación COMPLETA de todos los patrones del UML.
 * Incluye el uso correcto del patrón Bridge.
 */
public class Controlador {
    private ModeloPaquete modelo;
    private View vista;
    private IVista vistaImplementacion;
    private FabricaEnvio fabrica;
    private Map<Integer, Timer> timersActivos;

    public Controlador(ModeloPaquete modelo, View vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.timersActivos = new HashMap<>();
        inicializarEventos();
    }

    private void inicializarEventos() {
        this.vista.btnEnviar.addActionListener(e -> registrarEnvio());
        this.vista.comboTarifa.addActionListener(e -> actualizarCosto());
        this.vista.txtPeso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarCosto();
            }
        });
    }

    public void actualizarCosto() {
        try {
            String textoPeso = vista.txtPeso.getText().trim();
            if (textoPeso.isEmpty()) {
                vista.txtCosto.setText("$0.00");
                return;
            }

            double peso = Double.parseDouble(textoPeso);
            String tarifa = vista.comboTarifa.getSelectedItem().toString().toLowerCase();
            EstrategiaCosto estrategia = obtenerEstrategia(tarifa);

            double costo = estrategia.calcularCosto(peso);
            vista.txtCosto.setText(String.format("$%.2f", costo));

        } catch (Exception e) {
            vista.txtCosto.setText("$0.00");
        }
    }

    public void registrarEnvio() {
        try {
            if (!validarCampos()) return;

            String nombre = vista.txtNombre.getText().trim();
            String destino = vista.txtDestino.getText().trim();
            double peso = Double.parseDouble(vista.txtPeso.getText().trim());
            String tipoEnvio = vista.comboTipoEnvio.getSelectedItem().toString();
            String tarifa = vista.comboTarifa.getSelectedItem().toString();
            String modoEnvio = vista.comboModoEnvio.getSelectedItem().toString();
            int id = generarIdUnico();

            // Factory Method
            crearFabricaEnvio(modoEnvio);
            Envio envio = fabrica.crearEnvio(destino);

            // Crear paquete
            ModeloPaquete paquete = new ModeloPaquete(peso, destino);
            paquete.setId(id);
            paquete.setEnvio(envio);

            // **AQUÍ APLICAMOS EL PATRÓN BRIDGE**
            // Crear la vista de implementación
            IVista vistaEnvio = new VistaEnvio(paquete);

            // Conectar el envío con su vista (Bridge)
            envio.setImplementacionVista(vistaEnvio);

            // Ahora el envío puede usar mostrarPaquete()
            System.out.println("\n📦 MOSTRANDO PAQUETE VIA BRIDGE:");
            envio.mostrarPaquete(); // Usa el patrón Bridge

            // Strategy
            EstrategiaCosto estrategia = obtenerEstrategia(tarifa.toLowerCase());
            paquete.setEstrategiaCosto(estrategia);
            paquete.calcularCosto();

            // Agregar a tabla
            agregarATabla(nombre, id, destino, tipoEnvio, tarifa,
                    modoEnvio, envio, paquete);

            // State - Timer
            iniciarActualizacionEstado(envio, id);

            limpiarCampos();

            JOptionPane.showMessageDialog(vista,
                    "✅ Paquete registrado\nID: " + id,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "❌ Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (vista.txtNombre.getText().trim().isEmpty() ||
                vista.txtDestino.getText().trim().isEmpty() ||
                vista.txtPeso.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Complete todos los campos",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void crearFabricaEnvio(String modoEnvio) {
        if (modoEnvio.equalsIgnoreCase("Aéreo")) {
            fabrica = new FabricaEnvioAereo();
        } else {
            fabrica = new FabricaEnvioTerrestre();
        }
    }

    private EstrategiaCosto obtenerEstrategia(String tarifa) {
        switch (tarifa.toLowerCase()) {
            case "económica":
                return new EnvioEconomico();
            case "normal":
                return new EnvioNormal();
            default:
                return new EnvioExpress();
        }
    }

    private void agregarATabla(String nombre, int id, String destino,
                               String tipoEnvio, String tarifa, String modoEnvio,
                               Envio envio, ModeloPaquete paquete) {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaEnvios.getModel();
        modelo.addRow(new Object[]{
                nombre, id, destino, tipoEnvio, tarifa, modoEnvio,
                envio.actualizarEstado(),
                String.format("$%.2f", paquete.getCostoCalculado())
        });
    }

    private void iniciarActualizacionEstado(Envio envio, int id) {
        Timer timer = new Timer(5000, null);
        timer.addActionListener(e -> {
            try {
                if (envio.getEstado().puedeAvanzar()) {
                    envio.avanzarEstado();
                    actualizarEstadoEnTabla(id, envio.actualizarEstado());

                    // **USAR EL PATRÓN BRIDGE AQUÍ TAMBIÉN**
                    System.out.println("\n🔄 ACTUALIZANDO ESTADO VIA BRIDGE:");
                    envio.mostrarPaquete(); // Muestra via Bridge
                } else {
                    timer.stop();
                    timersActivos.remove(id);
                }
            } catch (Exception ex) {
                timer.stop();
                timersActivos.remove(id);
            }
        });
        timer.start();
        timersActivos.put(id, timer);
    }

    private void actualizarEstadoEnTabla(int id, String nuevoEstado) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel modelo = (DefaultTableModel) vista.tablaEnvios.getModel();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (((Integer) modelo.getValueAt(i, 1)).equals(id)) {
                    modelo.setValueAt(nuevoEstado, i, 6);
                    break;
                }
            }
        });
    }

    private int generarIdUnico() {
        return (int) (Math.random() * 100000) + 1000;
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtDestino.setText("");
        vista.txtPeso.setText("");
        vista.txtCosto.setText("$0.00");
    }

    public void detenerTimers() {
        timersActivos.values().forEach(Timer::stop);
        timersActivos.clear();
    }
}