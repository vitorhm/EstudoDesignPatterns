package behavioral;

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

public class Interpreter {

    public static void main(String[] args) {

        String expressao = "2*3+5+(10*2)";

        final Operador operador = parse(expressao);

        int value = operador.interpretar();

        System.out.print(expressao + " = " + value);
    }

    private static Operador parse(@NotNull String expressao) {

        Operador left = null;

        for (int i = 0; i < expressao.trim().length(); i++) {
            char expr = expressao.charAt(i);

            switch (expr) {
                case '(':
                    int endExpIndex = expressao.indexOf(')');
                    left = parse(expressao.substring(i + 1, endExpIndex));
                    i = endExpIndex;
                    left.setPref(true);

                    break;
                case '*':
                case '+':
                case '-':
                    Operacao op = OperacaoFactory.criaOperacaoPorSinal(expr);

                    if (op == null) {
                        throw new RuntimeException("Operação não identificada");
                    }

                    op.setEsquerda(left);
                    op.setDireita(parse(expressao.substring(i+1)));

                    return op;
                default:
                    if (Character.isDigit(expr)) {
                        Pair<Integer, Numero> pair = buildNumero(expressao.substring(i));
                        left = pair.getValue();
                        i = i + pair.getKey() - 1;
                        break;
                    }
            }

        }

        return left;
    }

    private static Pair<Integer, Numero> buildNumero(String expression) {

        StringBuilder builder = new StringBuilder();

        int j = 0;

        for (; j < expression.length(); j++) {
            if (!Character.isDigit(expression.charAt(j))) {
                break;
            }

            builder.append(expression.charAt(j));
        }

        return new Pair<>(j, new Numero(Integer.parseInt(builder.toString())));
    }

}

abstract class Operador {

    private boolean pref;

    public abstract int interpretar();

    public void setPref(boolean pref) {
        this.pref = pref;
    }

    public boolean isPref() {
        return pref;
    }
}

class Numero extends Operador {

    private final int numero;

    Numero(int numero) {
        this.numero = numero;
    }

    @Override
    public int interpretar() {
        return numero;
    }

    @Override
    public String toString() {
        return String.valueOf(numero);
    }
}

abstract class Operacao extends Operador {

    private Operador esquerda;
    private Operador direita;

    public Operador getEsquerda() {

        return esquerda;
    }

    public Operador getDireita() {

        return direita;
    }

    public void setDireita(Operador direita) {
        this.direita = direita;
    }

    public void setEsquerda(Operador esquerda) {
        this.esquerda = esquerda;
    }

    public Operador getFirst() {

        if (this.direita.isPref()) {
            return this.direita;
        }

        return this.esquerda;
    }

    public Operador getLast() {

        if (this.direita.isPref()) {
            return this.esquerda;
        }

        return this.direita;
    }

    public abstract String getSinal();

    @Override
    public String toString() {
        return getFirst().toString() + " " + getSinal() + " " + getLast().toString();
    }

}

class Adicao extends Operacao {

    @Override
    public int interpretar() {
        return getFirst().interpretar() + getLast().interpretar();
    }

    @Override
    public String getSinal() {
        return "+";
    }
}

class Subtracao extends Operacao {

    @Override
    public int interpretar() {
        return getFirst().interpretar() - getLast().interpretar();
    }

    @Override
    public String getSinal() {
        return "-";
    }
}

class Multiplicacao extends Operacao {

    @Override
    public int interpretar() {
        return getFirst().interpretar() * getLast().interpretar();
    }

    @Override
    public String getSinal() {
        return "*";
    }
}

class OperacaoFactory {

    public static Operacao criaOperacaoPorSinal(char sinal) {

        switch (sinal) {
            case '*':
                return new Multiplicacao();
            case '-':
                return new Subtracao();
            case '+':
                return new Adicao();
        }

        return null;
    }

}