package org.example.Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {

public JTextField txtNombre;
public JTextField txtDestino;
public JTextField txtPeso;
public JComboBox<String> comboTipoEnvio;
public JComboBox<String> comboTarifa;
public JComboBox<String> comboModoEnvio;
public JButton btnEnviar;
public JTable tablaEnvios;
private DefaultTableModel modeloTabla;
public JLabel lblCosto;
public JLabel txtCosto;

public View() {
    this.setTitle("Envíos");
    this.setSize(820, 520);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(null);

    // Panel principal de datos
    JPanel panelDatos = new JPanel();
    panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del paquete"));
    panelDatos.setLayout(null);
    panelDatos.setBounds(20, 20, 760, 150);
    this.add(panelDatos);

    // Etiquetas y campos de texto

    lblCosto = new JLabel("Costo:");
    lblCosto.setBounds(460, 105, 60, 25);
    panelDatos.add(lblCosto);

    txtCosto = new JLabel("0.0");
    txtCosto.setBounds(520, 105, 80, 25);
    panelDatos.add(txtCosto);

    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(20, 30, 80, 25);
    panelDatos.add(lblNombre);

    txtNombre = new JTextField();
    txtNombre.setBounds(100, 30, 160, 25);
    panelDatos.add(txtNombre);

    JLabel lblTipoEnvio = new JLabel("Tipo de envío:");
    lblTipoEnvio.setBounds(280, 30, 100, 25);
    panelDatos.add(lblTipoEnvio);

    comboTipoEnvio = new JComboBox<>(new String[]{"Normal", "Rápido", "Express"});
    comboTipoEnvio.setBounds(380, 30, 120, 25);
    panelDatos.add(comboTipoEnvio);

    JLabel lblTarifa = new JLabel("Tarifa:");
    lblTarifa.setBounds(520, 30, 50, 25);
    panelDatos.add(lblTarifa);

    comboTarifa = new JComboBox<>(new String[]{"Económica", "Normal", "Expres"});
    comboTarifa.setBounds(570, 30, 120, 25);
    panelDatos.add(comboTarifa);

    JLabel lblDestino = new JLabel("Destino:");
    lblDestino.setBounds(20, 70, 80, 25);
    panelDatos.add(lblDestino);

    txtDestino = new JTextField();
    txtDestino.setBounds(100, 70, 160, 25);
    panelDatos.add(txtDestino);

    JLabel lblPeso = new JLabel("Peso (kg):");
    lblPeso.setBounds(280, 70, 80, 25);
    panelDatos.add(lblPeso);

    txtPeso = new JTextField();
    txtPeso.setBounds(360, 70, 80, 25);
    panelDatos.add(txtPeso);

    JLabel lblModo = new JLabel("Modo de envío:");
    lblModo.setBounds(460, 70, 100, 25);
    panelDatos.add(lblModo);

    comboModoEnvio = new JComboBox<>(new String[]{"Aéreo", "Terrestre"});
    comboModoEnvio.setBounds(560, 70, 130, 25);
    panelDatos.add(comboModoEnvio);

    btnEnviar = new JButton("Enviar");
    btnEnviar.setBounds(320, 105, 120, 30);
    panelDatos.add(btnEnviar);

    // Tabla de envíos
    String[] columnas = {"Nombre", "ID", "Destino", "Tipo de envío", "Tarifa", "Modo de envío", "Estado", "costo"};
    modeloTabla = new DefaultTableModel(columnas, 0) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    tablaEnvios = new JTable(modeloTabla);
    tablaEnvios.getTableHeader().setReorderingAllowed(false);

    JScrollPane scroll = new JScrollPane(tablaEnvios);
    scroll.setBounds(20, 190, 760, 280);
    this.add(scroll);
}
}
