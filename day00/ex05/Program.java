import java.util.Scanner;

public class Program {

    public static void errorExit(Scanner in) {
        System.err.println("IllegalArgument");
        in.close();
        System.exit(-1);
    }

    private static void getStudents(Scanner in, String[] students) {
        int i = 0;
        System.out.print("Enter student name: ");
        while (i < 10) {
            String name = in.nextLine();
            if (name.equals(".")) {
                break;
            }
            if (name.length() > 10) {
                errorExit( in);
            }
            students[i] = name;
            i++;
        }
    }

    private static void getchart(Scanner in, int[][] chart) {
        int i = 0;
        System.out.print("Set chart: ");
        while (i < 10) {
            String studies = in.nextLine();
            if (studies.equals(".")) {
                break;
            }
            String[] parts = studies.split(" ");
            if (parts.length != 2) {
                errorExit( in);
            }
            int time = Integer.parseInt(parts[0]);
            if (time < 0 || time >= 5) {
                errorExit( in);
            }
            int day = getDay(parts[1]);
            if (day < 0 || day >= 7) {
                errorExit( in);
            }
            chart[time][day] = 1;
        }
    }

    private static int getDay(String dayOfWeek) {
        if (dayOfWeek.equals("MO")) {
            return 0;
        } else if (dayOfWeek.equals("TU")) {
            return 1;
        } else if (dayOfWeek.equals("WE")) {
            return 2;
        } else if (dayOfWeek.equals("TH")) {
            return 3;
        } else if (dayOfWeek.equals("FR")) {
            return 4;
        } else if (dayOfWeek.equals("ST")) {
            return 5;
        } else if (dayOfWeek.equals("SU")) {
            return 6;
        } else {
            return -1;
        }
    }

    private static void setMonthchart(boolean[][][] monthchart, int[][] chart) {
        int dayOfWeek = getStartingDayOfWeek("TU");
        for (int i = 0; i < 30; i++) {
            for (int k = 0; k < 5; k++) {
                if (chart[k][dayOfWeek] == 1) {
                    monthchart[i][dayOfWeek][k] = true;
                } else {
                    for (int j = 0; j < 7; j++) {
                        monthchart[i][j][k] = false;
                    }
                }
            }
            dayOfWeek = (dayOfWeek + 1) % 7;
        }
    }

    private static int getStartingDayOfWeek(String startingDay) {
        return getDay(startingDay);
    }

    public static int getStudentIndex(String[] students, String student) {
        for (int i = 0; i < students.length; i++) {
            if (student.equals(students[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void setAttendance(Scanner in, String[] students, int[][][][] attendance) {
        int studentIndex;
        System.out.print("Set attendance: ");
        while (true) {
            String input = in.nextLine();
            if (input.equals(".")) {
                break;
            }
            String[] tokens = input.split(" ");
            if (tokens.length != 4) {
                errorExit( in);
            }
            String name = tokens[0];
            studentIndex = getStudentIndex(students, name);
            if (studentIndex == -1) {
                errorExit( in);
            }
            int time = Integer.parseInt(tokens[1]);
            if (time < 0 || time >= 5) {
                errorExit( in);
            }

            int day = Integer.parseInt(tokens[2]);
            if (day < 0 || day > 30) {
                errorExit( in);
            }

            String attendanceState = tokens[3];
            if (attendanceState.equals("HERE")) {
                attendance[studentIndex][time][day - 1][0] = 1;
            } else if (attendanceState.equals("NOT_HERE")) {
                attendance[studentIndex][time][day - 1][0] = -1;
            } else {
                errorExit( in);
            }
        }
    }

    private static void printMonthchart(boolean[][][] monthchart) {
        String[] weekDays = {"MO", "TU", "WE", "TH", "FR", "ST", "SU"};
        String[] times = {"1:00", "2:00", "3:00", "4:00", "5:00"};

        System.out.format("%10s", "");
        for (int day = 0; day < 30; day++) {
            for (int hour = 0; hour < 5; hour++) {
                for (int weekDay = 0; weekDay < 7; weekDay++) {
                    if (monthchart[day][weekDay][hour]) {
                        System.out.format("%-5s %2s %02d|", times[hour - 1], weekDays[weekDay], (day + 1));
                    }
                }
            }
        }
        System.out.println();
    }

    private static void printAttendance(String[] students, int[][][][] attendance, boolean[][][] monthchart) {
        for (int studentIndex = 0; studentIndex < students.length && students[studentIndex] != null; studentIndex++) {
            if (students[studentIndex] == null) {
                continue;
            }
            System.out.format("%-10s", students[studentIndex]);
            for (int day = 0; day < 30; day++) {
                for (int weekDay = 0; weekDay < 7; weekDay++) {
                    for (int hour = 0; hour < 5; hour++) {
                        if (monthchart[day][weekDay][hour]) {
                            int state = attendance[studentIndex][hour][day][0];
                            if (state == 1) {
                                System.out.format("%12s", "1|");
                            } else if (state == -1) {
                                System.out.format("%12s", "-1|");
                            } else {
                                System.out.format("%12s", "|");
                            }
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        String[] students = new String[10];
        int[][] chart = new int[5][7];
        boolean[][][] monthchart = new boolean[30][7][5];
        int[][][][] attendance = new int[students.length][5][30][1];

        getStudents(in, students);
        getchart(in, chart);
        setMonthchart(monthchart, chart);
        setAttendance(in, students, attendance);
        printMonthchart(monthchart);
        printAttendance(students, attendance, monthchart);
    }

}