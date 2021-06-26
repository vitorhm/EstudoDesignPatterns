package creational;

public class Factory {

    public static void main(String[] args) {

    }
}

// Static Factory Method
class Retangulo {

    double largura;
    double altura;

    public Retangulo(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public static Retangulo newRetangulo(double largura, double altura) {

        if (largura == altura) {
            return null;
        }

        return new Retangulo(largura, altura);
    }

    public static Retangulo newQuadrado(double lado) {
        return new Retangulo(lado, lado);
    }

    public double getLargura() {
        return largura;
    }

    public double getAltura() {
        return altura;
    }
}

// Class Factory
class RetanguloFactory {

    public static Retangulo newRetangulo(double altura, double largura) {
        return new Retangulo(largura, altura);
    }

    public static Retangulo newQuadrado(double lado) {
        return new Retangulo(lado, lado);
    }
}

// Abstract Factory
enum TipoMeioComunicacao {
    EMAIL,
    SMS;
}

enum TipoComunicacao {
    PESSOAL,
    TRABALHO;
}

abstract class MeioComunicacao {
    public abstract void envia(String mensagem);
}

class EmailPessoal extends MeioComunicacao {

    @Override
    public void envia(String mensagem) {

    }
}

class EmailTrabalho extends MeioComunicacao {

    @Override
    public void envia(String mensagem) {

    }
}

class SmsPessoal extends MeioComunicacao {

    @Override
    public void envia(String mensagem) {

    }
}

class SmsTrabalho extends MeioComunicacao {

    @Override
    public void envia(String mensagem) {

    }

}

interface MeioComunicacaoFactory {
    public MeioComunicacao criaMeioComunicacao(TipoComunicacao tipo);
}

class EmailFactory implements MeioComunicacaoFactory {

    @Override
    public MeioComunicacao criaMeioComunicacao(TipoComunicacao tipo) {
        if (tipo == TipoComunicacao.PESSOAL) {
            return new EmailPessoal();
        }

        return new EmailTrabalho();
    }
}

class SmsFactory implements MeioComunicacaoFactory {

    @Override
    public MeioComunicacao criaMeioComunicacao(TipoComunicacao tipo) {
        if (tipo == TipoComunicacao.PESSOAL) {
            return new SmsPessoal();
        }

        return new SmsTrabalho();
    }
}

class MeioComunicacaoProvider {

    public static MeioComunicacaoFactory getMeioComunicacaoFactory(TipoMeioComunicacao tipoMeioComunicacao) {

        if (tipoMeioComunicacao == TipoMeioComunicacao.EMAIL) {
            return new EmailFactory();
        }

        return new SmsFactory();
    }

}
