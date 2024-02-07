package ex00;

public class User {
    private static int count_id = 1;
    private String Name_ = "Undefined";
    private int Identifier_ = count_id;
    private int UBalance_ = 0;

    User() {
        count_id++;
    }

    User(String name) {
        Name_ = name;
        count_id++;
    }

    User(String name, int balance) {
        count_id++;
        Name_ = name;
        if (checkBalance(balance)) {
            UBalance_ = balance;
        } else UBalance_ = 0;
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

    public void setIdentifier(int newId) {
        this.Identifier_ = newId;
    }

    public void setUBalance(int newBalance) {
        this.UBalance_ = newBalance;
    }

    public void Print() {
        System.out.println("Name = " + Name_ + "; ID = " + Identifier_ + "; UBalance = " + UBalance_);

    }

    private boolean checkBalance(int balance) {
        return balance >= 0;
    }
}
