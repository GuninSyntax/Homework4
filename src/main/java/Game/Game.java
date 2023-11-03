package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        List<Integer> playground = initPlayground();
        startGame(playground);
    }

    static List<Integer> initPlayground() {
        Random random = new Random();
        List<Integer> playground = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            playground.add(random.nextInt(10));
        }
        return playground;
    }

    static void startGame(List<Integer> playground) {
        printGameRules();
        boolean ready = readyToGame();
        if (ready) {
            System.out.println("\n*************************");
            System.out.println("*******  НАЧНЕМ! ********");
            System.out.println("*************************\n");
            gameProcess(playground);
        } else {
            System.out.println("Удачи, увидимся в следующий раз!");
        }
    }

    static void printGameRules() {
        System.out.println("*************************");
        System.out.println("Добро пожаловать в игру!");
        System.out.println("*************************");
        System.out.println();
        System.out.println("*************************");
        System.out.println("ПРАВИЛА ИГРЫ:");
        System.out.println("1. Дана последовательность целых чисел (от 0 до 9)");
        System.out.println("2. Вы можете вычеркнуть (убрать из последовательности) два рядом стоящих числа");
        System.out.println("3. Выбранные числа должны быть ОДИНАКОВЫМИ или В СУММЕ ДАЮТ 9");
        System.out.println("4. За каждое вычеркивание 2-х рядом стоящих чисел вы получие 1 очко!");
        System.out.println("5. Игра заканчивается, когда не останется возможных комбинаций или чисел не останется");
        System.out.println("*************************");
        System.out.println();
        System.out.println("Приятной игры!");
        System.out.println();
    }

    static boolean readyToGame() {
        Scanner scanner = new Scanner(System.in);
        boolean result = false;
        System.out.print("Вы готовы начать? (да/нет): ");
        result = scanner.nextLine().equalsIgnoreCase("да") ? true : false;
        return result;
    }

    static void gameProcess(List<Integer> playground) {
        int resultScore = 0;
        boolean endThisGameEmpty = false;
        boolean endThisGameIdentical = false;
        boolean endThisGameSumNine = false;

        while (true) {
            if (checkIsEmpty(playground)) endThisGameEmpty = true;
            if (!checkIsIdenticalNumbers(playground)) endThisGameIdentical = true;
            if (!checkSumIsNine(playground)) endThisGameSumNine = true;
            if (endThisGameEmpty || (endThisGameIdentical && endThisGameSumNine)) {
                endGame(resultScore);
                printReasonsForEndingTheGame(playground);
                break;
            }
            printPlayground(playground);
            int number1 = getUserNumber();
            int number2 = getUserNumber();
            resultScore += checkArray(playground, number1, number2);
        }
    }

    static int getUserNumber() {
        Scanner scanner = new Scanner(System.in);
        int number;
        System.out.print("Введите ЦЕЛОЕ число: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Введите корректное целое число!: ");
        }
        number = scanner.nextInt();
        return number;
    }

    static int checkArray(List<Integer> playground, int number1, int number2) {
        int result = 0;
        if (playground.contains(number1) && playground.contains(number2)) {
            if (number1 == number2) {
                result = checkIsIdenticalNumbersPlayer(playground, number1, number2);
            } else result = checkSumNineNumbersPlayer(playground, number1, number2);
        } else System.out.println("\nОдного или двух указанных чисел нет в массиве!\n");
        return result;
    }

    static int checkIsIdenticalNumbersPlayer(List<Integer> playground, int number1, int number2) {
        int result = 0;
        for (int i = 1; i < playground.size(); i++) {
            for (int j = i - 1; j < i; j++) {
                if ((playground.get(i) == number1 || playground.get(i) == number2) &&
                        (playground.get(j) == number1 || playground.get(j) == number2)) {
                    if (playground.get(i).equals(playground.get(j))) {
                        result = 1;
                        playground.remove(i);
                        playground.remove(j);
                        System.out.println("\n*************************");
                        System.out.println("Отлично! Вы нашли 2 одинаковых числа, которые стоят рядом!");
                        System.out.println("*************************\n");
                        break;
                    }
                }
            }
        }
        return result;
    }

    static int checkSumNineNumbersPlayer(List<Integer> playground, int number1, int number2) {
        int result = 0;
        for (int i = 1; i < playground.size(); i++) {
            for (int j = i - 1; j < i; j++) {
                if ((playground.get(i) == number1 || playground.get(i) == number2) &&
                        playground.get(j) == number1 || playground.get(j) == number2) {
                    if (playground.get(i) + playground.get(j) == 9) {
                        result = 1;
                        playground.remove(i);
                        playground.remove(j);
                        System.out.println("\n*************************");
                        System.out.println("Отлично! Вы нашли числа, которые дают в сумме 9!");
                        System.out.println("*************************\n");
                        break;
                    }
                }
            }
        }
        return result;
    }

    static boolean checkIsEmpty(List<Integer> playground) {
        return playground.isEmpty();
    }

    static boolean checkIsIdenticalNumbers(List<Integer> playground) {
        boolean result = false;
        for (int i = 1; i < playground.size(); i++) {
            for (int j = i - 1; j < i; j++) {
                if (playground.get(i).equals(playground.get(j))) result = true;
            }
        }
        return result;
    }

    static boolean checkSumIsNine(List<Integer> playground) {
        boolean result = false;
        for (int i = 1; i < playground.size(); i++) {
            for (int j = i - 1; j < i; j++) {
                if (playground.get(i) + playground.get(j) == 9) result = true;
            }
        }
        return result;
    }

    static void printPlayground(List<Integer> playground) {
        System.out.print("На данный момент у нас есть следующие числа: ");
        System.out.println(playground);
    }

    static void endGame(int resultScore) {
        System.out.println("*************************");
        System.out.println("ИГРА ОКОНЧЕНА!");
        System.out.println("*************************");
        System.out.println();
        System.out.println("Ваш счет: " + resultScore);
    }

    static void printReasonsForEndingTheGame(List<Integer> playground) {
        String reasons;
        if (checkIsEmpty(playground)) {
            reasons = " чисел больше не осталось...";
        } else if (!checkSumIsNine(playground)) {
            reasons = " числа больше не дают сумму 9";
        } else {
            reasons = " одинаковых чисел находящихся рядом больше не осталось";
        }
        System.out.println("Причина окончания игры -" + reasons);
        System.out.println("Игровое поле после окончания игры: " + playground);
    }
}
