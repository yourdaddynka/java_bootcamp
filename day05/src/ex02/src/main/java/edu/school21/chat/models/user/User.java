package edu.school21.chat.models.user;

import edu.school21.chat.models.chatroom.Chatroom;

import java.util.ArrayList;

public class User {
    private int id;
    private String login;
    private String password;

    private ArrayList<Chatroom> chatRoomStories;
    private ArrayList<Chatroom> chatrooms;

    public User(String login, String password, ArrayList chatRoomStories, ArrayList chatrooms) {
        this.id = UserIdGenerator.getInstance().generateId();
        this.login = login;
        this.password = password;
        this.chatRoomStories = new ArrayList<>();
        this.chatrooms = new ArrayList<>();
    }

    @Override
    public boolean equals(Object objects) {
        if (this == objects) return true;
        if (objects == null || this.getClass() != objects.getClass()) return false;
        User other = (User) objects;
        return id == other.id &&
                login.equals(other.login) &&
                password.equals(other.password) &&
                chatRoomStories.equals(other.chatRoomStories) &&
                chatrooms.equals(other.chatrooms);
    }

    @Override
    public int hashCode() {
        return login.hashCode() + password.hashCode() + chatRoomStories.hashCode() + chatrooms.hashCode() + id * 31;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chatRoomStories=" + chatRoomStories +
                ", chatrooms=" + chatrooms +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Chatroom> getChatRoomStories() {
        return chatRoomStories;
    }

    public ArrayList<Chatroom> getChatrooms() {
        return chatrooms;
    }
}
