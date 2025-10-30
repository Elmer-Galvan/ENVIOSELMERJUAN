package org.example.models;

/**
 * Clase abstracta para el patrón Factory Method.
 *
 * Define el método factory que las subclases concretas implementarán
 * para crear diferentes tipos de envíos (terrestre o aéreo).
 *
 * Este patrón permite:
 * - Delegar la creación de objetos a subclases
 * - Añadir nuevos tipos de envío sin modificar código existente
 * - Encapsular la lógica de creación
 *
 * Subclases concretas:
 * - FabricaEnvioTerrestre: Crea envíos terrestres
 * - FabricaEnvioAereo: Crea envíos aéreos
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public abstract class FabricaEnvio {

    /**
     * Factory Method: crea un envío específico según el tipo de fábrica.
     *
     * Este método abstracto debe ser implementado por cada fábrica concreta
     * para crear su tipo específico de envío.
     *
     * @param destino Ciudad o dirección de destino del envío
     * @return Instancia concreta de Envio (EnvioTerrestre o EnvioAereo)
     * @throws IllegalArgumentException si el destino es null o vacío
     */
    public abstract Envio crearEnvio(String destino);

    /**
     * Valida que el destino sea válido antes de crear el envío.
     *
     * @param destino Destino a validar
     * @throws IllegalArgumentException si el destino es null o vacío
     */
    protected void validarDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El destino no puede ser null o vacío");
        }
    }
}