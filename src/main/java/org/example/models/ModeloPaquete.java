package org.example.models;

/**
 * Modelo principal del sistema de envíos (Patrón MVC - Modelo).
 * Integra los patrones Strategy, State y Bridge.
 */
public class ModeloPaquete {

    private int id;
    private double peso;
    private String destino;
    private EstrategiaCosto estrategiaCosto;
    private Envio envio;
    private double costoCalculado;

    public ModeloPaquete(double peso, String destino) {
        validarPeso(peso);
        validarDestino(destino);

        this.peso = peso;
        this.destino = destino;
        this.costoCalculado = 0.0;
    }

    public void calcularCosto() {
        if (estrategiaCosto == null) {
            throw new IllegalStateException("No hay estrategia de costo definida");
        }
        this.costoCalculado = estrategiaCosto.calcularCosto(peso);
    }

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

    // Validaciones
    private void validarPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }
    }

    private void validarDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío");
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public String getDestino() {
        return destino;
    }

    public void setEstrategiaCosto(EstrategiaCosto estrategiaCosto) {
        this.estrategiaCosto = estrategiaCosto;
    }

    public EstrategiaCosto getEstrategiaCosto() {
        return estrategiaCosto;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public double getCostoCalculado() {
        return costoCalculado;
    }
}