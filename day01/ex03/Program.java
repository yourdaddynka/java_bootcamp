package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsLinkedList list = new TransactionsLinkedList();
        String name = "User";
        int balance = 100;
        for (int i = 0; i < 10; i++) {
            User sender = new User(name + i, balance);
            User recipient = new User(name + (i + 1), balance);
            Transaction transaction = new Transaction(sender, recipient, Transaction.CategoryVar.DEBITS, 100);
            list.addTransaction(transaction);
            balance += 100;
        }
        for (int i = 0; i < 10; i++) {
            User sender = new User(name + i, balance);
            User recipient = new User(name + (i + 1), balance);
            Transaction transaction = new Transaction(sender, recipient, Transaction.CategoryVar.CREDITS, -100);
            list.addTransaction(transaction);
            balance += 100;
        }
        System.out.println("Size: " + list.getListSize());
        System.out.println("Invalid transaction: ");
        try {
            list.removeTransactionByUUID(UUID.randomUUID());
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        System.out.println("Length of the array: " + list.transformIntoArray().length);
        for (Transaction item : list.transformIntoArray()) {
            item.Print();
        }
    }
}
