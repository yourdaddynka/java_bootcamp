package edu.school21.chat.models.message;

public class MessageIdGenerator {
    private int id = 1;
    private static MessageIdGenerator instance;

    private MessageIdGenerator() {
    }

    public static MessageIdGenerator getInstance(){
        if(instance == null){
            instance = new MessageIdGenerator();
        }
        return instance;
    }
    public int generateId(){
        return id++;
    }
}
