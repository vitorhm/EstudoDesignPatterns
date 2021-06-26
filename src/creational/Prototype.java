package creational;

import java.util.List;

public class Prototype {

    public static void main(String[] args) {
        Produto produto = new Produto();


    }
}

class Produto implements Cloneable {

    private String name;
    private List<CodigoBarras> listaCodigoBarras;
}

class CodigoBarras implements Cloneable {
    private boolean ativo;
    private String codigoBarras;

    public CodigoBarras(boolean ativo, String codigoBarras) {
        this.codigoBarras = codigoBarras;
        this.ativo = ativo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }
}