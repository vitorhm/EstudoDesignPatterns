package structure.proxy;

public class PropertyProxy {
}

class Propriedade<T> {

    private T valor;

    public Propriedade(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        System.out.println("Usuário obteve a idade");
        return valor;
    }

    public void setValor(T valor) {
        System.out.println("Usuário obteve a idade");
        this.valor = valor;
    }
}

class Pessoa {

    private Propriedade<Integer> idade;

    public Pessoa(int idade) {
        this.idade = new Propriedade<>(idade);
    }

    public int getIdade() {
        return idade.getValor();
    }

    public void setIdade(int idade) {
        this.idade.setValor(idade);
    }
}
