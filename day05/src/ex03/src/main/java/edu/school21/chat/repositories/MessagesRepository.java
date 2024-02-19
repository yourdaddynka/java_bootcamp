package edu.school21.chat.repositories;

import edu.school21.chat.models.message.Message;

import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id);
    void delete(Message course);
    void save(Message course);

    void update(Message course);
}
