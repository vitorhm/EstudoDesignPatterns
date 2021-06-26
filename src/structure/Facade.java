package structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Facade {

    public static void main(String[] args) {

//        Ide intellij = new Ide();
//
//        Computador mac = new Computador();
//        mac.instala(intellij);
//
//        Desenvolvedor vitor = new Desenvolvedor();
//        vitor.desenvolve(intellij,"public static void main(String[] args) {}");

        Desenvolvedor vitor = new Desenvolvedor();

        DesenvolvedorFacade facade = new DesenvolvedorFacade(vitor);

        facade.desenvolveIntellijNoMac("public class Main {");
        facade.desenvolveIntellijNoMac("");
        facade.desenvolveIntellijNoMac("    public static void main(String[] args) {");
        facade.desenvolveIntellijNoMac("");
        facade.desenvolveIntellijNoMac("    }");
        facade.desenvolveIntellijNoMac("");
        facade.desenvolveIntellijNoMac("}");
    }

}

abstract class Programa {
    abstract String getNome();
}

interface Computador {
    public void instala(Programa programa);
}

class Mac implements Computador {

    private final Set<Programa> programasInstalados = new HashSet<>();

    public void instala(Programa programa) {

        if (programasInstalados.contains(programa)) {
            return;
        }

        programasInstalados.add(programa);
        System.out.println("Instalação do programa " + programa.getNome() + " concluída.");
    }

}

class Ide extends Programa {

    public void escreveCodigo(String codigo) {
        System.out.println(codigo);
    }

    @Override
    String getNome() {
        return "Intellij IDEA";
    }
}

class Desenvolvedor {

    public void desenvolve(Ide ide, String codigo) {
        ide.escreveCodigo(codigo);
    }

}

class DesenvolvedorFacade {

    private final Desenvolvedor desenvolvedor;
    private final Computador mac = new Mac();
    private final Ide intellij = new Ide();

    public DesenvolvedorFacade(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public void desenvolveIntellijNoMac(String codigo) {

        mac.instala(intellij);
        desenvolvedor.desenvolve(intellij, codigo);
    }

}