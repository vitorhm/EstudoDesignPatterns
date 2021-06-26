package structure;

import java.util.List;

public class Composite {

    public static void main(String[] args) {

        DepartamentoVendas departamentoVendas = new DepartamentoVendas();
        DepartamentoFinancas departamentoFinancas = new DepartamentoFinancas();

        DepartamentoChefe departamentoChefe = new DepartamentoChefe();


    }

}

interface Departamento {
    public void imprimeNome();
}

class DepartamentoVendas implements Departamento {

    @Override
    public void imprimeNome() {

    }
}

class DepartamentoFinancas implements Departamento {

    @Override
    public void imprimeNome() {

    }
}

class DepartamentoChefe implements Departamento {

    private List<Departamento> departamentosFilhos;

    @Override
    public void imprimeNome() {
        departamentosFilhos.forEach(Departamento::imprimeNome);
    }


}
