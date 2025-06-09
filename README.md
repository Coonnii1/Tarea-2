#Sistema de Administración de Pruebas según la Taxonomía de Bloom

Este programa en Java Swing implementa un sistema para administrar la aplicación de pruebas con ítems clasificados según la Taxonomía de Bloom. Fue desarrollado como parte del curso **Paradigmas de Programación**.

---

## Funcionalidades

### Carga de ítems desde archivo
- Permite cargar la descripción de los ítems desde un archivo al iniciar el programa.
- Se debe seleccionar un archivo ubicado en el almacenamiento local.
- Una vez cargado, se muestra en la GUI:
  - Cantidad total de ítems.
  - Tiempo total estimado.
- Aparece un botón para iniciar la prueba.

### Aplicación de la prueba
- Se muestra un ítem por vez, con sus opciones de respuesta.
- Acciones disponibles:
  - **Volver atrás:** retrocede al ítem anterior (deshabilitado si estás en el primero).
  - **Avanzar a la siguiente:** pasa al siguiente ítem.
    - Si estás en el último, el botón cambia a **"Enviar respuestas"** y se revisa la prueba.
- Las respuestas del usuario se guardan al navegar entre ítems.

### Revisión de respuestas
- Muestra visualmente:
  - Porcentaje de respuestas correctas por nivel de la Taxonomía de Bloom.
  - Porcentaje de respuestas correctas por tipo de ítem.
- Permite revisar cada ítem nuevamente, indicando si la respuesta fue correcta o incorrecta.
- Acciones disponibles durante la revisión:
  - Volver atrás
  - Avanzar a la siguiente
  - Volver al resumen de resultados

---

## Estructura del Proyecto

Tarea-2/
├── backend/ # Lógica de la aplicación (ítems, lector de archivos, etc.)
├── frontend/ # Interfaz gráfica (Java Swing)
├── Main.java # Clase principal que lanza el programa
├── items.csv # Archivo con ítems de ejemplo (formato definido abajo)
└── README.md # Este archivo

---

## Comunicación entre módulos

Se usa el patrón **Observer** con `PropertyChangeSupport`:
- El frontend notifica eventos al backend.
- El backend actualiza el estado y notifica cambios al frontend de forma asíncrona.

---

## Cómo ejecutar el programa

### Requisitos
- Java Development Kit (JDK) 8 o superior
- IDE como IntelliJ IDEA, Eclipse, NetBeans (recomendado) o editor + terminal

### Ejecución

#### Desde IDE:
1. Abre el proyecto.
2. Ejecuta `Main.java`.

#### Desde consola:
javac backend/*.java frontend/*.java Main.java
java Main
Formato del archivo de ítems (CSV)
Cada línea representa un ítem, con campos separados por comas:

Tipo,Enunciado,Nivel,Tiempo,Respuesta,OpcionA,OpcionB,OpcionC,OpcionD
Campos esperados:
Tipo: MULTIPLE o VF

Enunciado: Texto de la pregunta

Nivel: RECORDAR, ENTENDER, APLICAR, ANALIZAR, EVALUAR, CREAR

Tiempo: Tiempo estimado en segundos

Respuesta:

MULTIPLE: Letra de la opción correcta (A, B, C o D)

VF: VERDADERO o FALSO

OpcionA a OpcionD: Solo para preguntas MULTIPLE

Ejemplos:
Selección múltiple
MULTIPLE,¿Cuál es el resultado de 3 * 4?,APLICAR,60,B,6,12,9,15
Verdadero/Falso
css
Copiar
Editar
VF,La programación orientada a objetos usa clases y objetos,ENTENDER,30,VERDADERO,,,,
📚 Créditos y Referencias
Basado en el trabajo académico y referencias como:

Anderson et al. (2000). A taxonomy for learning, teaching and assessing

Bloom, B. S. (1956). Taxonomy of educational objectives

Fuller et al. (2007). Developing a CS-specific learning taxonomy

Autor
Desarrollado por: [Constanza Garrido Arriaza]
Curso: Paradigmas de Programación
Año: 2025
