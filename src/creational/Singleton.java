package creational;

import java.io.Serializable;

public class Singleton implements Serializable {

    private final static Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }

}
