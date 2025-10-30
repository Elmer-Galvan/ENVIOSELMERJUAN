package org.example.models;

/**
 * Estado final del envío cuando ha sido cancelado por el usuario o el sistema.
 *
 * Representa la conclusión del ciclo de vida del envío por cancelación.
 * El paquete no será procesado ni entregado.
 *
 * Transiciones válidas:
 * - Ninguna (estado terminal)
 * - No puede avanzar a otro estado
 * - Es irreversible
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EstadoCancelado extends EstadoEnvio {

    /**
     * Intenta avanzar el estado, pero lanza excepción porque es terminal.
     *
     * El estado "Cancelado" es un estado final. Una vez cancelado,
     * el envío no puede volver a procesarse.
     *
     * @param envio El envío en estado cancelado
     * @throws IllegalStateException siempre, porque no puede avanzar
     */
    @Override
    public void avanzarEstado(Envio envio) {
        throw new IllegalStateException(
                "El envío ha sido cancelado. No puede avanzar a otro estado.");
    }

    /**
     * Obtiene el mensaje descriptivo del estado actual.
     *
     * @return Mensaje indicando que el envío fue cancelado
     */
    @Override
    public String mostrarEstado() {
        return "❌ CANCELADO: El envío ha sido cancelado";
    }

    /**
     * Verifica si este estado puede avanzar al siguiente.
     *
     * @return false, ya que "Cancelado" es un estado terminal
     */
    @Override
    public boolean puedeAvanzar() {
        return false;
    }
}