package backend;

public abstract class Item {
    private String enunciado;
    private BloomLevel nivel;
    private int tiempoEstimado;

    public Item(String enunciado, BloomLevel nivel, int tiempoEstimado) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public BloomLevel getNivel() {
        return nivel;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public abstract String getRespuestaCorrecta();

    public abstract String toString();
}