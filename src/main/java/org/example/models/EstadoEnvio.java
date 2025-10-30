package org.example.models;

/**
 * Clase abstracta para el patrón State que representa el estado de un envío.
 *
 * Define el contrato que deben implementar todos los estados concretos
 * del ciclo de vida de un envío. Permite cambiar el comportamiento del
 * envío según su estado actual sin modificar su clase.
 *
 * Estados posibles:
 * - Preparando: Paquete siendo preparado para despacho
 * - En Tránsito: Paquete en camino al destino
 * - Entregado: Paquete entregado exitosamente
 * - Cancelado: Envío cancelado
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public abstract class EstadoEnvio {

    /**
     * Avanza el envío al siguiente estado en su ciclo de vida.
     *
     * Cada estado concreto define cuál es el siguiente estado válido.
     * Algunos estados pueden ser terminales (no tienen siguiente estado).
     *
     * @param envio El envío que cambiará de estado
     * @throws IllegalStateException si el estado actual no permite avanzar
     */
    public abstract void avanzarEstado(Envio envio);

    /**
     * Obtiene una representación textual del estado actual.
     *
     * Proporciona un mensaje amigable para mostrar al usuario sobre
     * el estado actual del envío.
     *
     * @return Descripción del estado actual
     */
    public abstract String mostrarEstado();

    /**
     * Verifica si el estado actual permite avanzar al siguiente.
     *
     * @return true si puede avanzar, false si es un estado terminal
     */
    public abstract boolean puedeAvanzar();
}
