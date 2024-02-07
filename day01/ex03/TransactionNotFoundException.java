package ex03;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String err){
        super(err);
    }
}
