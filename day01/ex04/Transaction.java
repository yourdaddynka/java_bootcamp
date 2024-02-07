package ex04;

import java.util.UUID;

public class Transaction {
    public enum CategoryVar {
        DEBITS,
        CREDITS
    }

    private UUID Identifier_;
    private User Recipient_;
    private User Sender_;
    private CategoryVar Category_;
    private int Amount_ = 0;

    Transaction(User Rec, User Send, CategoryVar category, int amount) {
        if (CheckPossible(Rec, amount)) {
            this.Identifier_ = UUID.randomUUID();
            setRecipient(Rec);
            setSender(Send);
            setCategory(category);
            setAmount(amount);
        }
    }

    public UUID getIdentifier() {
        return this.Identifier_;
    }

    public User getRecipient() {
        return this.Recipient_;
    }

    public User getSender() {
        return this.Sender_;
    }

    public CategoryVar getCategory() {
        return this.Category_;
    }

    public int getAmount() {
        return this.Amount_;
    }

    public void setRecipient(User newRecip) {
        this.Recipient_ = newRecip;
    }

    public void setSender(User newSend) {
        this.Sender_ = newSend;
    }

    public void seIdentifier(UUID newUid) {
        this.Identifier_ = newUid;
    }

    public void setCategory(CategoryVar newCategory) {
        this.Category_ = newCategory;
    }

    public void setAmount(int newAmount) {
        if (!(getCategory() == CategoryVar.CREDITS && newAmount > 0) || !(getCategory() == CategoryVar.DEBITS && newAmount < 0))
            this.Amount_ = newAmount;
    }

    public void Print() {
            System.out.println(getRecipient().getName() + " -> " + getSender().getName() + ", " + Amount_ + ", transaction: " + getIdentifier());
    }

    private boolean CheckPossible(User recipient, int amount) {
        return (recipient.getUBalance() >= 0) && (recipient.getUBalance() - amount >= 0);
    }

}
