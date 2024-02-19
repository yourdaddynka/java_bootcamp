package edu.school21.chat.repositories;

import edu.school21.chat.models.user.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll(int page, int size);
}
