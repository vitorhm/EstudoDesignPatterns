package behavioral.chain;

public class MethodChain {

    public static void main(String[] args) {

        Criatura criatura = new Criatura(2, 3, "Joao");

        Modificador root = new Modificador(criatura);

        root.addModificador(new BonusSemModificador(criatura));
        root.addModificador(new AtaqueDuploModificador(criatura));
        root.addModificador(new DefesaDuplaModificador(criatura));

        root.handle();
        System.out.println(criatura);
    }
}

class Criatura {

    private int ataque, defesa;
    private String nome;

    public Criatura(int ataque, int defesa, String nome) {
        this.ataque = ataque;
        this.defesa = defesa;
        this.nome = nome;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    @Override
    public String toString() {
        return "Criatura{" +
                "ataque=" + ataque +
                ", defesa=" + defesa +
                ", nome='" + nome + '\'' +
                '}';
    }
}

class Modificador {

    protected Criatura criatura;
    private Modificador modificador;

    Modificador(Criatura criatura) {
        this.criatura = criatura;
    }

    public void addModificador(Modificador modificador) {

        if (this.modificador == null) {
            this.modificador = modificador;
            return;
        }

        this.modificador.addModificador(modificador);
    }

    public void handle() {

        if (modificador == null) {
            return;
        }

        modificador.handle();
    }

}

class AtaqueDuploModificador extends Modificador {

    AtaqueDuploModificador(Criatura criatura) {
        super(criatura);
    }

    @Override
    public void handle() {

        criatura.setAtaque(criatura.getAtaque() * 2);
        super.handle();
    }
}

class DefesaDuplaModificador extends Modificador {

    DefesaDuplaModificador(Criatura criatura) {
        super(criatura);
    }

    @Override
    public void handle() {

        criatura.setDefesa(criatura.getDefesa() * 2);
        super.handle();
    }
}

class BonusSemModificador extends Modificador {

    BonusSemModificador(Criatura criatura) {
        super(criatura);
    }

    @Override
    public void handle() {
        System.out.println("Sem modificador");
    }
}