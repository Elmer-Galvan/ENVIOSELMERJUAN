package org.example.models;

/**
 * Implementación concreta del envío por transporte aéreo.
 */
public class EnvioAereo extends Envio {

    public EnvioAereo(String destino) {
        super(destino);
    }

    @Override
    public void enviar() {
        System.out.println("✈️ Enviando por Aéreo (Avión)");
        System.out.println("📍 Destino: " + destino);
        System.out.println("⏱️ Tiempo estimado: 1-3 días");

        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}