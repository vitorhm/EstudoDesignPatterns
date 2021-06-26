package solid.dip.incorreto;

public class DependencyInversion {

    public static void main(String[] args) {

        Frete frete = new Frete("14810-042", MetodoEnvio.TRANSPORTADORA);

        CalculaFrete calculaFrete = new CalculaFrete(frete);
        calculaFrete.calcula(new Transportadora());
    }

}

class Frete {
    private String cep;
    private MetodoEnvio metodoEnvio;

    public Frete(String cep, MetodoEnvio metodoEnvio) {
        this.cep = cep;
        this.metodoEnvio = metodoEnvio;
    }
}

enum MetodoEnvio {
    TRANSPORTADORA,
    CORREIOS;
}

class Transportadora {

    public double calcula(Frete frete) {
        return 15;
    }
}

class Correio {

    public double calcula(Frete frete) {
        return 10;
    }
}

class CalculaFrete {

    private Frete frete;

    public CalculaFrete(Frete frete) {
        this.frete = frete;
    }

    public double calcula(Transportadora transportadora) {
        return transportadora.calcula(frete);
    }

    public double calcula(Correio correio) {
        return correio.calcula(frete);
    }
}

