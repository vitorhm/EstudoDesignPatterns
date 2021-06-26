package solid;

public class Liskov {

    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(10, 20);

        Quadrado quadrado = new Quadrado(10);

        useIt(quadrado);
    }

    private static void useIt(Rectangle rectangle) {

        int largura = rectangle.getLargura();
        rectangle.setAltura(5);

        System.out.print("Área obtida é " + rectangle.getArea() + ". Era esperado " + largura * rectangle.getAltura());

    }

}

class Rectangle {
    protected int altura, largura;

    public Rectangle(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getArea() {
        return this.altura * largura;
    }
}

class Quadrado extends Rectangle {

    public Quadrado(int tamanho) {
        super(tamanho, tamanho);
    }

    @Override
    public void setAltura(int altura) {
        super.setAltura(altura);
        super.setLargura(altura);
    }

    @Override
    public void setLargura(int largura) {
        super.setLargura(largura);
        super.setAltura(largura);
    }
}