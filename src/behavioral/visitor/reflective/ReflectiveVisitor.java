package behavioral.visitor.reflective;

public class ReflectiveVisitor {
}

abstract class FormaGeometrica {

}

class CalculaPerimetroForma {

    public static double calcula(FormaGeometrica f) {

        if (f instanceof Retangulo) {
            Retangulo retangulo = (Retangulo) f;
            return retangulo.getAltura() + retangulo.getLargura();
        }

        Triangulo t = (Triangulo) f;
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
}
