package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemFileReader {

    public static List<Item> readItemsFromFile(String filePath) throws IOException, IllegalArgumentException {
        List<Item> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] fields = line.split(",");
                if (fields.length < 5) {
                    throw new IllegalArgumentException("Error en la línea " + lineNumber + ": Faltan campos.");
                }

                String type = fields[0].trim();
                String enunciado = fields[1].trim().replace("\"", "");
                BloomLevel nivel = parseBloomLevel(fields[2].trim());
                int tiempoEstimado = parseTiempoEstimado(fields[3].trim(), lineNumber);
                String respuestaCorrecta = fields[4].trim().replace("\"", "");

                if (type.equalsIgnoreCase("MultipleChoice")) {
                    if (fields.length != 9) {
                        throw new IllegalArgumentException("Error en la línea " + lineNumber + ": Faltan opciones para MultipleChoice.");
                    }
                    String opcionA = fields[5].trim().replace("\"", "");
                    String opcionB = fields[6].trim().replace("\"", "");
                    String opcionC = fields[7].trim().replace("\"", "");
                    String opcionD = fields[8].trim().replace("\"", "");
                    items.add(new MultipleChoiceItem(enunciado, nivel, tiempoEstimado, opcionA, opcionB, opcionC, opcionD, respuestaCorrecta));
                } else if (type.equalsIgnoreCase("TrueFalse")) {
                    if (fields.length != 5) {
                        throw new IllegalArgumentException("Error en la línea " + lineNumber + ": Campos incorrectos para TrueFalse.");
                    }
                    boolean correcta = parseBoolean(respuestaCorrecta, lineNumber);
                    items.add(new TrueFalseItem(enunciado, nivel, tiempoEstimado, correcta));
                } else {
                    throw new IllegalArgumentException("Error en la línea " + lineNumber + ": Tipo de ítem desconocido: " + type);
                }
            }
        }
        return items;
    }

    private static BloomLevel parseBloomLevel(String level) {
        try {
            return BloomLevel.valueOf(level);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Nivel de Bloom inválido: " + level);
        }
    }

    private static int parseTiempoEstimado(String tiempo, int lineNumber) {
        try {
            return Integer.parseInt(tiempo);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error en la línea " + lineNumber + ": Tiempo estimado inválido: " + tiempo);
        }
    }

    private static boolean parseBoolean(String value, int lineNumber) {
        if (value.equalsIgnoreCase("true")) {
            return true;
        } else if (value.equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new IllegalArgumentException("Error en la línea " + lineNumber + ": Valor booleano inválido: " + value);
        }
    }
}