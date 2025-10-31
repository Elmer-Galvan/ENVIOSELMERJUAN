package org.example;

import org.example.Controlador.Controlador;
import org.example.Vista.View;
import org.example.models.ModeloPaquete;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase principal del Sistema de Envíos MVC.
 */
public class Main {

    public static void main(String[] args) {
        configurarLookAndFeel();
        SwingUtilities.invokeLater(Main::inicializarSistema);
    }

    private static void inicializarSistema() {
        imprimirBanner();

        ModeloPaquete modeloInicial = new ModeloPaquete(1.0, "Inicial");
        View vista = new View();
        Controlador controlador = new Controlador(modeloInicial, vista);

        configurarCierre(vista, controlador);

        vista.setVisible(true);
        System.out.println("✅ Sistema listo\n");
        mostrarInstrucciones();
    }

    private static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("⚠️ No se pudo configurar Look and Feel");
        }
    }

    private static void configurarCierre(View vista, Controlador controlador) {
        vista.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("\n🛑 Cerrando sistema...");
                controlador.detenerTimers();
                System.out.println("✅ Recursos liberados");
                System.exit(0);
            }
        });
    }

    private static void imprimirBanner() {
        System.out.println("═══════════════════════════════════════════");
        System.out.println("🚀 SISTEMA DE ENVÍOS MVC");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("📦 Patrones: MVC | Factory | Strategy | State | Bridge");
        System.out.println("═══════════════════════════════════════════\n");
    }

    private static void mostrarInstrucciones() {
        System.out.println("📋 INSTRUCCIONES:");
        System.out.println("─────────────────────────────────────────");
        System.out.println("1. Complete todos los campos del formulario");
        System.out.println("2. Click en 'Enviar' para registrar el paquete");
        System.out.println("3. El estado se actualizará automáticamente");
        System.out.println("   📦 PREPARANDO → 🚚 EN TRÁNSITO → ✅ ENTREGADO");
        System.out.println("─────────────────────────────────────────\n");
    }
}