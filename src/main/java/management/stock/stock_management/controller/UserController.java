package management.stock.stock_management.controller;

import org.springframework.web.bind.annotation.RestController;

import management.stock.stock_management.models.User;
import management.stock.stock_management.services.UserService;
import management.stock.stock_management.services.UserServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

    @Autowired
    UserService userService = new UserServiceImpl();

    @GetMapping("api/v1/users")
    public List<User> getUsers() {
        return userService.readUsers();
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping("users")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable String id) {

        return userService.deleteUser(id);
    }

}
