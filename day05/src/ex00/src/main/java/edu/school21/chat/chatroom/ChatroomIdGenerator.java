package edu.school21.chat.chatroom;

public class ChatroomIdGenerator {
    private int id = 1;
    private static ChatroomIdGenerator instance;

    private ChatroomIdGenerator() {
    }

    public static ChatroomIdGenerator getInstance(){
        if(instance == null){
            instance = new ChatroomIdGenerator();
        }
        return instance;
    }
    public int generateId(){
        return id++;
    }
}
