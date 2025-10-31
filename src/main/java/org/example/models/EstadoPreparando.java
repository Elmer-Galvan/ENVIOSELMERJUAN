package org.example.models;

/**
 * Estado inicial del envío cuando está siendo preparado para el despacho.
 *
 * Representa la primera etapa del ciclo de vida del envío, donde el paquete
 * está siendo empacado, etiquetado y preparado para su transporte.
 *
 * Transiciones válidas:
 * - Siguiente estado: En Tránsito
 * - Puede cancelarse en este estado
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class EstadoPreparando extends EstadoEnvio {

    /**
     * Avanza el envío del estado "Preparando" al estado "En Tránsito".
     *
     * Este método se invoca cuando el paquete ha sido completamente preparado
     * y está listo para ser despachado al transporte.
     *
     * @param envio El envío que pasará a estado "En Tránsito"
     */
    @Override
    public void avanzarEstado(Envio envio) {
        envio.setEstado(new EstadoEnTransito());
    }

    /**
     * Obtiene el mensaje descriptivo del estado actual.
     *
     * @return Mensaje indicando que el paquete está siendo preparado
     */
    @Override
    public String mostrarEstado() {
        return "📦 PREPARANDO";
    }

    /**
     * Verifica si este estado puede avanzar al siguiente.
     *
     * @return true, ya que "Preparando" puede avanzar a "En Tránsito"
     */
    @Override
    public boolean puedeAvanzar() {
        return true;
    }
}