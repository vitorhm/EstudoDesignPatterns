package structure;

import java.util.HashMap;
import java.util.Map;

public class Flyweight {

    public static void main(String[] args) {

        System.out.println("Obtendo calculadoras");

        CalculadoraFrete calcCorreio1 = CalculadoraFreteFactory.getCalculadora(Estado.SP, TipoCalculadora.CORREIO);
        System.out.println("Obteve calculadora");
        CalculadoraFrete calcCorreio2 = CalculadoraFreteFactory.getCalculadora(Estado.SP, TipoCalculadora.CORREIO);
        System.out.println("Obteve calculadora");
        CalculadoraFrete calcCorreio3 = CalculadoraFreteFactory.getCalculadora(Estado.SP, TipoCalculadora.CORREIO);
        System.out.println("Obteve calculadora");
        CalculadoraFrete calcCorreio4 = CalculadoraFreteFactory.getCalculadora(Estado.SP, TipoCalculadora.CORREIO);
        System.out.println("Obteve calculadora");

    }

}

enum Estado {
    SP;
}

enum TipoCalculadora {
    TRANSPORTADORA,
    CORREIO;
}

interface CalculadoraFrete {
    public void calcula(Estado estado);
}

class CalculadoraCorreio implements CalculadoraFrete {

    private Estado estado; // Propriedade extrínsica
    private final TipoCalculadora tipo = TipoCalculadora.CORREIO; // Propriedade intrínsica

    CalculadoraCorreio(Estado estado) {
        this.estado = estado;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public TipoCalculadora getTipo() {
        return tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public void calcula(Estado estado) {

    }
}

class CalculadoraTransportadora implements CalculadoraFrete {

    private final Estado estado;

    CalculadoraTransportadora(Estado estado) {
        this.estado = estado;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public void calcula(Estado estado) {

    }
}

class CalculadoraFreteFactory {

    private static final Map<String, CalculadoraFrete> FRETE_LIST = new HashMap<>();

    public static CalculadoraFrete getCalculadoraCorreio(Estado estado) {

        String key = estado.toString() + "-CORREIO";

        if (FRETE_LIST.containsKey(key)) {
            return FRETE_LIST.get(key);
        }

        return FRETE_LIST.put(key, new CalculadoraCorreio(estado));
    }

    public static CalculadoraFrete getCalculadoraTransportadora(Estado estado) {

        String key = estado.toString() + "-TRANSPORTADORA";

        if (FRETE_LIST.containsKey(key)) {
            return FRETE_LIST.get(key);
        }

        return FRETE_LIST.put(key, new CalculadoraTransportadora(estado));
    }

    public static CalculadoraFrete getCalculadora(Estado estado, TipoCalculadora tipoCalculadora) {

        String key = estado.toString() + "-" + tipoCalculadora.toString();

        if (FRETE_LIST.containsKey(key)) {
            return FRETE_LIST.get(key);
        }

        switch (tipoCalculadora) {
            case TRANSPORTADORA:
                return FRETE_LIST.put(key, new CalculadoraTransportadora(estado));
            case CORREIO:
                return FRETE_LIST.put(key, new CalculadoraCorreio(estado));
        }

        throw new RuntimeException("Calculadora não encontrada para o tipo " + tipoCalculadora);
    }

}