package com.example.ProjektSW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Klasa posiadająca statyczną metodę main, która uruchamia server.
 */
@SpringBootApplication
public class ProjektSwApplication {

    /**
     * Pole przechowujące jdbcTemplate umożliwające wykonywanie operacji na bazie danych.
     */
    private static JdbcTemplate jdbcTemplate = null;
    /**
     * Pole przechowujące menager danych użytkowników.
     */
    private static InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

    /**
     * Statyczna metoda do uruchamiania serwera.
     *
     * @param args - prarametry przekazywane do programu
     */
    public static void main(String[] args) {

        SpringApplication.run(ProjektSwApplication.class, args);
    }

    /**
     * Metoda do pobierania jdbcTemplate. W przypadku pierwszego pobrania następuje połączenie z bazą danych.
     *
     * @return (JdbcTemplate) - jdbcTemplate do wykonywania operacji na bazie danych
     */
    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl(System.getenv("dbUrl"));
            dataSource.setUsername(System.getenv("dbUserName"));
            dataSource.setPassword(System.getenv("dbPassword"));
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    /**
     * Metoda zwracająca menadżer danych użytkowników.
     *
     * @return (InMemoryUserDetailsManager) - menadżer danych użytkowników
     */
    public static InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
        return inMemoryUserDetailsManager;
    }
}

