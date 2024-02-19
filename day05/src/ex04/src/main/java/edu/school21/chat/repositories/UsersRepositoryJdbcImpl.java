package edu.school21.chat.repositories;

import edu.school21.chat.models.chatroom.Chatroom;
import edu.school21.chat.models.user.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UserRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
//            Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("WITH user_chatrooms AS (SELECT u.User_ID,\n" +
                    "                               array_agg(DISTINCT cr.Chatroom_ID)   AS created_rooms_ids,\n" +
                    "                               array_agg(DISTINCT cr.Chatroom_Name) AS created_rooms_names,\n" +
                    "                               array_agg(DISTINCT mr.Chatroom_ID)   AS rooms_ids,\n" +
                    "                               array_agg(DISTINCT mr.Chatroom_Name) AS rooms_names,\n" +
                    "                               array_agg(DISTINCT o.User_ID)        AS owners_ids,\n" +
                    "                               array_agg(DISTINCT o.Login)          AS owners_logins,\n" +
                    "                               array_agg(DISTINCT o.Password)       AS owners_passwords\n" +
                    "                        FROM Users u\n" +
                    "                                 LEFT JOIN Chatroom cr ON u.User_ID = cr.id_User\n" +
                    "                                 LEFT JOIN Message m ON u.User_ID = m.id_User\n" +
                    "                                 LEFT JOIN Chatroom mr ON m.id_Chatroom = mr.Chatroom_ID\n" +
                    "                                 LEFT JOIN Users o ON mr.id_User = o.User_ID\n" +
                    "                        GROUP BY u.User_ID)\n" +
                    "SELECT u.*,\n" +
                    "       uc.created_rooms_ids,\n" +
                    "       uc.created_rooms_names,\n" +
                    "       uc.rooms_ids,\n" +
                    "       uc.rooms_names,\n" +
                    "       uc.owners_ids,\n" +
                    "       uc.owners_logins,\n" +
                    "       uc.owners_passwords\n" +
                    "FROM Users u\n" +
                    "         LEFT JOIN user_chatrooms uc ON u.User_ID = uc.User_ID LIMIT ? OFFSET ?;");

            statement.setInt(1, size);
            statement.setInt(2, page * size);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                ArrayList<Chatroom> chatRoomStories = new ArrayList<>();
                Integer[] chatroomIds = (Integer[]) res.getArray("created_rooms_ids").getArray();
                String[] chatroomNames = (String[]) res.getArray("created_rooms_names").getArray();
                Integer[] chatroomUserIds = (Integer[]) res.getArray("rooms_ids").getArray();
                for (int i = 0; i < chatroomIds.length; i++) {
                    chatRoomStories.add(new Chatroom(chatroomNames[i], chatroomUserIds[i]));
                }
                ArrayList<Chatroom> chatrooms = new ArrayList<>();
                Integer[] chatRooms_ids = (Integer[]) res.getArray("rooms_ids").getArray();
                String[] chatRooms_names = (String[]) res.getArray("rooms_names").getArray();
                Integer[] chatRooms_owners_ids = (Integer[]) res.getArray("owners_ids").getArray();

                for (int i = 0; i < chatRooms_ids.length; i++) {
                    chatrooms.add(new Chatroom(chatRooms_names[i], chatRooms_owners_ids[i]));
                }
                int user_id = (int) res.getLong("user_id");
                String login = res.getString("login");
                String password = res.getString("password");
                userList.add(new User(user_id, login, password, chatRoomStories, chatrooms));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}