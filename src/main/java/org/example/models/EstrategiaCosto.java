package org.example.models;

/**
 * Interfaz para el patrón Strategy que define el contrato para
 * calcular costos de envío.
 *
 * Esta interfaz permite implementar diferentes algoritmos de cálculo
 * de precios (económico, normal, express) que pueden ser intercambiados
 * en tiempo de ejecución.
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public interface EstrategiaCosto {

    /**
     * Calcula el precio del envío basándose en el peso del paquete.
     *
     * Cada implementación concreta define su propia lógica de cálculo
     * según el tipo de envío (económico, normal o express).
     *
     * @param pesoKilogramos Peso del paquete en kilogramos. Debe ser mayor a 0.
     * @return Costo total del envío en la moneda local
     * @throws IllegalArgumentException si el peso es menor o igual a 0
     */
    double calcularCosto(double pesoKilogramos);

    /**
     * Obtiene el nombre descriptivo de la estrategia de costo.
     *
     * @return Nombre de la estrategia (ej: "Económico", "Normal", "Express")
     */
    String obtenerNombreEstrategia();
}