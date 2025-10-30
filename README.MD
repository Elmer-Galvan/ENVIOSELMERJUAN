# 🚚 Sistema **EnvíaConecta**

## 🧩 Descripción del proyecto
**EnvíaConecta** es un sistema desarrollado en **Java** bajo la arquitectura **MVC (Modelo–Vista–Controlador)**, diseñado para simular la gestión de envíos de paquetes.  
El proyecto aplica tres patrones de diseño —**Factory Method**, **Bridge** y **State**— con el fin de garantizar **flexibilidad, reutilización y bajo acoplamiento** entre las clases del sistema.

El sistema permite:
- Crear diferentes tipos de envíos (terrestres o aéreos).
- Cambiar el comportamiento de los envíos según su tipo.
- Gestionar los estados del envío (preparando, en tránsito, entregado o cancelado).

---

## 👥 Integrantes
- elmer jesus galvan oquendo
- juan david berrio durango

---

## 💻 Lenguaje y herramientas

| Elemento | Descripción                      |
|-----------|----------------------------------|
| 🧠 **Lenguaje principal** | Java                             |
| 🧱 **Entorno de desarrollo** | IntelliJ IDEA                    |
| 🧰 **Control de versiones** | GitHub                           |
| 🧮 **Modelado UML** | plantext                         |
| 🖼️ **Documentación visual** | Carpeta `docs/` con diagrama UML |

---

## 🏗️ Patrones usados y su rol dentro del MVC

| Patrón | Tipo | Rol en el MVC | Descripción |
|---------|------|---------------|--------------|
| **Factory Method** | Creacional | **Modelo** | Permite crear los distintos tipos de envío (`EnvioAereo`, `EnvioTerrestre`) sin depender de clases concretas. Facilita la extensión del sistema con nuevos tipos de envíos. |
| **Bridge** | Estructural | **Modelo ↔ Vista** | Separa la abstracción del envío (modelo) de su implementación concreta. Esto permite conectar distintos tipos de transporte sin afectar la lógica principal del sistema. |
| **State** | Comportamiento | **Controlador** | Gestiona los diferentes estados del envío (`Preparando`, `En tránsito`, `Entregado`, `Cancelado`), permitiendo que el objeto cambie su comportamiento dinámicamente. |

---

## ⚙️ Estructura del proyecto

---

## 🧩 Diagramas UML

### 🧱 Diagrama de Clases

El siguiente diagrama representa la estructura del sistema **EnvíaConecta**, integrando los patrones de diseño **MVC**, **Factory Method**, **Bridge**, **State** y **Strategy**.

![Diagrama de Clases](SISTEMASENVIOS/UML/uml-proyecto.jpg)





**Descripción general:**
- El **ControladorPrincipal** gestiona la comunicación entre **ModeloPaquete** y **VistaEnvio**.
- El **Factory Method** se usa para crear envíos (aéreos o terrestres).
- El **ModeloPaquete** aplica:
    - **Strategy** para calcular costos.
    - **Bridge** para separar el tipo de envío.
    - **State** para manejar los estados del paquete.

---

### 🔁 Diagrama de Secuencia: *Registro de Envío*

```text
Usuario -> VistaEnvio : ingresarDatosEnvio()
VistaEnvio -> ControladorPrincipal : registrarPaquete()
ControladorPrincipal -> FabricaEnvio : crearEnvio(destino)
FabricaEnvio -> Envio : new EnvioAereo() / EnvioTerrestre()
ControladorPrincipal -> ModeloPaquete : setEnvio(Envio)
ModeloPaquete -> EstrategiaCosto : calcular(peso)
ModeloPaquete -> EstadoEnvio : setEstado(Preparando)
ModeloPaquete -> VistaEnvio : mostrarPaquete()
``` 

### 💬 Reflexión final

### ✅ **Ventajas**
- Alta **modularidad**: cada componente cumple una función específica.
- **Extensibilidad:** se pueden agregar nuevos tipos de envío, estrategias o estados sin alterar el código existente.
- **Mantenibilidad:** los patrones reducen el acoplamiento y facilitan la comprensión del sistema.
- Aplicación clara del **principio de abierto/cerrado (OCP)**.

