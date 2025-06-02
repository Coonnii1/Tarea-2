package backend;

public class TrueFalseItem extends Item {
    private boolean respuestaCorrecta;

    public TrueFalseItem(String enunciado, BloomLevel nivel, int tiempoEstimado, boolean respuestaCorrecta) {
        super(enunciado, nivel, tiempoEstimado);
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public boolean isRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    @Override
    public String getRespuestaCorrecta() {
        return String.valueOf(respuestaCorrecta);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}