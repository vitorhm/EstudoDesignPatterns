package structure.proxy;

public class ProtectionProxy {

    public static void main(String[] args) {

        Cliente lorrant = new Cliente(12);

        Bar bar = new BarDoVitorProxy();
        bar.servirCerveja(lorrant);
        bar.venderBala(lorrant);
    }

}

class Cliente {

    private final int idade;

    Cliente(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }
}

interface Bar {
    public void servirCerveja(Cliente cliente);
    public void venderCigarro(Cliente cliente);
    public void venderBala(Cliente cliente);
}

class BarDoVitor implements Bar {

    @Override
    public void servirCerveja(Cliente cliente) {
        System.out.println("Vendeu cerveja");
    }

    @Override
    public void venderCigarro(Cliente cliente) {
        System.out.println("Vendeu cigarro");
    }

    @Override
    public void venderBala(Cliente cliente) {
        System.out.println("Vendeu bala");
    }
}

class BarDoVitorProxy extends BarDoVitor {

    @Override
    public void servirCerveja(Cliente cliente) {

        if (cliente.getIdade() < 21) {
            System.out.println("*** Atenção: O cliente " + cliente + " tem apenas " + cliente.getIdade() + " anos");
            return;
        }

        super.servirCerveja(cliente);
    }

    @Override
    public void venderCigarro(Cliente cliente) {

        if (cliente.getIdade() < 21) {
            System.out.println("*** Atenção: O cliente " + cliente + " tem apenas " + cliente.getIdade() + " anos");
            return;
        }

        super.venderCigarro(cliente);
    }
}