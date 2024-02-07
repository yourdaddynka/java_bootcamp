package ex02;
public interface UsersList {
    public void addUser(User newUser);

    public User RetrieveUserId(int id) throws Exception;

    public User RetrieveUserIndex(int index) throws Exception;

    public int RetrieveNumberUsers();
}
