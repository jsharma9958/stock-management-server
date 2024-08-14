package management.stock.stock_management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import management.stock.stock_management.dao.UserDao;
import management.stock.stock_management.models.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> readUsers() {
        return userDao.getAll();
    }

    @Override
    public User getUser(String id) {
        return userDao.getUser(id);
    }

    @Override
    public String addUser(User user) {

        return userDao.addUser(user);
    }

    @Override
    public String deleteUser(String id) {
        return userDao.deleteUser(id);
    }

}
