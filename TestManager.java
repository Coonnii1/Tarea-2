package backend;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestManager {
    private List<Item> items = new ArrayList<>();
    private Map<Item, String> respuestas = new HashMap<>();
    private int currentItemIndex = 0;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getCurrentItem() {
        return items.get(currentItemIndex);
    }

    public int getCurrentItemIndex() {
        return currentItemIndex;
    }

    public int getTotalItems() {
        return items.size();
    }

    public void guardarRespuesta(Item item, String respuesta) {
        respuestas.put(item, respuesta);
    }

    public String getRespuesta(Item item) {
        return respuestas.get(item);
    }

   public Map<Item, String> getRespuestas() {
        return respuestas;
    }


    public void moveToNextItem() {
        if (currentItemIndex < items.size() - 1) {
            int oldValue = currentItemIndex;
            currentItemIndex++;
            support.firePropertyChange("currentItemIndex", oldValue, currentItemIndex);
        }
    }

    public void moveToPreviousItem() {
        if (currentItemIndex > 0) {
            int oldValue = currentItemIndex;
            currentItemIndex--;
            support.firePropertyChange("currentItemIndex", oldValue, currentItemIndex);
        }
    }

    public boolean isLastItem() {
        return currentItemIndex == items.size() - 1;
    }

    public boolean isFirstItem() {
        return currentItemIndex == 0;
    }

    public int getTiempoTotalEstimado() {
        int total = 0;
        for (Item item : items) {
            total += item.getTiempoEstimado();
        }
        return total;
    }

    public Map<BloomLevel, Double> calcularPorcentajeAciertosPorNivel() {
        Map<BloomLevel, Integer> correctasPorNivel = new HashMap<>();
        Map<BloomLevel, Integer> totalPorNivel = new HashMap<>();

        for (Item item : items) {
            BloomLevel nivel = item.getNivel();
            correctasPorNivel.put(nivel, correctasPorNivel.getOrDefault(nivel, 0) + (esRespuestaCorrecta(item) ? 1 : 0));
            totalPorNivel.put(nivel, totalPorNivel.getOrDefault(nivel, 0) + 1);
        }

        Map<BloomLevel, Double> porcentajes = new HashMap<>();
        for (BloomLevel nivel : BloomLevel.values()) {
            int correctas = correctasPorNivel.getOrDefault(nivel, 0);
            int total = totalPorNivel.getOrDefault(nivel, 0);
            porcentajes.put(nivel, total > 0 ? (double) correctas / total * 100 : 0);
        }

        return porcentajes;
    }

    public Map<String, Double> calcularPorcentajeAciertosPorTipo() {
        Map<String, Integer> correctasPorTipo = new HashMap<>();
        Map<String, Integer> totalPorTipo = new HashMap<>();

        for (Item item : items) {
            String tipo = item.getClass().getSimpleName();
            correctasPorTipo.put(tipo, correctasPorTipo.getOrDefault(tipo, 0) + (esRespuestaCorrecta(item) ? 1 : 0));
            totalPorTipo.put(tipo, totalPorTipo.getOrDefault(tipo, 0) + 1);
        }

        Map<String, Double> porcentajes = new HashMap<>();
        for (String tipo : correctasPorTipo.keySet()) {
            int correctas = correctasPorTipo.getOrDefault(tipo, 0);
            int total = totalPorTipo.getOrDefault(tipo, 0);
            porcentajes.put(tipo, total > 0 ? (double) correctas / total * 100 : 0);
        }

        return porcentajes;
    }

    public boolean esRespuestaCorrecta(Item item) {
        if (!respuestas.containsKey(item)) {
            return false; // No hay respuesta para este ítem
        }
        return respuestas.get(item).equalsIgnoreCase(item.getRespuestaCorrecta());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}