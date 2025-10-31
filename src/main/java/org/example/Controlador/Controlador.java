package org.example.Controlador;


import org.example.Vista.View;
import org.example.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controlador {
    ModeloPaquete paquete;
    View view;
    FabricaEnvio FabricaEnvio;



    public Controlador(ModeloPaquete paquete,View view) {
        this.paquete = paquete;
        this.view = view;
        this.view.btnEnviar.addActionListener(e -> RegistrarPaquete());
        this.view.comboTarifa.addActionListener(e -> ActualizarCosto());
        this.view.txtPeso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ActualizarCosto();
            }
        });

    }

    public void setFabricaEnvio(FabricaEnvio fabricaEnvio) {

        this.FabricaEnvio = fabricaEnvio;
    }
    public void ActualizarCosto() {
        try {
            String textoPeso = view.txtPeso.getText().trim();
            if (textoPeso.isEmpty()) {
                view.txtCosto.setText("0.0");
                return;
            }

            double peso = Double.parseDouble(textoPeso);
            String tarifa = view.comboTarifa.getSelectedItem().toString().toLowerCase();

            EstrategiaCosto estrategia;

            switch (tarifa) {
                case "normal":
                    estrategia = new EnvioNormal();
                    break;
                case "económica":
                    estrategia = new EnvioEconomico();
                    break;
                default:
                    estrategia = new EnvioExpress();
                    break;
            }

            double costo = estrategia.calcularCosto(peso);

            // Asegura que la actualización gráfica ocurra correctamente
            SwingUtilities.invokeLater(() -> {
                view.txtCosto.setText(String.format("%.2f", costo));
            });

        } catch (NumberFormatException e) {
            view.txtCosto.setText("0.0");
        } catch (Exception e) {
            view.txtCosto.setText("Error");
        }
    }

    public void RegistrarPaquete() {

        String nombre = view.txtNombre.getText();
        String destino = view.txtDestino.getText();
        Double peso = Double.valueOf(view.txtPeso.getText());
        String tipoEnvio = view.comboTipoEnvio.getSelectedItem().toString();
        String tarifa = view.comboTarifa.getSelectedItem().toString();
        String modoenvio = view.comboModoEnvio.getSelectedItem().toString();
        int id = (int) (Math.random() * 100);

        if (modoenvio.equals("Aéreo")) {
            FabricaEnvio = new FabricaEnvioAereo();
        } else {
            FabricaEnvio = new FabricaEnvioTerrestre();
        }
        Envio envio = FabricaEnvio.crearEnvio(destino);
        paquete.setDestino(destino);
        paquete.setPeso(peso);
        paquete.setEnvio(envio);
        paquete.setId(id);

        EstrategiaCosto estrategia;

        switch (tarifa.toLowerCase()) {
            case "normal":
                ;
                estrategia = new EnvioNormal();
                break;
            case "Económica":
                ;
                estrategia = new EnvioEconomico();
                break;
            default:
                estrategia = new EnvioExpress();
                break;

        }

        paquete.setEstrategiaCosto(estrategia);
        paquete.calcularCosto();
        DefaultTableModel tablaModelo = (DefaultTableModel) view.tablaEnvios.getModel();
        tablaModelo.addRow(new Object[]{nombre, id, destino, tipoEnvio, tarifa,
                modoenvio, envio.getEstado().toString(),String.format("%.2f", paquete.getCostoFinal())});


        new javax.swing.Timer(5000, e -> {
            envio.getEstado().avanzarEstado();
            int rowCount = tablaModelo.getRowCount();

            // Buscar fila y actualizar envio
            for (int i = 0; i < rowCount; i++) {
                Object idTabla = tablaModelo.getValueAt(i, 1); // Columna ID
                if (idTabla instanceof Integer && (Integer) idTabla == id) {
                    tablaModelo.setValueAt(envio.getEstado().toString(), i, 6); // Columna Estado
                    break;
                }
            }
        }).start();

        view.txtPeso.setText("");
        view.txtPeso.setText("");
        view.txtDestino.setText("");
        view.txtNombre.setText("");


    }
}
