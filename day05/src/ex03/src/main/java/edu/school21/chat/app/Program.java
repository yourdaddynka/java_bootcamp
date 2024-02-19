package edu.school21.chat.app;


import edu.school21.chat.models.message.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;

import java.util.Date;
import java.util.Optional;


public class Program {

    public static void main(String[] args) {
        try {
            MessagesRepository messagesRep = new MessagesRepositoryJdbcImpl(new DataSourceCreate().createDataSource());
            Optional<Message> messageOptional = messagesRep.findById(9L);
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                System.out.println(message);
                message.setText("TEST TEXT");
                message.setDate(new Date());
                messagesRep.update(message);
            }
            Optional<Message> checkOpt = messagesRep.findById(9L);
            if (checkOpt.isPresent()) {
                System.out.println(checkOpt.get().getText());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
