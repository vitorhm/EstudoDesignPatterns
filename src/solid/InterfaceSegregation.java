package solid;

public class InterfaceSegregation {

    public static void main(String[] args) {
        Dog thor = new Thor();
    }
}

interface Pet {
    public void comer();
}

interface Dog extends Pet {
    public void latir();
    public String getRaca();
}

class Thor implements Dog {

    @Override
    public void comer() {

    }

    @Override
    public void latir() {
        System.out.print("Au Au");
    }

    @Override
    public String getRaca() {
        return "SRD";
    }
}