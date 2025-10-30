package org.example.models;

/**
 * Estrategia de cálculo para envíos express o urgentes.
 *
 * Implementación concreta del patrón Strategy que aplica la tarifa
 * premium más alta. Garantiza la entrega más rápida disponible con
 * prioridad en el procesamiento.
 *
 * Características:
 * - Tarifa premium (la más alta)
 * - Tiempo de entrega: 24-48 horas
 * - Prioridad máxima en procesamiento
 * - Seguimiento en tiempo real
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EnvioExpress implements EstrategiaCosto {

    /** Tarifa por kilogramo para envíos express */
    private static final double TARIFA_POR_KILOGRAMO = 20.0;

    /** Nombre descriptivo de esta estrategia */
    private static final String NOMBRE_ESTRATEGIA = "Envío Express";

    /**
     * Calcula el costo del envío express con tarifa premium.
     *
     * Fórmula: peso * tarifa_premium
     * Incluye servicio de entrega urgente y seguimiento prioritario.
     *
     * @param pesoKilogramos Peso del paquete en kilogramos
     * @return Costo total del envío express
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
     * @return "Envío Express"
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