package ex05;

public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String err){
        super(err);
    }

}
