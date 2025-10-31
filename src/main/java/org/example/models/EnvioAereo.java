package org.example.models;

/**
 * Implementación concreta del envío por transporte aéreo.
 *
 * <p>Esta clase representa la modalidad de envío aéreo, caracterizada
 * por su rapidez y costo elevado.
 *
 * <p>Participa en el patrón Bridge mediante la interfaz {@link IVista}
 * y en el patrón State para reflejar su ciclo de estados.
 *
 * @author Sistema
 * @version 1.0
 * @since 2025-01-29
 */
public class EnvioAereo extends Envio {

    /**
     * Crea un nuevo envío aéreo con el destino especificado.
     *
     * @param destino destino del paquete
     */
    public EnvioAereo(String destino) {
        super(destino);
    }

    /**
     * Inicia el proceso de envío aéreo, mostrando información relevante
     * y notificando a la vista si está implementada.
     */
    @Override
    public void enviar() {
        System.out.println("✈️ Enviando por Aéreo (Avión)");
        System.out.println("📍 Destino: " + destino);
        System.out.println("⏱️ Tiempo estimado: 1-3 días");

        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}