### ⚠️ **Limitaciones**
- Aumenta la **complejidad estructural**, ya que hay muchas clases y relaciones.
- Puede requerir **mayor tiempo de desarrollo** por el uso combinado de múltiples patrones.

### 💡 **Posibles mejoras**
- Agregar una **interfaz gráfica más interactiva** para simular el flujo de los envíos.
- Incluir un módulo de **persistencia de datos** (por ejemplo, con archivos o base de datos).

---
## ModeloPaquete

**Ubicación:** `src/main/java/org/example/models/ModeloPaquete.java`

**Propósito:** Clase principal del modelo que representa un paquete de envío. Coordina el uso de los patrones Strategy, Bridge y State.

### Responsabilidades:
- Almacenar información del paquete (id, destino, peso, costo)
- Calcular el costo del envío usando el patrón **Strategy**
- Gestionar el estado del envío mediante el patrón **State**
- Contener una referencia al tipo de envío mediante el patrón **Bridge**

### Atributos:
| Atributo | Tipo | Descripción |
|----------|------|-------------|
| `id` | `int` | Identificador único del paquete |
| `destino` | `String` | Ciudad o país destino del envío |
| `peso` | `double` | Peso del paquete en kilogramos |
| `costoFinal` | `double` | Costo calculado del envío |
| `estrategiaCosto` | `EstrategiaCosto` | Estrategia para calcular el costo (Strategy) |
| `envio` | `Envio` | Tipo de envío (Terrestre/Aéreo) (Bridge) |

### Métodos principales:
```java
void calcularCosto()                          // Calcula el costo usando la estrategia asignada
void setEstrategiaCosto(EstrategiaCosto)      // Cambia la estrategia de cálculo
void actualizarEstado()                       // Avanza al siguiente estado del envío
// Getters para todos los atributos
```

### Patrones utilizados:
- **Strategy:** Para calcular costos de forma intercambiable
- **Bridge:** Para separar abstracción (paquete) de implementación (tipo de envío)
- **State:** Para gestionar el ciclo de vida del envío

### Ejemplo de uso:
```java
Envio envio = new EnvioTerrestre("Bogotá");
ModeloPaquete paquete = new ModeloPaquete(1, "Bogotá", 15.5, envio);
paquete.setEstrategiaCosto(new EnvioExpress());
paquete.calcularCosto(); // Costo = 15.5 * 20 = 310
```
## Envio (Clase Abstracta)

**Ubicación:** `src/main/java/org/example/models/Envio.java`

**Propósito:** Clase abstracta del patrón **Bridge** que representa la abstracción de un envío. Permite que el tipo de transporte varíe independientemente del paquete.

### Responsabilidades:
- Definir la estructura común para todos los tipos de envío
- Mantener el destino del envío
- Gestionar el estado actual del envío mediante el patrón **State**
- Delegar el comportamiento de envío a las subclases concretas

### Atributos:
| Atributo | Tipo | Descripción |
|----------|------|-------------|
| `destino` | `String` | Destino del envío |
| `estado` | `EstadoEnvio` | Estado actual del envío (State) |

### Métodos principales:
```java
abstract void enviar()              // Método abstracto para enviar (implementado por subclases)
String obtenerDestino()             // Retorna el destino
EstadoEnvio getEstado()            // Obtiene el estado actual
void setEstado(EstadoEnvio)        // Cambia el estado
void ayudarEnEstado()              // Delega al estado actual
```

### Patrón utilizado:
- **Bridge:** Separa la abstracción (Envio) de su implementación (EnvioTerrestre, EnvioAereo)
- **State:** Gestiona los estados del envío

### Subclases:
- `EnvioTerrestre`
- `EnvioAereo`

### Diagrama:
```
Envio (abstracta)
  ├── EnvioTerrestre
  └── EnvioAereo
```
## EnvioTerrestre

**Ubicación:** `src/main/java/org/example/models/EnvioTerrestre.java`

**Propósito:** Implementación concreta del patrón **Bridge** que representa un envío por transporte terrestre (camión, tren, etc.).

