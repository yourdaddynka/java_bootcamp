package ex02;
public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private static int idCount = 1;

    private UserIdsGenerator() {
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId() {
        return idCount++;
    }
}
