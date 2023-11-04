package Ternary;

public class Ternary {
    public static String convertNumber(int number) {
        return number == 0 ? "zero" : (number > 0 ? (number % 7 < 4 ? "positive-m" : "positive-p") :
                                                     (number % 7 > -4 ? "negative-m" : "negative-p"));
    }

    public static void main(String[] args) {
        System.out.println(convertNumber(-2));
    }
}
