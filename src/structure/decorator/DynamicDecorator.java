package structure.decorator;

public class DynamicDecorator {

    public static void main(String[] args) {

        Camisa camisa = new Camisa("M");

        RoupaColorida roupaColorida = new RoupaColorida(camisa, "Preto");

        System.out.println(roupaColorida.getInfo());
    }
}

interface Roupa {
    public String getInfo();
}

class Camisa implements Roupa {

    private final String tamanho;

    Camisa(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String getInfo() {
        return "Camisa tamanho " + tamanho;
    }
}

class Calca implements Roupa {

    private final int tamanho;

    public Calca(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String getInfo() {
        return "Calça tamanho " + tamanho;
    }
}

class RoupaColorida implements Roupa {

    private final String cor;
    private final Roupa roupa;

    RoupaColorida(Roupa roupa, String cor) {
        this.cor = cor;
        this.roupa = roupa;
    }

    @Override
    public String getInfo() {
        return roupa.getInfo() + " na cor " + cor;
    }
}

