package br.com.marcosRp.Educ.security;

import br.com.marcosRp.Educ.services.UserDetailsServiceImpl;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class JWTConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;


    public JWTConfig(UserDetailsServiceImpl usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,  "/login").permitAll()
                .antMatchers(HttpMethod.GET,"/h2-console/**/**").permitAll()
                .anyRequest().authenticated().and()
//                .addFilterBefore(new JWTAutenticarFilter(authenticationManager()))
//                .addFilterAfter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
