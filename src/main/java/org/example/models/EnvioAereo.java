package org.example.models;

/**
 * Implementación concreta del envío por transporte aéreo.
 *
 * Parte del patrón Bridge: implementación específica para envíos
 * que utilizan transporte por avión.
 *
 * Características:
 * - Transporte por avión
 * - Costos más elevados
 * - Ideal para distancias largas
 * - Mayor rapidez en la entrega
 * - Cobertura internacional
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EnvioAereo extends Envio {

    /** Tipo de transporte utilizado */
    private static final String TIPO_TRANSPORTE = "Aéreo (Avión)";

    /**
     * Constructor que crea un envío aéreo.
     *
     * @param destino Ciudad o dirección de destino del envío
     */
    public EnvioAereo(String destino) {
        super(destino);
    }

    /**
     * Envía el paquete por transporte aéreo.
     *
     * Implementa la lógica específica de envío por avión,
     * mostrando el tipo de transporte y destino.
     */
    @Override
    public void enviar() {
        System.out.println("✈️ Enviando por " + TIPO_TRANSPORTE);
        System.out.println("📍 Destino: " + destino);
        System.out.println("⏱️ Tiempo estimado: 1-3 días");

        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }

    /**
     * Obtiene el tipo de transporte utilizado.
     *
     * @return "Aéreo (Avión)"
     */
    public String obtenerTipoTransporte() {
        return TIPO_TRANSPORTE;
    }
}