package org.example.models;

/**
 * Modelo principal del sistema de envíos dentro del patrón MVC.
 *
 * <p>Esta clase representa el "Modelo" encargado de la lógica de negocio del
 * sistema de paquetería. Integra los patrones de diseño:
 * <ul>
 *   <li><b>Strategy</b>: para el cálculo flexible del costo del envío.</li>
 *   <li><b>State</b>: para representar el ciclo de vida del envío (preparando, enviado, entregado, etc.).</li>
 *   <li><b>Bridge</b>: para desacoplar la vista de la lógica de transporte.</li>
 * </ul>
 *
 * <p>Valida datos como el peso y destino, calcula el costo según la estrategia definida,
 * y mantiene la información asociada al estado del envío.
 *
 * @author Sistema
 * @version 1.0
 * @since 2025-01-29
 */
public class ModeloPaquete {

    /** Identificador único del paquete. */
    private int id;

    /** Peso del paquete en kilogramos. */
    private double peso;

    /** Destino del paquete. */
    private String destino;

    /** Estrategia de costo utilizada (Strategy Pattern). */
    private EstrategiaCosto estrategiaCosto;

    /** Representa el envío asociado (State + Bridge). */
    private Envio envio;

    /** Valor del costo total calculado. */
    private double costoCalculado;

    /**
     * Crea un nuevo modelo de paquete con los datos especificados.
     *
     * @param peso peso del paquete en kilogramos
     * @param destino destino del paquete
     * @throws IllegalArgumentException si el peso es menor o igual a cero,
     *                                  o si el destino está vacío
     */
    public ModeloPaquete(double peso, String destino) {
        validarPeso(peso);
        validarDestino(destino);
        this.peso = peso;
        this.destino = destino;
        this.costoCalculado = 0.0;
    }

    /**
     * Calcula el costo del envío utilizando la estrategia definida.
     *
     * @throws IllegalStateException si no se ha definido una estrategia de costo
     */
    public void calcularCosto() {
        if (estrategiaCosto == null) {
            throw new IllegalStateException("No hay estrategia de costo definida.");
        }
        this.costoCalculado = estrategiaCosto.calcularCosto(peso);
    }

    /**
     * Devuelve una representación detallada del paquete,
     * incluyendo su estado y tipo de envío.
     *
     * @return cadena con los datos del paquete
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════╗\n");
        sb.append("📦 INFORMACIÓN DEL PAQUETE\n");
        sb.append("╠═══════════════════════════════════════╣\n");
        sb.append(String.format("🆔 ID: %d\n", id));
        sb.append(String.format("⚖️  Peso: %.2f kg\n", peso));
        sb.append(String.format("📍 Destino: %s\n", destino));

        if (estrategiaCosto != null) {
            sb.append(String.format("💰 Tipo: %s\n", estrategiaCosto.obtenerNombreEstrategia()));
            sb.append(String.format("💵 Costo: $%.2f\n", costoCalculado));
        }

        if (envio != null) {
            sb.append(String.format("📊 Estado: %s\n", envio.actualizarEstado()));
        }

        sb.append("╚═══════════════════════════════════════╝");
        return sb.toString();
    }

    /** Valida que el peso sea positivo. */
    private void validarPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0.");
        }
    }

    /** Valida que el destino no esté vacío. */
    private void validarDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
    }

    // ─── Getters y Setters ──────────────────────────────────────────────
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getPeso() { return peso; }
    public String getDestino() { return destino; }
    public void setEstrategiaCosto(EstrategiaCosto estrategiaCosto) { this.estrategiaCosto = estrategiaCosto; }
    public EstrategiaCosto getEstrategiaCosto() { return estrategiaCosto; }
    public Envio getEnvio() { return envio; }
    public void setEnvio(Envio envio) { this.envio = envio; }
    public double getCostoCalculado() { return costoCalculado; }
}
