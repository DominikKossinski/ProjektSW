package com.example.ProjektSW;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasa będąca RestConteoller'em odpowiada za wylogowanie użytkownika.
 */
@RestController
public class LogoutRestController {

    /**
     * Metoda odpowiadająca za wylogowanie użytkownika.
     *
     * @return "true" - informację o wylogowaniu użytkownika
     */
    @RequestMapping("/api/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "true";
    }
}
