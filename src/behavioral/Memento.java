package behavioral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Memento {

    private static List<NotaHistorico> historico = new ArrayList<>();

    public static void main(String[] args) {

        Nota nota = new Nota();

        menu(nota);
    }

    private static void writeLineConsole(String text) {
        System.out.println(text + Color.WHITE.getAnsi());
    }

    private static void writeConsole(String text) {
        System.out.print(text + Color.WHITE.getAnsi());
    }

    private static void menu(Nota nota) {

        int number = 1;

        boolean isEmpty = nota.get().length() == 0;

        writeLineConsole("--------------");

        writeLineConsole("Home");

        if (!isEmpty) {

            writeLineConsole("Sua nota: " + nota.get());
            writeLineConsole("");

            writeLineConsole(number + " - Editar nota");
            ++number;
        }

        writeLineConsole(number++ + " - Nova nota");

        if (!isEmpty) {
            writeLineConsole(number + " - Histórico");
        }

        writeLineConsole("--------------");

        Scanner sc = new Scanner(System.in);

        switch (sc.nextInt()) {
            case 1:
                if (!isEmpty) {
                    adicionaNota(nota);
                    menu(nota);

                    return;
                }

            case 2:
                editaNota(nota);
                menu(nota);

                return;
            case 3:
                historico(nota);
                menu(nota);
        }

    }

    private static void historico(Nota nota) {

        writeLineConsole("Histórico:");

        AtomicInteger i = new AtomicInteger(0);
        historico.forEach(
                h -> writeLineConsole(i.incrementAndGet() + " - " + h.getTexto())
        );

        writeLineConsole(i.incrementAndGet() + " - " + "Sair");

        int choice = readInt();

        if (choice <= historico.size()) {
            historico.add(nota.rollback(historico.get(choice - 1)));
        }
    }

    private static void adicionaNota(Nota nota) {

        writeConsole(nota.get());

        String text = readString();

        writeLineConsole("Escolha a cor:");

        Color color = getColor();

        historico.add(nota.add(text, color));

        writeLineConsole("Nota adicionada com sucesso");
        writeLineConsole("");
    }

    private static void editaNota(Nota nota) {

        writeConsole("Digite sua nova nota: ");

        String novaNota = readString();

        writeLineConsole("Escolha a cor:");

        Color color = getColor();

        historico.add(nota.edit(novaNota, color));

        writeLineConsole("Nota editada com sucesso");
        writeLineConsole("");
    }

    private static Color getColor() {

        AtomicInteger i = new AtomicInteger(0);
        Arrays.stream(Color.values()).forEach(
                c -> writeLineConsole(c.getAnsi() + c.getCode() + " - " + c)
        );

        return Color.from(
                readInt()
        );
    }

    private static int readInt() {
        return new Scanner(System.in).nextInt();
    }

    private static String readString() {
        return new Scanner(System.in).nextLine();
    }
}

enum Choice {
    EDITA,
    ADICIONAR,
    HISTORICO
}

enum Color {

    RED(1, "\u001B[31m", "Vermelho"),
    GREEN(2, "\u001B[32m", "Verde"),
    BLUE(3, "\u001B[34m", "Azul"),
    PURPLE(4, "\u001B[35m", "Roxo"),
    WHITE(5, "\u001B[0m", "Branco");

    private final String ansi;
    private final int code;
    private final String desc;

    Color(int code, String s, String desc) {
        this.ansi = s;
        this.code = code;
        this.desc = desc;
    }

    public static Color from(int parseInt) {

        return Arrays.stream(Color.values())
                .filter(p -> p.code == parseInt)
                .findFirst()
                .orElse(null);
    }

    public String getAnsi() {
        return ansi;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}

class Nota {

    private final StringBuilder nota = new StringBuilder();

    public NotaHistorico edit(String texto, Color color) {
        nota.delete(0, nota.length());

        return add(texto, color);
    }

    public NotaHistorico add(String texto, Color color) {

        if (color == null) {
            color = Color.WHITE;
        }

        nota.append(color.getAnsi()).append(texto);

        return new NotaHistorico(get());
    }

    public String get() {
        return nota.toString();
    }

    public NotaHistorico rollback(NotaHistorico historico) {

        nota.delete(0, nota.length());
        nota.append(historico.getTexto());

        return new NotaHistorico(get());
    }

}

// Memento
class NotaHistorico {

    private final String texto;
    public NotaHistorico(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}