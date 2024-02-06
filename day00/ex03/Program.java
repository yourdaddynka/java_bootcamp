import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String result = "";
        for (int i = 1; i <= 18; i++) {
            String weekCheck = in.next();
            if(!stopLoop(weekCheck)){break;}
            int WeekCounts = in.nextInt();
            if (weekCheck.equals("Week") && i == WeekCounts) {
                int min = ParseMaxNum(in);
                result += stringBuilder(min, weekCheck, WeekCounts);
            }
            else acidentClosedJob(in);
        }
        System.out.print(result);
        closedJob(in);
    }

    static void acidentClosedJob(Scanner in) {
        in.close();
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    static void closedJob(Scanner in) {
        in.close();
    }

    static int ParseMaxNum(Scanner in) {
        int min = 9;
        try {
            int one = Integer.parseInt(in.next());
            int two = Integer.parseInt(in.next());
            int three = Integer.parseInt(in.next());
            int four = Integer.parseInt(in.next());
            int five = Integer.parseInt(in.next());
            if (min > one) min = one;
            if (min > two) min = two;
            if (min > three) min = three;
            if (min > four) min = four;
            if (min > five) min = five;
//            return min;
        } catch (NumberFormatException e) {
            acidentClosedJob(in);
        }
        return min;
    }

    static String stringBuilder(int min, String weekCheck, int WeekCounts) {
        String tempRes = "";
        for (int i = 0; i < min; i++) {
            tempRes += "=";
        }
        return (weekCheck + " " + WeekCounts + " " + tempRes + ">" + "\n");
    }

    static boolean stopLoop(String inp) {
        try {
            if (Integer.parseInt(inp) == 42) {
                return false;
            }
        } catch (NumberFormatException ignored) {
        }
        return true;
    }
}