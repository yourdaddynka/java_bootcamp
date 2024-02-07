package ex04;

public class UsersArrayList implements UsersList {
    private User[] array = new User[10];

    public int getArraySize() {
        return array.length;
    }

    public int getSize() {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        return count;
    }

    private void resizeArray() {
        User[] newArray = new User[getArraySize() * 2];
        for (int i = 0; i < getSize(); i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    @Override
    public void addUser(User newUser) {
        if (getSize() == getArraySize()) {
            resizeArray();
        } else {
            this.array[getSize()] = newUser;
        }
    }

    @Override
    public User RetrieveUserId(int id) throws Exception {
        for (int i = 0; i < this.getSize(); i++) {
            if (array[i].getIdentifier() == id) {
                return array[i];
            }
        }
        throw new Exception("User id incorrect");
    }

    @Override
    public User RetrieveUserIndex(int index) throws Exception {
        if (index < this.getSize() && index >= 0) {
            return array[index];
        }
        throw new Exception("User index incorrect");
    }

    @Override
    public int RetrieveNumberUsers() {
        return getSize();
    }
}
