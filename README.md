# Sistema de Administración de Pruebas según la Taxonomía de Bloom

Este programa en Java Swing implementa un sistema para administrar la aplicación de pruebas con ítems clasificados según la Taxonomía de Bloom. [cite: 48, 49]

## Funcionalidades

* **Carga de ítems desde archivo:** Permite cargar la descripción de los ítems construidos de la prueba a aplicar al iniciar el programa. [cite: 49, 50] Se debe hacer mediante la selección de un archivo ubicado en el almacenamiento donde esté ejecutando su programa. [cite: 50, 51] Una vez correctamente cargado, se debe desplegar en la GUI la siguiente información de la prueba: cantidad de ítems y tiempo total estimado. [cite: 51, 52] También debe mostrarse un botón que permita dar inicio a la prueba. [cite: 52, 53] El formato del archivo queda a criterio de los estudiantes. [cite: 53, 54]
* **Aplicación de la prueba:** En una ventana se debe mostrar un ítem por vez, en donde se muestren las distintas posibilidades de respuesta respecto del ítem. [cite: 54, 55] Se debe disponer en todo momento de las siguientes acciones:

    * Volver atrás: retrocede al ítem anterior. [cite: 55, 56] Si está en la primera pregunta, esta opción debe estar deshabilitada. [cite: 56, 57]
    * Avanzar a la siguiente: avanza al siguiente ítem. [cite: 56, 57] Si está en la última pregunta, la funcionalidad de este botón debe cambiar "aviar respuestas", en cuyo caso se procede a la ejecución de la revisión de respuestas.

    Al moverse entre ítems, se debe mantener la respuesta ingresada por el usuario.
* **Revisión de respuestas:** Una vez finalizada la prueba y enviadas las respuestas, el sistema debe entregar visualmente un resumen que indique lo siguiente:

    * Porcentaje de respuestas correctas desglosadas según nivel de la Taxonomía de Bloom al que pertenecen. [cite: 57, 58]
    * Porcentaje de respuestas correctas desglosadas según tipo de ítem.

    Debe disponer además de un botón que permita ejecutar la funcionalidad de revisar las respuestas. [cite: 57, 58] La visualización debe ser equivalente a la de la funcionalidad de Aplicación de la prueba, indicando si la respuesta fue respondida correctamente o no. Las acciones que se deben disponer en todo momento son las de Volver atrás y Avanzar a la siguiente, las que son equivalentes a las de la funcionalidad de Aplicación de la prueba. [cite: 58, 59] Se debe añadir un botón que permita volver a la visualización del resumen de respuestas correctas. [cite: 59, 60]

## Estructura del Proyecto

El proyecto está organizado en dos paquetes principales:

* `backend`: Contiene la lógica de la aplicación (clases para ítems, lector de archivos, administrador de pruebas, etc.). [cite: 61, 62]
* `frontend`: Contiene la implementación de la interfaz gráfica de usuario (GUI) en Java Swing. [cite: 61, 62]

La comunicación entre estos paquetes se realiza mediante el patrón Observer (implementado con `PropertyChangeSupport` en Java). [cite: 61, 62]

## Cómo ejecutar el programa

1.  **Requisitos:**
    * Java Development Kit (JDK) 8 o superior instalado.
    * Entorno de Desarrollo Integrado (IDE) como Eclipse, IntelliJ IDEA o NetBeans (recomendado), o un editor de texto y la línea de comandos.
2.  **Compilación y ejecución:**
    * **IDE:** Importa el proyecto en tu IDE y ejecútalo.
    * **Línea de comandos:**
        * Compila los archivos Java: `javac backend/*.java frontend/*.java Main.java`
        * Ejecuta el programa: `java Main`
3.  **Carga del archivo de ítems:**
    * Al iniciar el programa, se mostrará una ventana para seleccionar el archivo CSV que contiene los ítems de la prueba.
    * Selecciona el archivo y se mostrará la información de la prueba (cantidad de ítems y tiempo total).
    * Haz clic en "Iniciar Prueba" para comenzar.

## Formato del archivo de ítems (CSV)

El archivo de ítems debe ser un archivo CSV (Comma Separated Values) con la siguiente estructura: [cite: 63, 64, 65, 66, 67]

Cada línea representa un ítem de la prueba y contiene los siguientes campos separados por comas:

1.  **Tipo de ítem:** `MultipleChoice` o `TrueFalse`
2.  **Enunciado:** El texto de la pregunta.
3.  **Nivel de Bloom:** `Recordar`, `Entender`, `Aplicar`, `Analizar`, `Evaluar` o `Crear`
4.  **Tiempo estimado (segundos):** Un número entero que indica el tiempo estimado para responder la pregunta.
5.  **Respuesta correcta:**
    * Para `MultipleChoice`: La letra de la opción correcta (A, B, C, D).
    * Para `TrueFalse`: `true` o `false` (en minúsculas).
6.  **Opción A:** (Solo para `MultipleChoice`) El texto de la opción A.
7.  **Opción B:** (Solo para `MultipleChoice`) El texto de la opción B.
8.  **Opción C:** (Solo para `MultipleChoice`) El texto de la opción C.
9.  **Opción D:** (Solo para `MultipleChoice`) El texto de la opción D.

**Ejemplo:**

```csv
MultipleChoice,"¿Cuál es la capital de Francia?",Recordar,30,A,"París","Londres","Berlín","Roma"
TrueFalse,"La Tierra es plana.",Recordar,15,false
MultipleChoice,"¿Qué método se utiliza para ordenar un array en Java?",Aplicar,60,C,"sort()","reverse()","Arrays.sort()","order()"