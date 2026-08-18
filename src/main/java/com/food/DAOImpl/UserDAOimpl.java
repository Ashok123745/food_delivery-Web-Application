package com.food.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.UserDAO;
import com.food.model.User;
import com.tap.utility.DBConnection;

public class UserDAOimpl implements UserDAO {

    @Override
    public int addUser(User user) {
        String INSERT_QUERY = "INSERT INTO users(username, password, email, mobile, address, role, `created date`, `last login date`) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_QUERY)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getMobile());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getRole());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User getUser(int userId) {
        String SELECT_QUERY = "SELECT * FROM users WHERE idusers = ?";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_QUERY)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = extractUserFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        String SELECT_QUERY = "SELECT * FROM users WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_QUERY)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = extractUserFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        String SELECT_QUERY = "SELECT * FROM users WHERE LOWER(TRIM(username)) = LOWER(TRIM(?))";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_QUERY)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateLastLoginDate(int userId) {
        String UPDATE_QUERY = "UPDATE users SET `last login date` = NOW() WHERE idusers = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String UPDATE_QUERY = "UPDATE users SET username=?, password=?, email=?, mobile=?, address=?, role=? WHERE idusers=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getMobile());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getRole());
            stmt.setInt(7, user.getUserId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User Updated Successfully!");
            } else {
                System.out.println("USER NOT FOUND");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        String DELETE_QUERY = "DELETE FROM users WHERE idusers=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(DELETE_QUERY)) {

            stmt.setInt(1, userId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User Deleted Successfully");
            } else {
                System.out.println("User Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String SELECT_QUERY = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_QUERY);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("idusers"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setMobile(rs.getString("mobile"));
        user.setAddress(rs.getString("address"));
        user.setRole(rs.getString("role"));
        user.setCreatedDate(rs.getTimestamp("created date"));
        user.setLastLoginDate(rs.getTimestamp("last login date"));
        return user;
    }
}