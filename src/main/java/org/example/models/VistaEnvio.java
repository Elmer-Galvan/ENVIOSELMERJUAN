package org.example.Vista;

import org.example.models.IVista;
import org.example.models.ModeloPaquete;

/**
 * Implementación concreta de IVista para el sistema de envíos.
 *
 * Parte del patrón Bridge: proporciona la implementación específica
 * para mostrar información de paquetes en consola o interfaz gráfica.
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class VistaEnvio implements IVista {

    private ModeloPaquete modelo;

    /**
     * Constructor que asocia la vista con un modelo.
     *
     * @param modelo Modelo del paquete a mostrar
     */
    public VistaEnvio(ModeloPaquete modelo) {
        this.modelo = modelo;
    }

    /**
     * Constructor sin parámetros para uso general.
     */
    public VistaEnvio() {
        this.modelo = null;
    }

    /**
     * Muestra el paquete actual en consola.
     *
     * Si no hay modelo asociado, muestra un mensaje de error.
     */
    @Override
    public void mostrarPaquete() {
        if (modelo != null) {
            System.out.println(modelo.toString());
        } else {
            System.out.println("❌ No hay paquete asociado para mostrar");
        }
    }

    /**
     * Muestra un paquete específico en consola.
     *
     * @param modelo Modelo del paquete a mostrar
     */
    @Override
    public void mostrarPaquete(ModeloPaquete modelo) {
        if (modelo != null) {
            System.out.println(modelo.toString());
        } else {
            System.out.println("❌ El modelo proporcionado es null");
        }
    }

    /**
     * Establece el modelo asociado a esta vista.
     *
     * @param modelo Nuevo modelo a asociar
     */
    public void setModelo(ModeloPaquete modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el modelo actualmente asociado.
     *
     * @return Modelo actual o null si no hay ninguno
     */
    public ModeloPaquete getModelo() {
        return modelo;
    }
}