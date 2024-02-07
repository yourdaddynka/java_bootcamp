package ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        private Node previous;
        private Node next;
        public Transaction transaction;

        public Node(Transaction temp) {
            setPrev(null);
            setNext(null);
            setTransact(temp);
        }

        private void setPrev(Node temp) {
            this.previous = temp;
        }

        private void setNext(Node temp) {
            this.next = temp;
        }

        private void setTransact(Transaction temp) {
            this.transaction = temp;
        }
    }

    private Node head;
    private Node tail;

    private int listIterator = 0;

    public int getListSize() {
        return listIterator;
    }

    public TransactionsLinkedList() {
        this.head = null;
        this.tail = null;
        setHead(null);
        setTeil(null);
        listIterator = 0;
    }

    private void setHead(Node temp) {
        this.head = temp;
    }

    private void setTeil(Node temp) {
        this.tail = temp;
    }

    @Override
    public void addTransaction(Transaction newTransact) {
        Node temp = new Node(newTransact);
        if (head == null) {
            setHead(temp);
            setTeil(temp);
        } else {
            tail.next = temp;
            temp.previous = tail;
            setTeil(temp);
        }
        listIterator++;
    }

    @Override
    public void removeTransactionByUUID(UUID id) throws TransactionNotFoundException {
        Node temporary = head;
        while (temporary != null) {
            if (temporary.transaction.getIdentifier().equals(id)) {
                if (temporary.previous == null) {
                    setHead(temporary.next);
                } else {
                    temporary.previous.next = temporary.next;
                }
                if (temporary.next == null) {
                    setTeil(temporary.previous);
                } else {
                    temporary.next.previous = temporary.previous;
                }
                listIterator--;
                return;
            }
            temporary = temporary.next;
        }
        throw new TransactionNotFoundException("The transaction could not be deleted");
    }

    @Override
    public Transaction[] transformIntoArray() {
        if (getListSize() > 0) {
            Transaction[] transactions = new Transaction[getListSize()];
            Node current = head;
            int i = 0;
            while (current != null) {
                transactions[i] = current.transaction;
                current = current.next;
                i++;
            }
            return transactions;
        } else {
            return null;
        }
    }
}
