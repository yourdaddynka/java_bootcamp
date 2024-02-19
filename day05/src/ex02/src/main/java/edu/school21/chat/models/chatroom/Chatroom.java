package edu.school21.chat.models.chatroom;

import edu.school21.chat.models.message.Message;

import java.util.ArrayList;

public class Chatroom {
    private int id;
    private String name;
    private int userId;
    private ArrayList<Message> messageList;

    public Chatroom(String name, int userId) {
        this.id = ChatroomIdGenerator.getInstance().generateId();
        this.name = name;
        this.userId = userId;
        messageList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id &&
                userId == chatroom.userId &&
                name.equals(chatroom.name) &&
                messageList.equals(chatroom.messageList);
    }

    @Override
    public int hashCode() {
        return ((id * 32) + userId * 32) + name.hashCode() + messageList.hashCode();
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", messageList=" + messageList +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }
}
