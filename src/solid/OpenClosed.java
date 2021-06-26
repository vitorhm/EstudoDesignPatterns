package solid;

import java.util.List;
import java.util.stream.Stream;

public class OpenClosed {

    private Tamanho tamanho;
    private Color color;

    public OpenClosed(Tamanho tamanho, Color color) {
        this.tamanho = tamanho;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }
}

interface Specification<T> {
    public boolean isSatisfied(T item);
}

interface Filter<T> {
    public Stream<T> filter(List<T> items, Specification<T> specification);
}

class BetterFilter implements Filter<OpenClosed> {

    @Override
    public Stream<OpenClosed> filter(List<OpenClosed> items, Specification<OpenClosed> specification) {
        return items.stream().filter(specification::isSatisfied);
    }
}

enum Tamanho {
    SMALL,
    MEDIUM,
    LARGE;
}

enum Color {
    BLACK,
    RED,
    WHITE;
}