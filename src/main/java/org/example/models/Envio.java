package org.example.models;

/**
 * Clase abstracta que representa la abstracción en el patrón Bridge.
 *
 * Separa la abstracción del envío de su implementación concreta
 * (terrestre o aéreo), permitiendo que ambas puedan variar independientemente.
 *
 * El patrón Bridge evita una explosión de clases al combinar dos jerarquías:
 * - Jerarquía de abstracción: lógica de negocio del envío
 * - Jerarquía de implementación: tipo de transporte (terrestre/aéreo)
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public abstract class Envio {

    /** Destino del envío */
    protected String destino;

    /** Estado actual del envío (patrón State) */
    protected EstadoEnvio estado;

    /** Implementación del tipo de envío (patrón Bridge) */
    protected IVista implementacionVista;

    /**
     * Constructor protegido para las subclases concretas.
     *
     * @param destino Ciudad o dirección de destino
     */
    protected Envio(String destino) {
        this.destino = destino;
        this.estado = new EstadoPreparando();
    }

    /**
     * Método abstracto para enviar el paquete.
     *
     * Las subclases concretas implementan la lógica específica
     * según el tipo de transporte.
     */
    public abstract void enviar();

    /**
     * Obtiene el destino del envío.
     *
     * @return Destino del paquete
     */
    public String obtenerDestino() {
        return destino;
    }

    /**
     * Establece la implementación de la vista (patrón Bridge).
     *
     * @param vista Implementación concreta de la vista
     */
    public void setImplementacionVista(IVista vista) {
        this.implementacionVista = vista;
    }

    /**
     * Obtiene el estado actual del envío.
     *
     * @return Estado actual
     */
    public EstadoEnvio getEstado() {
        return estado;
    }

    /**
     * Establece el estado del envío (usado por el patrón State).
     *
     * @param nuevoEstado Nuevo estado del envío
     */
    public void setEstado(EstadoEnvio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    /**
     * Avanza el envío al siguiente estado en su ciclo de vida.
     *
     * Delega la transición al estado actual (patrón State).
     */
    public void avanzarEstado() {
        estado.avanzarEstado(this);
    }

    /**
     * Actualiza y muestra el estado actual del envío.
     *
     * @return Descripción del estado actual
     */
    public String actualizarEstado() {
        return estado.mostrarEstado();
    }

    /**
     * Muestra el paquete utilizando la vista configurada.
     */
    public void mostrarPaquete() {
        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}