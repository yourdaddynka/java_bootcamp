import java.util.Arrays;
import java.util.Scanner;

public class Program {

    static void printed(int[][] popularChar) {
        int max = popularChar[0][1];
        if (max == 0) {
            System.out.println();
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (popularChar[i][1] == max) System.out.print(max + "\t");
        }
        System.out.println();
        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (popularChar[j][1] * 10 / max >= i) System.out.print("#\t");
                if (popularChar[j][1] * 10 / max == i - 1) {
                    if (popularChar[j][1] != 0) System.out.print(popularChar[j][1] + "\t");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 10 && popularChar[i][0] != 0; i++) {
            System.out.print((char) popularChar[i][0] + "\t");
        }
    }

    static char[] inpStrToCharArr(Scanner in) {
        return in.nextLine().replaceAll("[^a-zA-Zа-яА-Я]", "").toUpperCase().toCharArray();
    }

    static int[][] PopChar(char[] inputCharArr, Scanner in) {
        int[][] countSymbols = new int[65536][2];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < inputCharArr.length; i++) {
            int index = inputCharArr[i];
            if (inputCharArr[i] > 127) {
                index = inputCharArr[i] - 1040;
            }
            countSymbols[index][0] = inputCharArr[i];
            countSymbols[index][1]++;
        }
        Arrays.sort(countSymbols, (a, b) -> Integer.compare(b[1], a[1]));
        int[][] popularChar = new int[10][2];
        for (int i = 0; i < countSymbols.length && i < 10; i++) {
            if (countSymbols[i][1] > 0) {
                popularChar[i][0] = countSymbols[i][0];
                popularChar[i][1] = countSymbols[i][1];
            }
            if (countSymbols[i][1] > 999) {
                System.err.println("theIllegalArgument");
                in.close();
                System.exit(-1);
            }
        }
        return popularChar;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] inputCharArr = inpStrToCharArr(in);
        int[][] popularChar = PopChar(inputCharArr, in);
        printed(popularChar);
        in.close();
    }
}