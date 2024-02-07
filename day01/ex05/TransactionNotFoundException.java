package ex05;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String err){
        super(err);
    }
}
