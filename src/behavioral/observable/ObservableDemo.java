package behavioral.observable;

import java.util.ArrayList;
import java.util.List;

public class ObservableDemo {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        pessoa.subscribe(o -> System.out.println("Classe " + o.getSource() + " mudou a propriedade " + o.getPropertyName() + " para " + o.getNewValue()));

        pessoa.setIdade(20);
        pessoa.setIdade(30);
        pessoa.setIdade(40);
    }

}

class PropertyChangedEvent<T> {

    private Class<T> source;
    private String propertyName;
    private Object newValue;

    public PropertyChangedEvent(Class<T> source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }

    public Class<T> getSource() {
        return source;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getNewValue() {
        return newValue;
    }
}

interface Observer<T> {
    public void handle(PropertyChangedEvent<T> event);
}

class Observable<T> {

    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    protected void handle(PropertyChangedEvent<T> event) {
        observers.forEach(o -> o.handle(event));
    }

}

class Pessoa extends Observable<Pessoa> {

    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        handle(new PropertyChangedEvent<>(Pessoa.class, "nome", nome));
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
        handle(new PropertyChangedEvent<>(Pessoa.class, "idade", idade));
    }
}

