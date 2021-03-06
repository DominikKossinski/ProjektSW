package com.example.ProjektSW;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Klasa odpowiadająca za konfigurację bezpieczeństwa serwera.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Metoda odpowiadająca za konfigurację dostępu do poszczególnych url serwera.
     *
     * @param http (HttpSecurity) - httpSecurity do skonfigurowania
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home", "/api/login", "/api/getLoggedUser", "/api/logout", "/css/*", "/js/*","/img/*").permitAll()
                .antMatchers("/measurements", "/api/getMeasurements*", "/api/getLastMeasurements", "/api/getDates").authenticated()
                .antMatchers("/admin", "/api/testAlert", "/api/addUser", "/api/deleteUser", "/api/startTestingCart",
                        "/api/stopTestingCart", "/api/readCart", "/api/createUser", "/api/deleteUser").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().formLogin().loginPage("/home").defaultSuccessUrl("/measurements")
                .usernameParameter("username").passwordParameter("password").permitAll()
                .and().logout().logoutSuccessUrl("/home");
        http.csrf().disable();
    }


    /**
     * Metoda wczytująca dane użytkowników z bazy danych przy starcie serwera.
     *
     * @return wczytany servis danych użytkowników.
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        JdbcTemplate jdbcTemplate = ProjektSwApplication.getJdbcTemplate();
        List<com.example.ProjektSW.Data.User> users = jdbcTemplate.query("SELECT * FROM uzytkownicy",
                (rs, arg1) -> {
                    return new com.example.ProjektSW.Data.User(rs.getString("imie"),
                            rs.getString("rfid"), rs.getBoolean("zalogowany"),
                            rs.getString("rola"));
                });
        for (com.example.ProjektSW.Data.User user : users) {
            UserDetails userDetails = User.withUsername(user.getName()).password("true").roles(user.getRole()).build();
            ProjektSwApplication.getInMemoryUserDetailsManager().createUser(userDetails);
        }
        return ProjektSwApplication.getInMemoryUserDetailsManager();
    }

}



