package org.example.models;

/**
 * Implementación concreta del envío por transporte terrestre.
 */
public class EnvioTerrestre extends Envio {

    public EnvioTerrestre(String destino) {
        super(destino);
    }

    @Override
    public void enviar() {
        System.out.println("🚚 Enviando por Terrestre (Camión)");
        System.out.println("📍 Destino: " + destino);
        System.out.println("⏱️ Tiempo estimado: 3-7 días");

        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}