### Responsabilidades:
- Implementar el método `enviar()` para transporte terrestre
- Heredar la gestión de estados de la clase padre `Envio`

### Constructor:
```java
public EnvioTerrestre(String destino)
```
**Parámetros:**
- `destino`: Ciudad o país de destino

### Métodos implementados:
```java
@Override
void enviar()  // Ejecuta la lógica específica de envío terrestre
```

### Patrón utilizado:
- **Bridge:** Implementación concreta de la abstracción `Envio`

### Características:
- Transporte por tierra (camión, tren, vehículo)
- Generalmente más económico pero más lento que el aéreo
- Estado inicial: `EstadoPreparando`

### Ejemplo de uso:
```java
Envio envioTerrestre = new EnvioTerrestre("Madrid");
envioTerrestre.enviar();
```
## EnvioAereo

**Ubicación:** `src/main/java/org/example/models/EnvioAereo.java`

**Propósito:** Implementación concreta del patrón **Bridge** que representa un envío por transporte aéreo (avión).

### Responsabilidades:
- Implementar el método `enviar()` para transporte aéreo
- Heredar la gestión de estados de la clase padre `Envio`

### Constructor:
```java
public EnvioAereo(String destino)
```
**Parámetros:**
- `destino`: Ciudad o país de destino

### Métodos implementados:
```java
@Override
void enviar()  // Ejecuta la lógica específica de envío aéreo
```

### Patrón utilizado:
- **Bridge:** Implementación concreta de la abstracción `Envio`

### Características:
- Transporte por aire (avión)
- Generalmente más rápido pero más costoso que el terrestre
- Estado inicial: `EstadoPreparando`

### Ejemplo de uso:
```java
Envio envioAereo = new EnvioAereo("Nueva York");
envioAereo.enviar();
```

### Diferencias con EnvioTerrestre:
| Característica | EnvioTerrestre | EnvioAereo |
|----------------|----------------|------------|
| Velocidad | Media-Baja | Alta |
| Costo | Bajo-Medio | Alto |
| Transporte | Camión/Tren | Avión |
## FabricaEnvio (Clase Abstracta)

**Ubicación:** `src/main/java/org/example/models/FabricaEnvio.java`

**Propósito:** Clase abstracta del patrón **Factory Method** que define la interfaz para crear objetos de tipo `Envio`.

### Responsabilidades:
- Definir el método factory que las subclases deben implementar
- Permitir que las subclases decidan qué tipo de envío instanciar
- Promover el principio Open/Closed (abierto para extensión, cerrado para modificación)

### Método abstracto:
```java
public abstract Envio crearEnvio(String destino);
```

### Patrón utilizado:
- **Factory Method:** Define una interfaz para crear objetos, pero deja que las subclases decidan qué clase instanciar

### Subclases concretas:
- `FabricaEnvioTerrestre` → Crea `EnvioTerrestre`
- `FabricaEnvioAereo` → Crea `EnvioAereo`

### Ventajas:
- ✅ Elimina el uso de condicionales (`if-else`) para crear objetos
- ✅ Facilita agregar nuevos tipos de envío sin modificar código existente
- ✅ Cada fábrica tiene una única responsabilidad (Single Responsibility Principle)

### Diagrama:
```
FabricaEnvio (abstracta)
  ├── FabricaEnvioTerrestre → new EnvioTerrestre()
  └── FabricaEnvioAereo → new EnvioAereo()
```

### Ejemplo de uso:
```java
FabricaEnvio fabrica = new FabricaEnvioTerrestre();
Envio envio = fabrica.crearEnvio("Bogotá");
```
## FabricaEnvioTerrestre

**Ubicación:** `src/main/java/org/example/models/FabricaEnvioTerrestre.java`

**Propósito:** Implementación concreta del patrón **Factory Method** que crea objetos de tipo `EnvioTerrestre`.

### Responsabilidades:
- Crear instancias de `EnvioTerrestre`
- Implementar el método factory heredado de `FabricaEnvio`

### Método implementado:
```java
@Override
public Envio crearEnvio(String destino) {
    return new EnvioTerrestre(destino);
}
```

