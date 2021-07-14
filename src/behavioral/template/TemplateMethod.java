package behavioral.template;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TemplateMethod {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Insira seu cep: ");

        String cep = sc.nextLine();

        boolean isOk = false;
        int choice = -1;

        System.out.println("Selecione um tipo de frete: ");

        while (!isOk) {

            AtomicInteger i = new AtomicInteger(0);
            Arrays.stream(TipoFrete.values()).forEach(
                    t -> System.out.println(i.getAndIncrement() + " - " + t.toString())
            );

            choice = sc.nextInt();

            isOk = choice >= 0 && choice < TipoFrete.values().length;
        }

        TipoFrete tipo = TipoFrete.values()[choice];

        CalculadoraFrete calculadora = CalculadoraFreteFactory.criaCalculadora(tipo);

        System.out.println("O frete para " + cep + " fica em " + calculadora.calcula(cep));
    }

}


class CalculadoraFreteFactory {

    public static CalculadoraFrete criaCalculadora(TipoFrete metodoEnvio) {

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

enum TipoFrete {
    SEDEX,
    PAC,
    TRANSPORTADORA,
}

interface CalculadoraFrete {
    public double calcula(String cep);
}

class CalculadoraTransportadora implements CalculadoraFrete {

    @Override
    public double calcula(String cep) {
        return 10;
    }
}

abstract class CalculadoraCorreio implements CalculadoraFrete {

    @Override
    public double calcula(String cep) {

        // Efetua parâmetros de conexão com o webservice

        String parametro = getParametro();

        return 20;
    }

    abstract String getParametro();
}

class CalculadoraSedex extends CalculadoraCorreio {

    @Override
    String getParametro() {
        return "SEDEX";
    }
}

class CalculadoraPac extends CalculadoraCorreio {

    @Override
    String getParametro() {
        return "PAC";
    }
}
