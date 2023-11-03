package Apple;

public class Apple {
    public static void printNumberOfApples(String name, int apples) {
        String[] declension = {"яблок", "яблоко", "яблока"};
        String result = null;
        int remainder = apples % 10;

        switch (remainder) {
            case 1:
                result = apples == 11 ? declension[0] : declension[1];
                break;
            case 2:
            case 3:
            case 4:
                result = apples > 10 && apples < 20 ? declension[0] : declension[2];
                break;
            default:
                result = declension[0];
                break;

        }

        System.out.println(name + " хранит у себя " + apples + " " +result);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            printNumberOfApples("Дмитрий", i);
        }
    }
}
