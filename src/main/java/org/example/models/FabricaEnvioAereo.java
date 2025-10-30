package org.example.models;

/**
 * Fábrica concreta que crea envíos aéreos.
 *
 * Implementación del patrón Factory Method especializada en crear
 * instancias de EnvioAereo. Encapsula la lógica de creación
 * de envíos por transporte aéreo.
 *
 * Uso:
 * <pre>
 * FabricaEnvio fabrica = new FabricaEnvioAereo();
 * Envio envio = fabrica.crearEnvio("Madrid");
 * </pre>
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class FabricaEnvioAereo extends FabricaEnvio {

    /**
     * Crea una instancia de EnvioAereo.
     *
     * Implementa el Factory Method para producir objetos de tipo
     * EnvioAereo con el destino especificado.
     *
     * @param destino Ciudad o dirección de destino del envío
     * @return Nueva instancia de EnvioAereo
     * @throws IllegalArgumentException si el destino es null o vacío
     */
    @Override
    public Envio crearEnvio(String destino) {
        validarDestino(destino);
        return new EnvioAereo(destino);
    }
}