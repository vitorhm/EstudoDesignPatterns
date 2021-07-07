package behavioral;

public class Iterator {

    public static void main(String[] args) {

        Names names = new Names();

        while (names.hasNext()) {
            System.out.println(names.next());
        }

    }
}

class Names implements java.util.Iterator<String> {

    private String[] names = new String[] {"Vitor", "Lorrant", "Carlos", "João", "Odair"};
    private int currentIndex = 0;

    @Override
    public boolean hasNext() {
        return currentIndex < names.length;
    }

    @Override
    public String next() {
        if (hasNext()) {
            return names[currentIndex++];
        }

        return null;
    }
}
