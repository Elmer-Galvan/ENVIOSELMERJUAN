package org.example.models;

/**
 * Estrategia de cálculo para envíos económicos.
 *
 * Implementación concreta del patrón Strategy que aplica la tarifa
 * más baja disponible. Ideal para envíos sin urgencia donde el tiempo
 * de entrega no es crítico.
 *
 * Características:
 * - Tarifa base más económica
 * - Tiempo de entrega: 7-15 días
 * - Sin recargos por urgencia
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EnvioEconomico implements EstrategiaCosto {

    /** Tarifa por kilogramo para envíos económicos */
    private static final double TARIFA_POR_KILOGRAMO = 5.0;

    /** Nombre descriptivo de esta estrategia */
    private static final String NOMBRE_ESTRATEGIA = "Envío Económico";

    /**
     * Calcula el costo del envío económico basándose únicamente en el peso.
     *
     * Fórmula: peso * tarifa_base
     * No aplica recargos adicionales.
     *
     * @param pesoKilogramos Peso del paquete en kilogramos
     * @return Costo total del envío económico
     * @throws IllegalArgumentException si el peso es menor o igual a 0
     */
    @Override
    public double calcularCosto(double pesoKilogramos) {
        validarPeso(pesoKilogramos);
        return pesoKilogramos * TARIFA_POR_KILOGRAMO;
    }

    /**
     * Obtiene el nombre de esta estrategia.
     *
     * @return "Envío Económico"
     */
    @Override
    public String obtenerNombreEstrategia() {
        return NOMBRE_ESTRATEGIA;
    }

    /**
     * Valida que el peso sea un valor positivo válido.
     *
     * @param peso Peso a validar
     * @throws IllegalArgumentException si el peso es menor o igual a 0
     */
    private void validarPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException(
                    "El peso debe ser mayor a 0. Peso recibido: " + peso);
        }
    }
}