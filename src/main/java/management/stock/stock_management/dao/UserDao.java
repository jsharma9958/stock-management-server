package management.stock.stock_management.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import management.stock.stock_management.models.User;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        String query = "Select * from user_db";
        List<User> empList = null;
        empList = jdbctemplate.query(query, new BeanPropertyRowMapper<>(User.class));
        return empList;
    }

    public User getUser(String id) {
        String query = "Select * from user_db Where uid = ?";
        return jdbctemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);

    }

    public String addUser(User user) {
        System.out.println("Adding user with " + user.getPassword());

        String usernameQuery = "SELECT COUNT(*) FROM user_db WHERE name = ?";
        Integer usernameCount = jdbctemplate.queryForObject(usernameQuery, Integer.class, user.getName());

        String emailQuery = "SELECT COUNT(*) FROM user_db WHERE email = ?";
        Integer emailCount = jdbctemplate.queryForObject(emailQuery, Integer.class, user.getEmail());

        if (usernameCount != null && usernameCount > 0) {
            return "Username already exists";
        }
        if (emailCount != null && emailCount > 0) {
            return "Email already exists";
        }

        // Encrypt the password
        String encString = passwordEncoder.encode(user.getPassword());
        String query = "INSERT INTO user_db(name, email, password , gender) VALUES (?, ?, ? , ?)";
        int status = jdbctemplate.update(query, user.getName(), user.getEmail(), encString, user.getGender());

        if (status == 1) {
            return "User added successfully";
        } else {
            return "Adding user failed";
        }
    }

    public String deleteUser(String id) {
        String query = "DELETE FROM user_db WHERE uid = ? ";

        int status = jdbctemplate.update(query, id);
        if (status == 1) {
            return "User Deleted";
        } else {
            return "User failed to delete";
        }
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM user_db WHERE name=?";
        try {
            return jdbctemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            return null;
        }
    }

    public int getUserId(String username) {
        // Query to fetch user ID by username
        String query = "SELECT uid FROM user_db WHERE name=?";

        // Execute the query and return the user ID
        try {
            Integer userId = jdbctemplate.queryForObject(query, Integer.class, username);

            return userId;
        } catch (Exception e) {
            // Handle exception (e.g., log it, wrap it in a custom exception, etc.)
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
