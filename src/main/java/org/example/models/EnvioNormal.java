package org.example.models;

/**
 * Estrategia de cálculo para envíos normales o estándar.
 *
 * Implementación concreta del patrón Strategy que aplica una tarifa
 * intermedia con tiempo de entrega moderado. Es la opción más equilibrada
 * entre costo y rapidez.
 *
 * Características:
 * - Tarifa intermedia
 * - Tiempo de entrega: 3-7 días
 * - Balance costo-tiempo óptimo
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EnvioNormal implements EstrategiaCosto {

    /** Tarifa por kilogramo para envíos normales */
    private static final double TARIFA_POR_KILOGRAMO = 10.0;

    /** Nombre descriptivo de esta estrategia */
    private static final String NOMBRE_ESTRATEGIA = "Envío Normal";

    /**
     * Calcula el costo del envío normal basándose en el peso.
     *
     * Fórmula: peso * tarifa_intermedia
     * Tarifa equilibrada para entrega estándar.
     *
     * @param pesoKilogramos Peso del paquete en kilogramos
     * @return Costo total del envío normal
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
     * @return "Envío Normal"
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