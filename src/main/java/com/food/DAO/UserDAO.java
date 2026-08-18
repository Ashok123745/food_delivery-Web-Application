package com.food.DAO;

import java.util.List;
import com.food.model.User;

public interface UserDAO {
    int addUser(User user);
    User getUser(int userId);
    User getUserByEmail(String email);       // Added
    User getUserByUsername(String username); // Added
    void updateLastLoginDate(int userId);    // Added
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
}