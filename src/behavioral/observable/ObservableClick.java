package behavioral.observable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObservableClick {

    public static void main(String[] args) {

        Tela tela = new Tela();
        tela.subscribe(() -> System.out.println("Click"));
        tela.subscribe(() -> System.out.println("Click 2"));
        tela.subscribe(() -> System.out.println("Click 3"));

        tela.getButton().doClick();
    }

}

// Observer
interface ButtonListener {
    public void onClick();
}

class ObservableButton {

    private List<ButtonListener> listeners = new ArrayList<>();

    public void subscribe(ButtonListener listener) {
        listeners.add(listener);
    }

    protected void handle() {
        listeners.forEach(ButtonListener::onClick);
    }

}

// Observable
class Tela extends ObservableButton {

    private JButton button;
    private ButtonListener listener;

    public Tela() {
        button = new JButton();
        button.addActionListener(l -> handle());
    }

    public JButton getButton() {
        return button;
    }
}