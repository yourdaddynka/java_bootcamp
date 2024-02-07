package ex01;

public class Program {
    public static void main(String[] args) {
        User one = new User("Rik", 1000);
        User two = new User("Bob", 500);
        User three = new User("john", 100);
        User four = new User();
        User five = new User("Mike");
        System.out.println("one.getIdentifier() = " + one.getIdentifier());
        System.out.println("two.getIdentifier() = " + two.getIdentifier());
        System.out.println("three.getIdentifier() = " + three.getIdentifier());
        System.out.println("four.getIdentifier() = " + four.getIdentifier());
        System.out.println("five.getIdentifier() = " + five.getIdentifier());
    }
}
