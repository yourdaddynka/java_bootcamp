package edu.school21.chat.models.user;

public class UserIdGenerator {
    private int id = 1;
    private static UserIdGenerator instance;

    private UserIdGenerator() {
    }

    public static UserIdGenerator getInstance(){
        if(instance == null){
            instance = new UserIdGenerator();
        }
        return instance;
    }
    public int generateId(){
        return id++;
    }
}
