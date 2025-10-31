package org.example.models;

/**
 * Clase abstracta que representa un envío.
 * Integra el patrón State y Bridge.
 */
public abstract class Envio {

    protected String destino;
    protected EstadoEnvio estado;
    protected IVista implementacionVista;

    protected Envio(String destino) {
        this.destino = destino;
        this.estado = new EstadoPreparando();
    }

    public abstract void enviar();

    public String obtenerDestino() {
        return destino;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void avanzarEstado() {
        estado.avanzarEstado(this);
    }

    public String actualizarEstado() {
        return estado.mostrarEstado();
    }

    public void setImplementacionVista(IVista vista) {
        this.implementacionVista = vista;
    }

    public void mostrarPaquete() {
        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}