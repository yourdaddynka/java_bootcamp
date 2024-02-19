package edu.school21.chat.app;


import edu.school21.chat.models.chatroom.Chatroom;
import edu.school21.chat.models.message.Message;
import edu.school21.chat.models.user.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Program {

    public static void main(String[] args) {
        try {
            User creator = new User("user_test", "user_test", new ArrayList(), new ArrayList());
            Chatroom room = new Chatroom("room_test", creator.getId());
            Message message = new Message(4, creator.getId(), room.getId(), "TestHello!", new Date());
            MessagesRepository messagesRep = new MessagesRepositoryJdbcImpl(new DataSourceCreate().createDataSource());
            try{
                messagesRep.save(message);
                Optional<Message> messageOut = messagesRep.findById((long) message.getId());
                if (messageOut.isPresent()) { //контейнер не пустой
                    System.out.println(messageOut.get());
                } else {
                    System.out.println("Неверный или несуществующий ID!");
                }
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
