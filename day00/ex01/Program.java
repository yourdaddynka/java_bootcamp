import java.util.Scanner;
import static java.lang.System.exit;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean illegal = false;
        if (in.hasNextInt()) {
            int num = in.nextInt();
            if (num > 0 && num != 1) {
                int count = 0;
                boolean flag = true;
                for (int i = 2; i*i <= num; i++, ++count) {
                    if (num % i == 0) {
                        flag = false;
                        break;
                    }
                }
                System.out.printf(flag + " %d", ++count);
            } else illegal = true;
        } else illegal = true;
        if (illegal) {
            System.err.println("Illegal Argument");
            in.close();
            exit(-1);
        }
        in.close();
    }
}