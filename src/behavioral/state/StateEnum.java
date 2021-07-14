package behavioral.state;


import java.util.*;

public class StateEnum {

    private static ComputerState state = ComputerState.OFF;

    private static final HashMap<ComputerState, List<Map.Entry<Trigger, ComputerState>>> rules = new HashMap<>();
    static {
        rules.put(ComputerState.ON, List.of(
                new AbstractMap.SimpleImmutableEntry<>(Trigger.POWER_OFF, ComputerState.OFF),
                new AbstractMap.SimpleImmutableEntry<>(Trigger.SUSPEND, ComputerState.SUSPENDED),
                new AbstractMap.SimpleImmutableEntry<>(Trigger.HIBERNATE, ComputerState.HIBERNATED)
        ));

        rules.put(ComputerState.OFF, List.of(
                new AbstractMap.SimpleImmutableEntry<>(Trigger.POWER_ON, ComputerState.ON)
        ));

        rules.put(ComputerState.SUSPENDED, List.of(
                new AbstractMap.SimpleImmutableEntry<>(Trigger.WAKE_UP, ComputerState.ON)
        ));

        rules.put(ComputerState.HIBERNATED, List.of(
                new AbstractMap.SimpleImmutableEntry<>(Trigger.WAKE_UP, ComputerState.ON)
        ));
    }

    public static void main(String[] args) {



        do {

            System.out.println("The computer is " + state);

            List<Map.Entry<Trigger, ComputerState>> rule = rules.get(state);

            for (int i = 0; i < rule.size(); i++) {
                Trigger trigger = rule.get(i).getKey();

                System.out.println(i + " - " + trigger);
            }

            Scanner sc = new Scanner(System.in);

            int choice = -1;
            boolean isOk = false;

            do {
                try {
                    choice = sc.nextInt();
                    isOk = choice < rule.size() && choice >= 0;
                } catch (Exception e) {
                }
            } while (!isOk);

            state = rule.get(choice).getValue();

        } while (state != ComputerState.OFF);

    }

}

enum ComputerState {
    ON,
    OFF,
    SUSPENDED,
    HIBERNATED,
}

enum Trigger {
    POWER_ON,
    POWER_OFF,
    SUSPEND,
    HIBERNATE,
    WAKE_UP,
}
