package solid.dip.correto;

public class DependencyInversionCorreto {

    public static void main(String[] args) {

        Frete frete = new Frete("14810-042", MetodoEnvio.CORREIOS);

        CalculadoraFrete calculadoraFrete = new CalculadoraFreteFactory().criaCalculadoraFreteByMetodoEnvio(frete.getMetodoEnvio());

        double valor = new CalculoFrete(frete).calcula(calculadoraFrete);
    }
}

class CalculadoraFreteFactory {

    public CalculadoraFrete criaCalculadoraFreteByMetodoEnvio(MetodoEnvio metodoEnvio) {

        if (metodoEnvio == MetodoEnvio.CORREIOS) {
            return new CorreioImpl();
        }

        return new TransportadoraImpl();
    }

}

enum MetodoEnvio {
    TRANSPORTADORAS,
    CORREIOS;
}

class Frete {

    private String cep;
    private MetodoEnvio metodoEnvio;

    public Frete(String cep, MetodoEnvio metodoEnvio) {
        this.cep = cep;
        this.metodoEnvio = metodoEnvio;
    }

    public String getCep() {
        return cep;
    }

    public MetodoEnvio getMetodoEnvio() {
        return metodoEnvio;
    }
}

interface CalculadoraFrete {
    public double calcula(Frete frete);
}

class CorreioImpl implements CalculadoraFrete {

    @Override
    public double calcula(Frete frete) {
        return 15;
    }
}

class TransportadoraImpl implements CalculadoraFrete {

    @Override
    public double calcula(Frete frete) {
        return 10;
    }
}

class CalculoFrete {

    private final Frete frete;

    public CalculoFrete(Frete frete) {
        this.frete = frete;
    }

    public double calcula(CalculadoraFrete calculadora) {
        return calculadora.calcula(frete);
    }

}

