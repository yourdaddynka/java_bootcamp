import java.util.Scanner;

import static java.lang.System.exit;

public class Program {
    static boolean isSimpleNumber(int thisNum) {
        int count = 0;
        for (int i = 1; i <= thisNum / 2; i++) {
            if (thisNum % i == 0) count++;
            else if (count > 1) break;
        }
        return count <= 1;
    }

    static void IncorectInput(Scanner in) {
        System.out.println("Incorrect input");
        in.close();
        exit(-1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNextInt()) {
            int this_num = 0;
            int num = in.nextInt();
            if (num < 2) {
                IncorectInput(in);
                break;
            } else if (num == 42) break;
            else {
                for (; num > 0; num /= 10) {
                    this_num += num % 10;
                }
                if (isSimpleNumber(this_num)) count++;
            }
        }
        System.out.printf("Count of coffee-request - %d", count);
        in.close();
    }
}