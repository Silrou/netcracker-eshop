package com.eshop.backend.auth.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eshop.backend.DAO.DataAccess.AuthorizedUser.AuthorizedUserDao;
import com.eshop.backend.DAO.Models.AuthorizedUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static com.eshop.backend.auth.JWT.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final AuthorizedUserDao authorizedUserDao;

    public JWTAuthorizationFilter(AuthenticationManager authManager, AuthorizedUserDao authorizedUserDao) {
        super(authManager);
        this.authorizedUserDao = authorizedUserDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException, java.io.IOException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                // new arraylist means authorities
                AuthorizedUser authorizedUser = authorizedUserDao.getByLogin(user);
                return new UsernamePasswordAuthenticationToken(user, null,
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + authorizedUser.getUserRole())));

            }

            return null;
        }

        return null;
    }
}
