package org.example.models;

/**
 * Estado que representa un envío en camino hacia su destino.
 *
 * El paquete ha sido despachado y se encuentra en tránsito mediante
 * el método de transporte seleccionado (terrestre o aéreo).
 *
 * Transiciones válidas:
 * - Siguiente estado: Entregado
 * - No puede cancelarse en este estado
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EstadoEnTransito extends EstadoEnvio {

    /**
     * Avanza el envío del estado "En Tránsito" al estado "Entregado".
     *
     * Este método se invoca cuando el paquete ha llegado a su destino
     * y está listo para ser entregado al destinatario.
     *
     * @param envio El envío que pasará a estado "Entregado"
     */
    @Override
    public void avanzarEstado(Envio envio) {
        envio.setEstado(new EstadoEntregado());
    }

    /**
     * Obtiene el mensaje descriptivo del estado actual.
     *
     * @return Mensaje indicando que el paquete está en camino
     */
    @Override
    public String mostrarEstado() {
        return "🚚 EN TRÁNSITO: El paquete está en camino a su destino";
    }

    /**
     * Verifica si este estado puede avanzar al siguiente.
     *
     * @return true, ya que "En Tránsito" puede avanzar a "Entregado"
     */
    @Override
    public boolean puedeAvanzar() {
        return true;
    }
}