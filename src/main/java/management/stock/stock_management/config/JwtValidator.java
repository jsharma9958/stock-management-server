package management.stock.stock_management.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request,
            @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.Jwt_Header);

        if (jwt != null) {

            try {
                String username = JwtProvider.getUsernameFromToken(jwt);
                List<GrantedAuthority> authorities = new ArrayList<>();
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token...!!");
            }
        }

        filterChain.doFilter(request, response);
    }
}
