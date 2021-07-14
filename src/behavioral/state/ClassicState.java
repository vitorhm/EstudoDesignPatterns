package behavioral.state;

public class ClassicState {

    public static void main(String[] args) {

        InterruptorLuz interruptorLuz = new InterruptorLuz();
        interruptorLuz.on();
        interruptorLuz.off();
        interruptorLuz.off();

    }
}

class State {

    public void on(InterruptorLuz interruptor) {
        System.out.println("A luz já está ligada");
    }

    public void off(InterruptorLuz interruptor) {
        System.out.println("A luz já está desligada");
    }
}

class OnState extends State {

    @Override
    public void off(InterruptorLuz interruptor) {
        System.out.println("Luz desligada");
        interruptor.setState(new OffState());
    }
}

class OffState extends State {

    @Override
    public void on(InterruptorLuz interruptor) {
        System.out.println("Luz ligada");
        interruptor.setState(new OnState());
    }
}

class InterruptorLuz {

    private State state; // OffState --> OnState --> OffState

    public InterruptorLuz() {
        state = new OffState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void on() {
        state.on(this);
    }

    public void off() {
        state.off(this);
    }
}