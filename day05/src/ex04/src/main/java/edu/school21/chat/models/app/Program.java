package edu.school21.chat.models.app;

import edu.school21.chat.models.user.User;
import edu.school21.chat.repositories.UserRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.ArrayList;


public class Program {

    public static void main(String[] args) {
        try {
            UserRepository userRepository = new UsersRepositoryJdbcImpl(new DataSourceCreate().createDataSource());
            ArrayList<User> userArrayList = (ArrayList<User>) userRepository.findAll(1, 2);
            for (int i = 0; i < userArrayList.size(); i++) {
                System.out.println(userArrayList.get(i).toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
