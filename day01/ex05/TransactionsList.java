package ex05;

import java.util.UUID;
public interface TransactionsList {
    void addTransaction(Transaction newTransact);
    void removeTransactionByUUID(UUID temp) throws TransactionNotFoundException;
    Transaction[] transformIntoArray();
}
