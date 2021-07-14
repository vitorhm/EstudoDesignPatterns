package behavioral;

public class Strategy {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setCep("14810-042");

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setMetodoEnvio(MetodoEnvio.SEDEX);
    }

    private static void processaCompra(Compra compra) {

        CalculadoraFrete calculadora = CalculadoraFreteFactory.criaCalculadora(compra.getMetodoEnvio());

        double valorFrete = calculadora.calcula(compra.getCliente().getCep());

        System.out.println("O frete para o destino informado é de " + valorFrete);

    }

}

class CalculadoraFreteFactory {

    public static CalculadoraFrete criaCalculadora(MetodoEnvio metodoEnvio) {

        switch (metodoEnvio) {
            case SEDEX:
                return new CalculadoraSedex();
            case PAC:
                return new CalculadoraPac();
            case TRANSPORTADORA:
                return new CalculadoraTransportadora();
        }

        throw new RuntimeException("Calculadora não encontrada para o método de envio informado " + metodoEnvio);
    }
}

interface CalculadoraFrete {
    public double calcula(String cep);
}

abstract class CalculadoraFreteCorreio implements CalculadoraFrete {

}

class CalculadoraSedex extends CalculadoraFreteCorreio {

    @Override
    public double calcula(String cep) {
        return 20;
    }
}

class CalculadoraPac extends CalculadoraFreteCorreio {

    @Override
    public double calcula(String cep) {
        return 15;
    }
}

class CalculadoraTransportadora implements CalculadoraFrete {

    @Override
    public double calcula(String cep) {
        return 12;
    }
}

enum MetodoEnvio {
    SEDEX,
    PAC,
    TRANSPORTADORA,
}

class Compra {

    // Lista de produtos
    private Cliente cliente;
    private MetodoEnvio metodoEnvio;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public MetodoEnvio getMetodoEnvio() {
        return metodoEnvio;
    }
}

class Cliente {

    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}