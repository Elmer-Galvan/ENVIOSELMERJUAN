package org.example.models;

/**
 * Interfaz para la implementación en el patrón Bridge (Vista).
 *
 * Define el contrato para las diferentes implementaciones de visualización
 * del sistema. Permite separar la lógica de presentación de la lógica
 * de negocio del envío.
 *
 * Implementaciones posibles:
 * - VistaEnvio: Vista principal del sistema de envíos
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public interface IVista {

    /**
     * Muestra la información del paquete en la interfaz.
     *
     * Cada implementación define su propia forma de presentar
     * la información (consola, GUI, web, etc.).
     */
    void mostrarPaquete();

    /**
     * Muestra el paquete asociado a un modelo específico.
     *
     * @param modelo Modelo del paquete a mostrar
     */
    void mostrarPaquete(ModeloPaquete modelo);
}