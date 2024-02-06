import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int input, result = 0;
        Scanner in = new Scanner(System.in);
        try {
            input = in.nextInt();
            while (input > 0) {
                result += input % 10;
                input /= 10;
            }
            System.out.println(result);
        } catch (Exception ex1) {
            System.out.println("error");
        }
        in.close();
    }
}