package backend;

import java.util.Arrays;
import java.util.List;

public class MultipleChoiceItem extends Item {
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private String respuestaCorrecta; // A, B, C, or D

    public MultipleChoiceItem(String enunciado, BloomLevel nivel, int tiempoEstimado,
                             String opcionA, String opcionB, String opcionC, String opcionD,
                             String respuestaCorrecta) {
        super(enunciado, nivel, tiempoEstimado);
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    @Override
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

   public List<String> getOpciones() {
        return Arrays.asList(opcionA, opcionB, opcionC, opcionD);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "A) " + opcionA + "\n" +
                "B) " + opcionB + "\n" +
                "C) " + opcionC + "\n" +
                "D) " + opcionD;
    }
}