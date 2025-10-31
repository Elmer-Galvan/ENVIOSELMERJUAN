package org.example.models;

/**
 * Modelo principal del sistema de envíos (Patrón MVC - Modelo).
 *
 * Representa un paquete en el sistema de gestión de envíos.
 * Integra los tres patrones de diseño:
 * - Strategy: Para calcular costos según tipo de envío
 * - State: Para gestionar el ciclo de vida del paquete
 * - Bridge: Para separar abstracción de implementación
 *
 * Esta clase encapsula toda la información y comportamiento de un paquete,
 * incluyendo su peso, descripción, estrategia de costo y tipo de envío.
 *
 * @author Sistema de Envíos MVC
 * @version 1.0
 * @since 2025-01-29
 */
public class ModeloPaquete {

    /** Identificador único del paquete */
    private int id;

    /** Peso del paquete en kilogramos */
    private double peso;


    /** Estrategia para calcular el costo (Patrón Strategy) */
    private EstrategiaCosto estrategiaCosto;

    /** Tipo de envío: terrestre o aéreo (Patrón Bridge) */
    private Envio envio;

    /** Costo calculado del envío */
    private double costoCalculado;

    /** Contador estático para generar IDs únicos */
    private static int contadorId = 1;

    private String destino;

    /**
     * Constructor completo para crear un paquete.
     *
     * @param peso Peso del paquete en kilogramos (debe ser > 0)
     * @param descripcion Descripción del contenido
     * @param estrategiaCosto Estrategia para calcular costo
     * @param envio Tipo de envío (terrestre o aéreo)
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public ModeloPaquete(double peso, String descripcion,
                         EstrategiaCosto estrategiaCosto, Envio envio) {
        validarParametros(peso, descripcion, estrategiaCosto, envio);

        this.id = contadorId++;
        this.peso = peso;
        this.destino = destino;
        this.estrategiaCosto = estrategiaCosto;
        this.envio = envio;
        this.costoCalculado = 0.0;
    }

    /**
     * Constructor simplificado sin estrategia ni envío predefinidos.
     *
     * @param peso Peso del paquete en kilogramos
     * @param descripcion Descripción del contenido
     */
    public ModeloPaquete(double peso, String descripcion) {
        this(peso, descripcion, null, null);
    }

    /**
     * Calcula el costo del envío usando la estrategia configurada.
     *
     * Aplica el patrón Strategy para calcular el costo según el
     * tipo de envío seleccionado (económico, normal, express).
     *
     * @throws IllegalStateException si no hay estrategia definida
     */
    public void calcularCosto() {
        if (estrategiaCosto == null) {
            throw new IllegalStateException(
                    "No se ha establecido una estrategia de costo. " +
                            "Use setEstrategia() primero.");
        }

        this.costoCalculado = estrategiaCosto.calcularCosto(peso);
    }

    /**
     * Establece o cambia la estrategia de cálculo de costos.
     *
     * Permite cambiar dinámicamente cómo se calcula el costo del envío
     * (patrón Strategy).
     *
     * @param estrategia Nueva estrategia a utilizar
     * @throws IllegalArgumentException si la estrategia es null
     */
    public void setEstrategia(EstrategiaCosto estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException(
                    "La estrategia de costo no puede ser null");
        }
        this.estrategiaCosto = estrategia;
    }

    /**
     * Establece el tipo de envío (terrestre o aéreo).
     *
     * @param envio Tipo de envío
     * @throws IllegalArgumentException si el envío es null
     */
    public void setEnvio(Envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser null");
        }
        this.envio = envio;
    }

    /**
     * Avanza el estado del envío al siguiente en su ciclo de vida.
     *
     * Delega al patrón State la transición de estados.
     *
     * @throws IllegalStateException si el estado no permite avanzar
     */
    public void actualizarEstado() {
        if (envio == null) {
            throw new IllegalStateException(
                    "No se puede actualizar estado sin un envío asignado");
        }
        envio.avanzarEstado();
    }

    /**
     * Obtiene una representación completa del paquete.
     *
     * @return Esta misma instancia (para encadenamiento de métodos)
     */
    public ModeloPaquete mostrarPaquete() {
        return this;
    }

    /**
     * Genera una representación en texto del paquete.
     *
     * @return String con toda la información del paquete
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("📦 INFORMACIÓN DEL PAQUETE\n");
        sb.append("═══════════════════════════════════════\n");
        sb.append(String.format("🆔 ID: %d\n", id));
        sb.append(String.format("⚖️  Peso: %.2f kg\n", peso));
        sb.append(String.format("📝 Descripción: %s\n", destino));

        if (estrategiaCosto != null) {
            sb.append(String.format("💰 Tipo de envío: %s\n",
                    estrategiaCosto.obtenerNombreEstrategia()));
            sb.append(String.format("💵 Costo: $%.2f\n", costoCalculado));
        }

        if (envio != null) {
            sb.append(String.format("📍 Destino: %s\n", envio.obtenerDestino()));
            sb.append(String.format("📊 Estado: %s\n", envio.actualizarEstado()));
        }

        sb.append("═══════════════════════════════════════");
        return sb.toString();
    }

    // ==================== GETTERS ====================

    public int getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        ModeloPaquete.contadorId = contadorId;
    }

    public void setCostoCalculado(double costoCalculado) {
        this.costoCalculado = costoCalculado;
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

    public double getCostoCalculado() {
        return costoCalculado;
    }

    // ==================== VALIDACIONES ====================

    /**
     * Valida los parámetros del constructor.
     *
     * @param peso Peso a validar
     * @param estrategia Estrategia a validar (puede ser null)
     * @param envio Envío a validar (puede ser null)
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    private void validarParametros(double peso, String descripcion,
                                   EstrategiaCosto estrategia, Envio envio) {
        if (peso <= 0) {
            throw new IllegalArgumentException(
                    "El peso debe ser mayor a 0. Peso recibido: " + peso);
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La descripción no puede ser null o vacía");
        }
    }


}