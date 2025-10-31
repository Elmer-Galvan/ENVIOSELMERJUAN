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
 * Controlador principal del sistema de envíos.
 *
 * <p>Esta clase coordina la interacción entre el {@link ModeloPaquete} (Modelo)
 * y la {@link View} (Vista) bajo el patrón <b>MVC</b>.
 * Además, integra múltiples patrones de diseño:
 *
 * <ul>
 *   <li><b>Factory Method:</b> Crea instancias de {@link Envio} (aéreo o terrestre) mediante {@link FabricaEnvio}.</li>
 *   <li><b>Strategy:</b> Calcula el costo dinámicamente según la estrategia seleccionada.</li>
 *   <li><b>State:</b> Controla el ciclo de vida del envío (preparando, en tránsito, entregado).</li>
 *   <li><b>Bridge:</b> Desacopla la vista lógica de la representación visual con {@link IVista} y {@link VistaEnvio}.</li>
 * </ul>
 *
 * <p>El controlador también administra temporizadores para simular el progreso de los envíos
 * y permite la cancelación manual de pedidos desde la interfaz.
 *
 * @author
 * Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class Controlador {

    /** Modelo principal que representa la lógica del paquete. */
    private ModeloPaquete modelo;

    /** Vista principal del sistema (interfaz Swing). */
    private View vista;

    /** Implementación de vista concreta usada en el patrón Bridge. */
    private IVista vistaImplementacion;

    /** Fábrica utilizada para crear envíos concretos (Factory Method). */
    private FabricaEnvio fabrica;

    /** Mapa de timers activos que actualizan el estado de cada envío. */
    private Map<Integer, Timer> timersActivos;

    /**
     * Crea el controlador principal, inicializando la vista y los eventos.
     *
     * @param modelo modelo del paquete
     * @param vista vista principal Swing
     */
    public Controlador(ModeloPaquete modelo, View vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.timersActivos = new HashMap<>();
        inicializarEventos();
    }

    /**
     * Inicializa todos los eventos y listeners de la vista.
     * Configura los botones y campos de texto para interacción del usuario.
     */
    private void inicializarEventos() {
        this.vista.btnEnviar.addActionListener(e -> registrarEnvio());
        this.vista.comboTarifa.addActionListener(e -> actualizarCosto());
        this.vista.txtPeso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarCosto();
            }
        });
        this.vista.btnCancelar.addActionListener(e -> cancelarPedido());
    }

    /**
     * Actualiza dinámicamente el costo estimado del envío
     * según el peso y la tarifa seleccionada.
     */
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

    /**
     * Registra un nuevo envío en el sistema.
     *
     * <p>Combina múltiples patrones:
     * <ul>
     *   <li><b>Factory Method:</b> para crear el tipo de envío (aéreo o terrestre).</li>
     *   <li><b>Bridge:</b> para vincular el envío con su vista lógica.</li>
     *   <li><b>Strategy:</b> para calcular el costo del envío según la tarifa seleccionada.</li>
     *   <li><b>State:</b> para controlar el avance automático del estado mediante un temporizador.</li>
     * </ul>
     */
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

            // Modelo del paquete
            ModeloPaquete paquete = new ModeloPaquete(peso, destino);
            paquete.setId(id);
            paquete.setEnvio(envio);

            // Bridge: conectar el envío con su vista
            IVista vistaEnvio = new VistaEnvio(paquete);
            envio.setImplementacionVista(vistaEnvio);

            System.out.println("\n📦 MOSTRANDO PAQUETE VIA BRIDGE:");
            envio.mostrarPaquete();

            // Strategy: cálculo del costo
            EstrategiaCosto estrategia = obtenerEstrategia(tarifa.toLowerCase());
            paquete.setEstrategiaCosto(estrategia);
            paquete.calcularCosto();

            // Registrar en tabla
            agregarATabla(nombre, id, destino, tipoEnvio, tarifa, modoEnvio, envio, paquete);

            // State: iniciar simulación del avance de estado
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

    /**
     * Verifica que todos los campos obligatorios estén completos.
     *
     * @return true si todos los campos son válidos, false en caso contrario
     */
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

    /**
     * Crea la fábrica de envíos correspondiente al modo seleccionado.
     *
     * @param modoEnvio tipo de transporte (Aéreo o Terrestre)
     */
    private void crearFabricaEnvio(String modoEnvio) {
        if (modoEnvio.equalsIgnoreCase("Aéreo")) {
            fabrica = new FabricaEnvioAereo();
        } else {
            fabrica = new FabricaEnvioTerrestre();
        }
    }

    /**
     * Obtiene la estrategia de costo adecuada según la tarifa seleccionada.
     *
     * @param tarifa tipo de tarifa (económica, normal o express)
     * @return estrategia de cálculo correspondiente
     */
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

    /**
     * Agrega una nueva fila a la tabla de envíos en la vista.
     */
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

    /**
     * Inicia un temporizador que actualiza el estado del envío cada 5 segundos.
     *
     * @param envio objeto de envío asociado
     * @param id identificador único del paquete
     */
    private void iniciarActualizacionEstado(Envio envio, int id) {
        Timer timer = new Timer(5000, null);
        timer.addActionListener(e -> {
            try {
                if (envio.getEstado().puedeAvanzar()) {
                    envio.avanzarEstado();
                    actualizarEstadoEnTabla(id, envio.actualizarEstado());

                    System.out.println("\n🔄 ACTUALIZANDO ESTADO VIA BRIDGE:");
                    envio.mostrarPaquete();
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

    /**
     * Actualiza visualmente el estado del envío en la tabla.
     *
     * @param id identificador del envío
     * @param nuevoEstado texto descriptivo del nuevo estado
     */
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

    /** Genera un identificador único aleatorio para cada paquete. */
    private int generarIdUnico() {
        return (int) (Math.random() * 100000) + 1000;
    }

    /** Limpia los campos de texto de la vista principal. */
    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtDestino.setText("");
        vista.txtPeso.setText("");
        vista.txtCosto.setText("$0.00");
    }

    /** Detiene todos los temporizadores activos y limpia el registro. */
    public void detenerTimers() {
        timersActivos.values().forEach(Timer::stop);
        timersActivos.clear();
    }

    /**
     * Cancela un pedido seleccionado desde la tabla.
     * Detiene su temporizador y actualiza el estado visual.
     */
    public void cancelarPedido() {
        try {
            int filaSeleccionada = vista.tablaEnvios.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista,
                        "Seleccione un pedido de la tabla para cancelar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) vista.tablaEnvios.getValueAt(filaSeleccionada, 1);

            if (timersActivos.containsKey(id)) {
                timersActivos.get(id).stop();
                timersActivos.remove(id);
            }

            DefaultTableModel modelo = (DefaultTableModel) vista.tablaEnvios.getModel();
            modelo.setValueAt("Cancelado", filaSeleccionada, 6);

            JOptionPane.showMessageDialog(vista,
                    "🚫 Pedido con ID " + id + " ha sido cancelado.",
                    "Cancelado", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "❌ Error al cancelar el pedido: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
