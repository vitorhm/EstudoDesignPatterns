package behavioral;

public class Command {

    public static void main(String[] args) {

        Menu menu = new Menu();

        Action action = new Action(new PrimeiraOpcaoAction(menu));
        Action action1 = new Action(new SegundaOpcaoAction(menu));

        action.invoke();
        action1.invoke();
    }
}

interface ActionListener {
    public void invoke();
}

class PrimeiraOpcaoAction implements ActionListener {

    private final Menu menu;

    PrimeiraOpcaoAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void invoke() {
        menu.clickSegundaOpcao();
    }
}

class SegundaOpcaoAction implements ActionListener {

    private final Menu menu;

    SegundaOpcaoAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void invoke() {
        menu.clickSegundaOpcao();
    }
}

class Menu {

    public void clickPrimeiraOpcao() {

    }

    public void clickSegundaOpcao() {

    }

}

class Action {

    private final ActionListener actionListener;

    Action(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void invoke() {
        actionListener.invoke();
    }
}

