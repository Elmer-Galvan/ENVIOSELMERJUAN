package org.example.models;

/**
 * Fábrica concreta que crea envíos terrestres.
 *
 * Implementación del patrón Factory Method especializada en crear
 * instancias de EnvioTerrestre. Encapsula la lógica de creación
 * de envíos por transporte terrestre.
 *
 * Uso:
 * <pre>
 * FabricaEnvio fabrica = new FabricaEnvioTerrestre();
 * Envio envio = fabrica.crearEnvio("Bogotá");
 * </pre>
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class FabricaEnvioTerrestre extends FabricaEnvio {

    /**
     * Crea una instancia de EnvioTerrestre.
     *
     * Implementa el Factory Method para producir objetos de tipo
     * EnvioTerrestre con el destino especificado.
     *
     * @param destino Ciudad o dirección de destino del envío
     * @return Nueva instancia de EnvioTerrestre
     * @throws IllegalArgumentException si el destino es null o vacío
     */
    @Override
    public Envio crearEnvio(String destino) {
        validarDestino(destino);
        return new EnvioTerrestre(destino);
    }
}