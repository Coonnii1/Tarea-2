# Sistema de Administración de Pruebas según la Taxonomía de Bloom

Este programa en Java Swing implementa un sistema para administrar la aplicación de pruebas con ítems clasificados según la Taxonomía de Bloom.  
Desarrollado como parte del curso **Paradigmas de Programación**.

---

## Funcionalidades

### Carga de ítems desde archivo
- Permite cargar los ítems desde un archivo al iniciar el programa.
- Se debe seleccionar un archivo desde el almacenamiento local.
- Una vez cargado, la GUI muestra:
  - Cantidad total de ítems.
  - Tiempo total estimado.
  - Botón para iniciar la prueba.

### Aplicación de la prueba
- Se muestra un ítem por vez, con opciones de respuesta.
- Acciones disponibles:
  - **Volver atrás**: retrocede al ítem anterior (deshabilitado si es el primero).
  - **Avanzar a la siguiente**: avanza al siguiente ítem.
    - Si es el último, el botón cambia a **"Enviar respuestas"** y se revisa la prueba.
- Las respuestas se guardan al navegar.

### Revisión de respuestas
- Muestra visualmente:
  - Porcentaje de respuestas correctas por **nivel** de Bloom.
  - Porcentaje de respuestas correctas por **tipo de ítem**.
- Permite revisar cada ítem, indicando si fue respondido correctamente.
- Acciones disponibles:
  - Volver atrás
  - Avanzar a la siguiente
  - Volver al resumen

---

## Estructura del Proyecto

Tarea-2/
├── backend/ # Lógica (ítems, carga, controladores)
├── frontend/ # GUI con Java Swing
├── Main.java # Clase principal
├── items.csv # Archivo de ítems de ejemplo
└── README.md # Este archivo


---

## Comunicación entre módulos

Se utiliza el patrón **Observer** con `PropertyChangeSupport`:

- El **frontend** notifica eventos al **backend**.
- El **backend** actualiza estado y notifica al frontend de forma asíncrona.

---

## Cómo ejecutar el programa

### Requisitos
- JDK 8 o superior
- IDE como IntelliJ IDEA, Eclipse, NetBeans o terminal + editor

### Desde IDE
1. Abre el proyecto.
2. Ejecuta `Main.java`.

### Desde consola
javac backend/*.java frontend/*.java Main.java
java Main
Formato del archivo de ítems (CSV)
Cada línea representa un ítem y tiene los siguientes campos separados por comas:

Tipo,Enunciado,Nivel,Tiempo,Respuesta,OpcionA,OpcionB,OpcionC,OpcionD
Campos esperados:
Tipo: MULTIPLE o VF

Enunciado: Texto de la pregunta

Nivel: RECORDAR, ENTENDER, APLICAR, ANALIZAR, EVALUAR, CREAR

Tiempo: en segundos

Respuesta:

MULTIPLE: A, B, C o D

VF: VERDADERO o FALSO

Opciones A-D: Solo para MULTIPLE

Ejemplos:
Selección múltiple:

MULTIPLE,¿Cuál es el resultado de 3 * 4?,APLICAR,60,B,6,12,9,15
Verdadero/Falso:

VF,La programación orientada a objetos usa clases y objetos,ENTENDER,30,VERDADERO,,,,
Autor
Constanza Garrido Arriaza
Curso: Paradigmas de Programación
Año: 2025
