package game;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String tmp = sc.nextLine();
//        int lvl = isInteger(tmp) ? Integer.parseInt(tmp) : 1;
//        int maxNumber = lvl == 3 ? 1000 : lvl == 2 ? 100 : 10;
//        System.out.println("from 1 to " + maxNumber);
//        rightPrice(maxNumber);
//    }
    static String moreOrLess(int numberToGuess, int guessNumber) {
        if (guessNumber > numberToGuess) {
            return "<" + guessNumber;
        } else if (guessNumber < numberToGuess) {
            return guessNumber + ">";
        }
        return "GREAT!";
    }
    static boolean isInteger (String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    static void rightPrice(int max) {
        Scanner sc = new Scanner(System.in);
        Random rng = new Random();
        int numberToGuess = rng.nextInt(max);
        System.out.println("?");
        String tmp = sc.nextLine();
        int guessNumber = isInteger(tmp) ? Integer.parseInt(tmp) : -1;
        int count = 9;
        while (true) {
            if (count == 0) {
                System.out.printf("SORRY!\nTHE\nNUMBER\nWAS\n", numberToGuess);
                break;
            } else if (guessNumber == -1) {
                System.out.printf("Veuillez entrer un nombre valide: ", numberToGuess);
                tmp = sc.nextLine();
                guessNumber = isInteger(tmp) ? Integer.parseInt(tmp) : -1;
            } else if (numberToGuess != guessNumber) {
                System.out.println(moreOrLess(numberToGuess, guessNumber));
                System.out.printf("%s essais!", count);
                System.out.println("entrez un nombre: ");
                tmp = sc.nextLine();
                guessNumber = isInteger(tmp) ? Integer.parseInt(tmp) : -1;
                count--;
            } else {
                System.out.println(moreOrLess(numberToGuess, guessNumber));
                break;
            }
        }
    }
}