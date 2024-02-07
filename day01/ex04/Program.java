package ex04;

public class Program {
    public static void main(String[] args) throws Exception {
        TransactionsService service = new TransactionsService();
        User user1 = new User("John", 2000);
        User user2 = new User("Simon", 6000);
        User user3 = new User("Sue", 6000);
        User user4 = new User("Jack", 6000);
        service.addUser(user1);
        service.addUser(user2);
        service.addUser(user3);
        service.addUser(user4);
        System.out.println("John Balance = " + user1.getUBalance());
        System.out.println("Simon Balance  = " + user2.getUBalance());
        service.performingTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 300);
        service.performingTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 300);
        service.performingTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 300);
        System.out.println("John Balance after = " + user1.getUBalance());
        System.out.println("Simon Balance after = " + user2.getUBalance());

        Transaction[] user1Transactions = service.retrievingTransfersSpecificUser(user1);
        System.out.println("transact = " + service.retrievingTransfersSpecificUser(user1).length);
        for (int i = 0; i < user1Transactions.length; i++) {
            user1Transactions[i].Print();
        }
        service.removeTransaction(user1Transactions[0].getIdentifier(), user1.getIdentifier());
        System.out.println("remove transact = " + service.retrievingTransfersSpecificUser(user1).length);
        Transaction[] user2Transactions = service.retrievingTransfersSpecificUser(user1);
        for (int i = 0; i < user2Transactions.length; i++) {
            user1Transactions[i].Print();
        }
    }
}
