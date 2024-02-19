package edu.school21.chat.models.message;

import java.util.Date;

public class Message {
    private int id;
    private int idAuthor;
    private int idRoom;
    private String text;
    private Date date;

    public Message(int idAuthor, int idRoom, String text, Date date) {
        this.id = MessageIdGenerator.getInstance().generateId();
        this.idAuthor = idAuthor;
        this.idRoom = idRoom;
        this.text = text;
        this.date = date;
    }

    public Message(int id, int idAuthor, int idRoom, String text, Date date) {
        this.id = id;
        this.idAuthor = idAuthor;
        this.idRoom = idRoom;
        this.text = text;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                idAuthor == message.idAuthor &&
                idRoom == message.idRoom &&
                text.equals(message.text) &&
                date.equals(message.date);
    }

    @Override
    public int hashCode() {
        return ((id * 31) + idAuthor * 32) + idRoom * 32 + text.hashCode() + date.hashCode();
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", idAuthor=" + idAuthor +
                ", idRoom=" + idRoom +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
