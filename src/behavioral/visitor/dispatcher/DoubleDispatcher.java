package behavioral.visitor.dispatcher;

public class DoubleDispatcher {

    public static void main(String[] args) {
        Triangulo triangulo = new Triangulo(10, 20, 30);

        CalculaPerimetroForma calculaPerimetroForma = new CalculaPerimetroForma();
        Double perimetro = calculaPerimetroForma.visit(triangulo);
        System.out.println(perimetro);
    }

}

abstract class FormaGeometrica {

    abstract <T> T accept(FormaGeometricaVisitor<T>  visitor);
}

interface FormaGeometricaVisitor<T> {

    T visit(Retangulo r);
    T visit(Triangulo t);
}

class CalculaPerimetroForma implements FormaGeometricaVisitor<Double> {

    @Override
    public Double visit(Retangulo r) {
        return r.getAltura() + r.getLargura();
    }

    @Override
    public Double visit(Triangulo t) {
        return t.getL1() + t.getL2() + t.getL3();
    }
}

class Retangulo extends FormaGeometrica {

    private double altura, largura;

    public double getAltura() {
        return altura;
    }

    public double getLargura() {
        return largura;
    }

    public Retangulo(double altura, double largura) {
        this.altura = altura;
        this.largura = largura;
    }


    @Override
    <T> T accept(FormaGeometricaVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Triangulo extends FormaGeometrica {

    private double l1,l2,l3;

    public Triangulo(double l1, double l2, double l3) {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
    }

    public double getL1() {
        return l1;
    }

    public double getL2() {
        return l2;
    }

    public double getL3() {
        return l3;
    }

    @Override
    <T> T accept(FormaGeometricaVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

