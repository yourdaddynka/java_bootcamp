package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        private Node previous, next;
        public Transaction temp;

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
            this.temp = temp;
        }
    }

    private Node head;
    private Node teil;
    private static int listIterator = 0;

    static int getListSize() {
        return listIterator;
    }

    public TransactionsLinkedList() {
        setHead(null);
        setTeil(null);
        this.listIterator = 0;
    }

    private void setHead(Node temp) {
        this.head = temp;
    }

    private void setTeil(Node temp) {
        this.teil = temp;
    }

    @Override
    public void addTransaction(Transaction newTransact) {
        Node temp = new Node(newTransact);
        if (head == null) {
            setHead(temp);
            setTeil(temp);
        } else {
            teil.next = temp;
            temp.previous = teil;
            setTeil(temp);
        }
        listIterator++;
    }

    @Override
    public void removeTransactionByUUID(UUID temp) throws TransactionNotFoundException {
        Node temporary = head;
        while (temporary != null) {
            if (temporary.temp.getIdentifier().equals(temp)) {
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
            Transaction[] temp = new Transaction[getListSize()];
            Node temporary = head;
            for (int i = 0; i < getListSize(); i++) {
                temp[i] = temporary.temp;
                temporary = temporary.next;
            }
            return temp;
        }
        return null;
    }
}
