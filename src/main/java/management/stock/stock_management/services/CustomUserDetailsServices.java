package management.stock.stock_management.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import management.stock.stock_management.dao.UserDao;
import management.stock.stock_management.models.User;

@Service
public class CustomUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserDao uDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = uDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Create an empty list of authorities (you can populate this if needed)
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Return a Spring Security User object with username, password, and authorities
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                authorities);
    }
}
