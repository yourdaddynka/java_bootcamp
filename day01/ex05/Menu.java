package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService exempl;
    private Scanner in;

    public Menu() {
        exempl = new TransactionsService();
        in = new Scanner(System.in);
    }

    public void run(boolean flag) {
        while (true) {
            if (flag) {
                printMenu(true);
                parseInp(true);
            } else {
                printMenu(false);
                parseInp(false);
            }
            printLine();
        }
    }


    private void parseInp(boolean flag) {
        if (in.hasNextInt()) {
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    viewUserBalances();
                    break;
                case 3:
                    performTransfer();
                    break;
                case 4:
                    viewAllTransactionsSpecificUser();
                    break;
                case 5:
                    if (!flag) {
                        finishExecution();
                    }
                    removeTransferId();
//                    removeTransferById();
                    break;
                case 6:
                    checkTransferValidity();
                    break;
                case 7:
                    finishExecution();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
                    break;
            }
        } else {
            System.out.println("Invalid choice. Please enter a valid number.");
            in.next();
        }
    }


    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String[] args = in.nextLine().split(" ");
        if (args.length == 2) {
            String username = args[0];
            int balance = Integer.parseInt(args[1]);
            User temp = new User(username, balance);
            exempl.addUser(temp);
            System.out.println("User with id = " + temp.getIdentifier() + " is added");
        } else
            System.out.println("Invalid input, please select operation again");
    }

    private void viewUserBalances() {
        System.out.println("Enter a user ID");
        try {
            if (in.hasNextInt()) {
                int usId = in.nextInt();
                System.out.println(exempl.getUser(usId).getName() + " - " + exempl.retrievingUserBalance(usId));
            }

        } catch (Exception e) {
            System.out.println("User list is empty");
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String[] args = in.nextLine().split(" ");
        try {
            if (args.length == 3) {
                int senderId = Integer.parseInt(args[0]);
                int recipientId = Integer.parseInt(args[1]);
                int amount = Integer.parseInt(args[2]);
                exempl.performingTransferTransaction(senderId, recipientId, amount);
                System.out.println("The transfer is completed");
            } else
                System.out.println("Incorrect input");
        } catch (Exception e) {
            System.out.println("Invalid input, please select operation again");
        }
    }

    private void viewAllTransactionsSpecificUser() {
        System.out.println("Enter a user ID");
        if (in.hasNextInt()) {
            int usId = in.nextInt();
            try {
                Transaction[] temp = exempl.retrievingTransfersSpecificUser(exempl.getUser(usId));
                for (int i = 0; i < temp.length; i++) {
                    User recipient = temp[i].getRecipient();
                    User sender = temp[i].getSender();
                    String op = "+";
                    if (temp[i].getCategory() == Transaction.CategoryVar.DEBITS) op = "-";
                    System.out.println("To " + sender.getName() + "(id = " + sender.getIdentifier() + ") " + op + temp[i].getAmount() + " with id = " + temp[i].getIdentifier());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void removeTransferId() {
        System.out.println("Enter a user ID and a transfer ID");
        String argc[] = in.nextLine().split(" ");
        if (argc.length == 2) {
            try {
                int usId = Integer.parseInt(argc[0]);
                UUID userUuid = UUID.fromString(argc[1]);
                Transaction[] temp = exempl.retrievingTransfersSpecificUser(exempl.getUser(usId));
                if (temp == null) {
                    throw new RuntimeException("Transaction with id = " + userUuid + " not found");
                }
                Transaction transaction = null;
                for (Transaction item : temp) {
                    if (item.getIdentifier().equals(userUuid)) {
                        transaction = item;
                    }
                }
                if (transaction == null) {
                    throw new RuntimeException("Transaction with id = " + userUuid + " not found");
                }
                exempl.removeTransaction(userUuid, usId);
                User user = (transaction.getCategory() == Transaction.CategoryVar.DEBITS) ?
                        transaction.getSender() :
                        transaction.getRecipient();
                String category = (transaction.getCategory() == Transaction.CategoryVar.DEBITS) ?
                        "From " :
                        "To ";
                System.out.println("Transfer " + category + " " + user.getName() +
                        "(id = " + user.getIdentifier() + ") " + transaction.getAmount() + " removed");
            } catch (Exception e) {
                System.out.println("Transfer list is empty");
            }
        } else
            System.out.println("Incorrect input");
    }


    private void checkTransferValidity() {
        System.out.println("Check results:");
        Transaction[] transactions = exempl.checkValidityOfTransactions();
        if (transactions != null) {
            for (Transaction item : transactions) {
                User userHolder = getUserHolder(item);
                User userSender = (item.getCategory() == Transaction.CategoryVar.DEBITS) ?
                        item.getSender() :
                        item.getRecipient();
                UUID transactionId = item.getIdentifier();
                int amount = item.getAmount();
                System.out.println(userHolder.getName() + "(id = " + userHolder.getIdentifier() +
                        ") has an unacknowledged transfer id = " + transactionId + " from " +
                        userSender.getName() + "(id = " + userSender.getIdentifier() +
                        ") for " + amount);
            }
        } else System.out.println("There are no unpaired transactions");
    }

    private User getUserHolder(Transaction transaction) {
        try {
            UsersArrayList listUsers = exempl.getUserList();
            for (int i = 0; i < listUsers.getSize(); i++) {
                Transaction[] listTrans = listUsers.RetrieveUserIndex(i).getTransactionsList().transformIntoArray();
                for (int j = 0; listTrans != null && j < listTrans.length; j++) {
                    if (listTrans[j].getIdentifier().equals(transaction.getIdentifier())) {
                        return listUsers.RetrieveUserIndex(i);
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void finishExecution() {
        in.close();
        System.exit(0);
    }

    private void printMenu(boolean flag) {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (!flag) {
            System.out.println("5. Finish execution");
            return;
        }
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
    }

    private void printLine() {
        System.out.println("---------------------------------------------------------");

    }
}