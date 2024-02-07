package ex05;

public class User {
    private String Name_ = "Undefined";
    private int Identifier_;
    private int UBalance_ = 0;
    private TransactionsLinkedList transactionsList = new TransactionsLinkedList();

    public User() {
        setIdentifier();
    }

    public User(String name) {
        setName(name);
        setIdentifier();
    }

    public User(String name, int balance) {
        setName(name);
        if (checkBalance(balance)) {
            setUBalance(balance);
        } else
            setUBalance(0);
        setIdentifier();
    }

    public String getName() {
        return this.Name_;
    }

    public int getIdentifier() {
        return this.Identifier_;
    }

    public int getUBalance() {
        return this.UBalance_;
    }

    public void setName(String name) {
        this.Name_ = name;
    }

    public void setIdentifier() {
        this.Identifier_ = UserIdsGenerator.getInstance().generateId();
    }

    public void setUBalance(int newBalance) {
        this.UBalance_ = newBalance;
    }

    public void Print() {
        System.out.println("Name = " + getName() + "; ID = " + getIdentifier() + "; UBalance = " + getUBalance());

    }
    public TransactionsLinkedList getTransactionsList(){return this.transactionsList;}



    private boolean checkBalance(int balance) {
        return balance >= 0;
    }
}