### Patrón utilizado:
- **Factory Method:** Creador concreto que instancia productos concretos

### Características:
- Especializada en crear envíos terrestres
- No requiere condicionales
- Fácil de extender sin modificar código existente

### Ejemplo de uso:
```java
FabricaEnvio fabricaTerrestre = new FabricaEnvioTerrestre();
Envio envio = fabricaTerrestre.crearEnvio("Madrid");
// envio es de tipo EnvioTerrestre
```

### Relación con otras clases:
```
FabricaEnvioTerrestre
      ↓ crea
EnvioTerrestre
```
## FabricaEnvioAereo

**Ubicación:** `src/main/java/org/example/models/FabricaEnvioAereo.java`

**Propósito:** Implementación concreta del patrón **Factory Method** que crea objetos de tipo `EnvioAereo`.

### Responsabilidades:
- Crear instancias de `EnvioAereo`
- Implementar el método factory heredado de `FabricaEnvio`

### Método implementado:
```java
@Override
public Envio crearEnvio(String destino) {
    return new EnvioAereo(destino);
}
```

### Patrón utilizado:
- **Factory Method:** Creador concreto que instancia productos concretos

### Características:
- Especializada en crear envíos aéreos
- No requiere condicionales
- Fácil de extender sin modificar código existente

### Ejemplo de uso:
```java
FabricaEnvio fabricaAerea = new FabricaEnvioAereo();
Envio envio = fabricaAerea.crearEnvio("París");
// envio es de tipo EnvioAereo
```

### Relación con otras clases:
```
FabricaEnvioAereo
      ↓ crea
EnvioAereo
```

### Comparación con FabricaEnvioTerrestre:
| Característica | FabricaEnvioTerrestre | FabricaEnvioAereo |
|----------------|----------------------|-------------------|
| Producto creado | `EnvioTerrestre` | `EnvioAereo` |
| Tipo de transporte | Terrestre | Aéreo |
| Velocidad típica | Media | Alta |
## EstrategiaCosto (Interfaz)

**Ubicación:** `src/main/java/org/example/models/EstrategiaCosto.java`

**Propósito:** Interfaz del patrón **Strategy** que define el contrato para calcular el costo de un envío.

### Responsabilidades:
- Definir el método `calcular()` que todas las estrategias deben implementar
- Permitir cambiar el algoritmo de cálculo de costo en tiempo de ejecución
- Encapsular diferentes formas de calcular costos

### Método abstracto:
```java
double calcular(double peso);
```
**Parámetros:**
- `peso`: Peso del paquete en kilogramos

**Retorna:** Costo total del envío

### Patrón utilizado:
- **Strategy:** Define una familia de algoritmos, los encapsula y los hace intercambiables

### Implementaciones concretas:
| Estrategia | Fórmula | Descripción |
|------------|---------|-------------|
| `EnvioNormal` | `peso * 10` | Envío estándar, precio base |
| `EnvioExpress` | `peso * 20` | Envío rápido, el doble del precio |
| `EnvioEconomico` | `peso * 5` | Envío lento, mitad del precio |

### Ventajas del patrón Strategy:
- ✅ Elimina condicionales para seleccionar algoritmos
- ✅ Facilita agregar nuevas estrategias sin modificar código existente
- ✅ Permite cambiar el algoritmo en tiempo de ejecución

### Diagrama:
```
EstrategiaCosto (interfaz)
  ├── EnvioNormal    → peso * 10
  ├── EnvioExpress   → peso * 20
  └── EnvioEconomico → peso * 5
```

### Ejemplo de uso:
```java
EstrategiaCosto estrategia = new EnvioExpress();
double costo = estrategia.calcular(15.5); // 15.5 * 20 = 310

// Cambiar estrategia en tiempo de ejecución
estrategia = new EnvioEconomico();
costo = estrategia.calcular(15.5); // 15.5 * 5 = 77.5
```

### Integración con ModeloPaquete:
```java
ModeloPaquete paquete = new ModeloPaquete(1, "Madrid", 10.0, envio);
paquete.setEstrategiaCosto(new EnvioExpress());
paquete.calcularCosto(); // Usa la estrategia asignada
```
