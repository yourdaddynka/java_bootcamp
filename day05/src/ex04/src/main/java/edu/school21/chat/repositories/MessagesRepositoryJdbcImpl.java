package edu.school21.chat.repositories;

import edu.school21.chat.models.message.Message;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            String sqlCommand = "SELECT * FROM message WHERE message.message_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sqlCommand);
            if (res.next()) {
                return Optional.of(new Message(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getDate(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Message course) {
        try (Connection connection = dataSource.getConnection()) {
            connection.createStatement().executeUpdate("DELETE FROM message WHERE message.message_id = " + course.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void save(Message course) {
        try (Connection connection = dataSource.getConnection()) {
            connection.createStatement().executeUpdate("INSERT INTO message VALUES (" + course.getId() + ',' + course.getIdAuthor() + ',' + course.getIdRoom() + ",'" + course.getText() + "','" + new SimpleDateFormat("HH:mm:ss").format(course.getDate()) + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Message course) {
        try (Connection connection = dataSource.getConnection()) {
            connection.createStatement().executeUpdate(
                    "UPDATE message SET " +
                            "id_user = " + course.getIdAuthor() +
                            ",id_chatroom = " + course.getIdRoom() +
                            ",message_text = '" + course.getText() +
                            "',message_datetime = '" + new SimpleDateFormat("HH:mm:ss").format(course.getDate()) +
                            "' WHERE message_id = " + course.getId()
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
