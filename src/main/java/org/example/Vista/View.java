package org.example.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Vista principal del sistema de envíos (Patrón MVC - Vista).
 *
 * Solo contiene la definición de componentes visuales.
 * No contiene lógica de negocio ni validaciones.
 */
public class View extends JFrame {

    public JTextField txtNombre;
    public JTextField txtDestino;
    public JTextField txtPeso;
    public JComboBox<String> comboTipoEnvio;
    public JComboBox<String> comboTarifa;
    public JComboBox<String> comboModoEnvio;
    public JButton btnEnviar;
    public JButton btnCancelar; // 🔹 Nuevo botón
    public JTable tablaEnvios;
    public JLabel txtCosto;

    public View() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Envíos");
        setSize(820, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes() {
        JPanel panelDatos = crearPanelDatos();
        add(panelDatos);

        JScrollPane scrollTabla = crearTabla();
        add(scrollTabla);
    }

    private JPanel crearPanelDatos() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Datos del paquete"));
        panel.setLayout(null);
        panel.setBounds(20, 20, 760, 150);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 30, 80, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 30, 160, 25);
        panel.add(txtNombre);

        // Tipo de envío
        JLabel lblTipoEnvio = new JLabel("Tipo de envío:");
        lblTipoEnvio.setBounds(280, 30, 100, 25);
        panel.add(lblTipoEnvio);

        comboTipoEnvio = new JComboBox<>(new String[]{"Normal", "Rápido", "Express"});
        comboTipoEnvio.setBounds(380, 30, 120, 25);
        panel.add(comboTipoEnvio);

        // Tarifa
        JLabel lblTarifa = new JLabel("Tarifa:");
        lblTarifa.setBounds(520, 30, 50, 25);
        panel.add(lblTarifa);

        comboTarifa = new JComboBox<>(new String[]{"Económica", "Normal", "Expres"});
        comboTarifa.setBounds(570, 30, 120, 25);
        panel.add(comboTarifa);

        // Destino
        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 70, 80, 25);
        panel.add(lblDestino);

        txtDestino = new JTextField();
        txtDestino.setBounds(100, 70, 160, 25);
        panel.add(txtDestino);

        // Peso
        JLabel lblPeso = new JLabel("Peso (kg):");
        lblPeso.setBounds(280, 70, 80, 25);
        panel.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(360, 70, 80, 25);
        panel.add(txtPeso);

        // Modo de envío
        JLabel lblModo = new JLabel("Modo de envío:");
        lblModo.setBounds(460, 70, 100, 25);
        panel.add(lblModo);

        comboModoEnvio = new JComboBox<>(new String[]{"Aéreo", "Terrestre"});
        comboModoEnvio.setBounds(560, 70, 130, 25);
        panel.add(comboModoEnvio);

        // Costo
        JLabel lblCosto = new JLabel("Costo:");
        lblCosto.setBounds(460, 105, 60, 25);
        panel.add(lblCosto);

        txtCosto = new JLabel("$0.00");
        txtCosto.setBounds(520, 105, 80, 25);
        panel.add(txtCosto);

        // Botón enviar
        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(320, 105, 120, 30);
        panel.add(btnEnviar);

        // 🔹 Botón cancelar pedido
        btnCancelar = new JButton("Cancelar Pedido");
        btnCancelar.setBounds(600, 105, 150, 30);
        panel.add(btnCancelar);

        return panel;
    }

    private JScrollPane crearTabla() {
        String[] columnas = {"Nombre", "ID", "Destino", "Tipo", "Tarifa", "Modo", "Estado", "Costo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEnvios = new JTable(modelo);
        tablaEnvios.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tablaEnvios);
        scroll.setBounds(20, 190, 760, 280);

        return scroll;
    }
}
