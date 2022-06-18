package br.com.marcosRp.Educ.security;

import br.com.marcosRp.Educ.model.Usuario;
import br.com.marcosRp.Educ.services.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authentication;
    public static final int TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "9aeee44d-3a67-43fc-80d6-db4066b4b0aa";


    public JWTAutenticarFilter(AuthenticationManager authentication) {
        this.authentication = authentication;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try{
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return authentication.authenticate( new UsernamePasswordAuthenticationToken(
                    usuario.getUsername(),
                    usuario.getPassword(),
                    new ArrayList<>()
            ));
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = JWT.create().
                withSubject(userDetails.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+ TOKEN_EXPIRACAO)).sign(Algorithm.HMAC512(TOKEN_SENHA));
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
