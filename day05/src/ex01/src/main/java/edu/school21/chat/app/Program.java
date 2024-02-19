package edu.school21.chat.app;


import edu.school21.chat.models.message.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a message ID");
        if(in.hasNextInt()) {
            try{
            DataSource dataSource =  new DataSourceCreate().createDataSource();
            MessagesRepository messagesRep = new MessagesRepositoryJdbcImpl(dataSource);
            Optional <Message> message = messagesRep.findById(in.nextLong());
                if(message.isPresent()){ //контейнер не пустой
                    System.out.println(message.get());
                }else {
                    System.out.println("Неверный или несуществующий ID!");
                }

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }else{
            System.err.println("Incorrect input!");
            System.exit(-1);
        }
    }
}
