package org.example.models;

/**
 * Implementación concreta del envío por transporte terrestre.
 *
 * Parte del patrón Bridge: implementación específica para envíos
 * que utilizan transporte por carretera (camiones, furgonetas).
 *
 * Características:
 * - Transporte por carretera
 * - Costos moderados
 * - Ideal para distancias cortas/medias
 * - Mayor flexibilidad en rutas
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EnvioTerrestre extends Envio {

    /** Tipo de vehículo utilizado para el transporte */
    private static final String TIPO_TRANSPORTE = "Terrestre (Camión)";

    /**
     * Constructor que crea un envío terrestre.
     *
     * @param destino Ciudad o dirección de destino del envío
     */
    public EnvioTerrestre(String destino) {
        super(destino);
    }

    /**
     * Envía el paquete por transporte terrestre.
     *
     * Implementa la lógica específica de envío por carretera,
     * mostrando el tipo de transporte y destino.
     */
    @Override
    public void enviar() {
        System.out.println("🚚 Enviando por " + TIPO_TRANSPORTE);
        System.out.println("📍 Destino: " + destino);
        System.out.println("⏱️ Tiempo estimado: 3-7 días");

        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }

    /**
     * Obtiene el tipo de transporte utilizado.
     *
     * @return "Terrestre (Camión)"
     */
    public String obtenerTipoTransporte() {
        return TIPO_TRANSPORTE;
    }
}