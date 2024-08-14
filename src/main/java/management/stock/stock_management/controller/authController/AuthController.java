package management.stock.stock_management.controller.authController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import management.stock.stock_management.config.JwtProvider;
// import management.stock.stock_management.controller.authController.AuthResponse;
// import management.stock.stock_management.controller.authController.LoginRequest;
import management.stock.stock_management.dao.UserDao;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao uDao;

    @PostMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            int uid = uDao.getUserId(loginRequest.getUsername()); // Retrieve the UID from your user details or database
            String jwt = JwtProvider.generateToken(authentication, uid);
            return new AuthResponse(jwt, "success", uid);
        } catch (AuthenticationException e) {
            return new AuthResponse(null, "Invalid credentials", 0);
        }
    }
}