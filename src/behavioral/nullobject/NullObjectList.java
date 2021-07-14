package behavioral.nullobject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NullObjectList {

    public static void main(String[] args) {

        Pasta pasta = new Pasta("/deasasasv");

        pasta.getArquivos()
                .forEach(System.out::println);
    }

}

class Arquivo {

    private final String name;

    Arquivo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Pasta {

    private String diretorio;

    public Pasta(String diretorio) {
        this.diretorio = diretorio;
    }

    public List<Arquivo> getArquivos() {

        File file = new File(diretorio);

        File[] files = file.listFiles();

        if (files == null) {
            return new ArrayList<>();
        }

        if (files.length == 0) {
            return new ArrayList<>();
        }

        return Arrays.stream(files)
                .filter(f -> !f.isDirectory())
                .map(f -> new Arquivo(f.getName()))
                .collect(Collectors.toList());
    }

}
