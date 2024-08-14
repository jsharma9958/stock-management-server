package management.stock.stock_management.services;

import java.util.List;

import management.stock.stock_management.models.User;

public interface UserService {

    List<User> readUsers();

    User getUser(String id);

    String addUser(User user);

    String deleteUser(String id);

}
