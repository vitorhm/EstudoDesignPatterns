package solid;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SingleResponsibilityPrinciple {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.addTarefa("Teste nova tarefa");
        agenda.addTarefa("123");

        try {
            agenda.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Agenda {

    private final List<String> listaTarefas = new ArrayList<>();

    public void addTarefa(String tarefa) {
        listaTarefas.add(tarefa);
    }

    public void removeTarefa(int index) {
        listaTarefas.remove(index);
    }

    public void clear() {
        listaTarefas.clear();
    }

    public void print() {
        for (String tarefa : listaTarefas) {
            System.out.println(tarefa);
        }
    }

    public void save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("teste.txt");

        for (String tarefa : listaTarefas) {
            outputStream.write(tarefa.getBytes(StandardCharsets.UTF_8));
        }

        outputStream.close();
    }

}
