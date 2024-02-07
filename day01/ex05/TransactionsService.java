
package ex05;

import java.util.UUID;

public class TransactionsService {
    private UsersArrayList userList;
    public UsersArrayList getUserList(){return this.userList;}
    TransactionsService() {
        userList = new UsersArrayList();
    }

    public void addUser(User newUser) {
        userList.addUser(newUser);
    }

    public int retrievingUserBalance(int id) throws Exception {
        return userList.RetrieveUserId(id).getUBalance();
    }

    public Transaction[] retrievingTransfersSpecificUser(User user) {

        return user.getTransactionsList().transformIntoArray();
    }

    public void removeTransaction(UUID transactId, int userId) throws Exception {
        getUser(userId).getTransactionsList().removeTransactionByUUID(transactId);
    }


    public User getUser(int userId) throws Exception {
        User temp = new User();
        for (int i = 0; i < this.userList.getSize(); i++) {
            temp = userList.RetrieveUserId(userId);
        }
        return temp;
    }

    public void performingTransferTransaction(int userId1, int userId2, int transactSum) throws Exception {
        User userOne = userList.RetrieveUserId(userId1);
        User userTwo = userList.RetrieveUserId(userId2);
        CheckPossible(userOne, transactSum);
        Transaction oneTrans = new Transaction(userOne, userTwo, Transaction.CategoryVar.DEBITS, transactSum);
        Transaction twoTrans = new Transaction(userTwo, userOne, Transaction.CategoryVar.CREDITS, -transactSum);
        userOne.getTransactionsList().addTransaction(oneTrans);
        userTwo.getTransactionsList().addTransaction(twoTrans);
        userList.RetrieveUserId(userId1).setUBalance(userOne.getUBalance() - oneTrans.getAmount());
        userList.RetrieveUserId(userId2).setUBalance(userTwo.getUBalance() + oneTrans.getAmount());
    }
/*
    public Transaction[] checkValidityTransactions() throws Exception {
        TransactionsList debitList = new TransactionsLinkedList();
        TransactionsList creditList = new TransactionsLinkedList();
        TransactionsList unpairedList = new TransactionsLinkedList();
        for (int i = 0; i < userList.getSize(); i++) {
            Transaction[] tempTransaction = userList.RetrieveUserIndex(i).getTransactionsList().transformIntoArray();
            for (int j = 0; j < tempTransaction.length; j++) {
                 if(tempTransaction[j].getCategory() == Transaction.CategoryVar.DEBITS){
                     debitList.addTransaction(tempTransaction[j]);
                 }
                 else{
                     creditList.addTransaction(tempTransaction[j]);
                 }
            }
        }
        Transaction[] debitArr = debitList.transformIntoArray();
        Transaction[] creditArr = debitList.transformIntoArray();
        for(int i = 0;i < debitArr.length;i++){
            boolean flag = false;
            for(int j = 0;i < creditArr.length;j++){
                if(debitArr[i].getIdentifier().equals(creditArr[j].getIdentifier())){
                    flag = true;break;
                }
            }
            if(!flag){
                unpairedList.addTransaction(debitArr[i]);
            }
        }
        for(int i = 0;i < creditArr.length;i++){
            boolean flag = false;
            for(int j = 0;i < debitArr.length;j++){
                if(creditArr[i].getIdentifier().equals(debitArr[j].getIdentifier())){
                    flag = true;break;
                }
            }
            if(!flag){
                unpairedList.addTransaction(creditArr[i]);
            }
        }
        return unpairedList.transformIntoArray();
    }*/

    public Transaction[] checkValidityOfTransactions() {
        TransactionsList transactionsList = getAllTransactions();
        TransactionsLinkedList result = new TransactionsLinkedList();
        Transaction[] arrayFirst = transactionsList.transformIntoArray();
        if (arrayFirst != null) {
            int sizeArray = arrayFirst.length;
            for (int i = 0; i < sizeArray; i++) {
                int count = 0;
                for (int j = 0; j < sizeArray; j++) {
                    if (arrayFirst[i].getIdentifier().equals(arrayFirst[j].getIdentifier())) {
                        count++;
                    }
                }
                if (count != 2) {
                    result.addTransaction(arrayFirst[i]);
                }
            }
        }
        return result.transformIntoArray();
    }
    private TransactionsList getAllTransactions() {
        TransactionsList list = new TransactionsLinkedList();
try {
    for (int i = 0; i < userList.RetrieveNumberUsers(); i++) {
        User user = userList.RetrieveUserIndex(i);
        if (user != null) {
            for (int j = 0; j < user.getTransactionsList().getListSize(); j++) {
                list.addTransaction(user.getTransactionsList().transformIntoArray()[j]);
            }
        }
    }
    return list;
} catch (Exception e) {
    throw new RuntimeException(e);
}
    }

    private void CheckPossible(User recipient, int amount) throws IllegalTransactionException {
        if (recipient.getUBalance() < 0 || recipient.getUBalance() - amount < 0) {
            throw new IllegalTransactionException("Insufficient funds");
        }
    }
}
