package org.example.models;
/**
 * Implementación concreta del envío por transporte terrestre.
 *
 * <p>Esta clase representa la modalidad de envío terrestre dentro del sistema.
 * Utiliza camiones u otros medios de transporte por carretera.
 *
 * <p>Se integra al patrón Bridge al poder tener una implementación visual (IVista),
 * y al patrón State para manejar el ciclo de vida del envío.
 *
 * @author Sistema
 * @version 1.0
 * @since 2025-01-30
 */
public class EnvioTerrestre extends Envio {

    /**
     * Crea un nuevo envío terrestre con el destino especificado.
     *
     * @param destino destino del paquete
     */
    public EnvioTerrestre(String destino) {
        super(destino);
    }

    /**
     * Inicia el proceso de envío por transporte terrestre.
     * Muestra mensajes informativos y actualiza la vista si está disponible.
     */
    @Override
    public void enviar() {
        System.out.println("🚚 Enviando por Terrestre (Camión)");
        System.out.println("📍 Destino: " + destino);
        System.out.println("⏱️ Tiempo estimado: 3-7 días");

        if (implementacionVista != null) {
            implementacionVista.mostrarPaquete();
        }
    }
}

