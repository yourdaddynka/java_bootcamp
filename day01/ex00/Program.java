package ex00;

public class Program {
    public static void main(String[] args) {
        User one = new User("Rik", 1000);
        User two = new User("Bob", 500);

        System.out.println("one.getIdentifier() = " + one.getIdentifier());
        System.out.println("two.getIdentifier() = " + two.getIdentifier());

        Transaction first = new Transaction(one, two, 100);
        first.Print();
        one.setUBalance(one.getUBalance() - first.getAmount());
        two.setUBalance(two.getUBalance() + first.getAmount());
        System.out.println(one.getName() + ": " + one.getUBalance());
        System.out.println(two.getName() + ": " + two.getUBalance());
        Transaction second = new Transaction(two, one, 200);
        second.Print();
        two.setUBalance(two.getUBalance() - second.getAmount());
        one.setUBalance(one.getUBalance() + second.getAmount());
        System.out.println(one.getName() + ": " + one.getUBalance());
        System.out.println(two.getName() + ": " + two.getUBalance());
    }
}
