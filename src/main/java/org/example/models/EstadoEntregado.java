package org.example.models;

/**
 * Estado final exitoso del envío cuando ha sido entregado al destinatario.
 *
 * Representa la conclusión satisfactoria del ciclo de vida del envío.
 * El paquete ha llegado a su destino y fue recibido por el destinatario.
 *
 * Transiciones válidas:
 * - Ninguna (estado terminal)
 * - No puede avanzar a otro estado
 * - No puede cancelarse
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EstadoEntregado extends EstadoEnvio {

    /**
     * Intenta avanzar el estado, pero lanza excepción porque es terminal.
     *
     * El estado "Entregado" es el estado final exitoso del ciclo de vida.
     * No hay estados posteriores posibles.
     *
     * @param envio El envío en estado entregado
     * @throws IllegalStateException siempre, porque no puede avanzar
     */
    @Override
    public void avanzarEstado(Envio envio) {
        throw new IllegalStateException(
                "El envío ya fue entregado. No puede avanzar a otro estado.");
    }

    /**
     * Obtiene el mensaje descriptivo del estado actual.
     *
     * @return Mensaje indicando que el paquete fue entregado exitosamente
     */
    @Override
    public String mostrarEstado() {
        return "✅ ENTREGADO: El paquete ha sido entregado exitosamente";
    }

    /**
     * Verifica si este estado puede avanzar al siguiente.
     *
     * @return false, ya que "Entregado" es un estado terminal
     */
    @Override
    public boolean puedeAvanzar() {
        return false;
    }
}