package org.example.models;

/**
 * Clase abstracta que representa un envío dentro del sistema de paquetería.
 *
 * <p>Integra los patrones de diseño <b>State</b> y <b>Bridge</b>:
 * <ul>
 *   <li><b>State:</b> Permite que el envío cambie de comportamiento según su estado actual (Preparando, Enviado, Entregado, etc.).</li>
 *   <li><b>Bridge:</b> Desacopla la representación visual (IVista) de la lógica de envío.</li>
 * </ul>
 *
 * <p>Proporciona operaciones comunes a todos los tipos de envío (aéreo, terrestre, marítimo, etc.).
 *
 * @author Sistema
 * @version 1.0
 * @since 2025-01-29
 */
public abstract class Envio {

    /** Destino del paquete. */
    protected String destino;

    /** Estado actual del envío (patrón State). */
    protected EstadoEnvio estado;

    /** Implementación de la vista asociada (patrón Bridge). */
    protected IVista implementacionVista;

    /**
     * Crea un nuevo envío con el destino especificado.
     * El estado inicial es "Preparando".
     *
     * @param destino destino del paquete
     */
    protected Envio(String destino) {
        this.destino = destino;
        this.estado = new EstadoPreparando();
    }

    /**
     * Método abstracto que debe implementar cada tipo de envío
     * (aéreo, terrestre, marítimo, etc.).
     */
    public abstract void enviar();

    /**
     * Obtiene el destino del paquete.
     *
     * @return destino del paquete
     */
    public String obtenerDestino() {
        return destino;
    }

    /**
     * Obtiene el estado actual del envío.
     *
     * @return objeto del estado actual
     */
    public EstadoEnvio getEstado() {
        return estado;
    }

    /**
     * Define un nuevo estado para el envío.
     *
     * @param nuevoEstado nuevo estado del envío
     */
    public void setEstado(EstadoEnvio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    /**
     * Avanza el estado del envío al siguiente, si es posible.
     */
    public void avanzarEstado() {
        estado.avanzarEstado(this);
    }

    /**
     * Devuelve una descripción legible del estado actual.
     *
     * @return texto descriptivo del estado
     */
    public String actualizarEstado() {
        return estado.mostrarEstado();
    }

    /**
     * Asigna una implementación de vista (Bridge Pattern).
     *
     * @param vista vista que representará el envío
     */
    public void setImplementacionVista(IVista vista) {
        this.implementacionVista = vista;
    }

    /**
     * Muestra la información del paquete a través de la vista asignada.
     */
    public void mostrarPaquete() {
        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}

