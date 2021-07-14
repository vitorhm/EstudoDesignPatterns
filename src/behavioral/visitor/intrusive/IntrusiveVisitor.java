package behavioral.visitor.intrusive;

public class IntrusiveVisitor {

    public static void main(String[] args) {

    }
}

abstract class FormaGeometrica {

    abstract double getPerimetro();

}

class Retangulo extends FormaGeometrica {

    private double altura, largura;

    public Retangulo(double altura, double largura) {
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    double getPerimetro() {
        return altura + largura;
    }
}

class Triangulo extends FormaGeometrica {

    private double l1,l2,l3;

    public Triangulo(double l1, double l2, double l3) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
    }

    @Override
    double getPerimetro() {
        return l1 + l2 + l3;
    }
}
