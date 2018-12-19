package com.example.ProjektSW.RestControllers;

import com.example.ProjektSW.ProjektSwApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasa odpowiadająca za logowanie użytkownika.
 */
@RestController
public class LoginRestController {

    /**
     * Metoda odpowiadająca za zalogowanie użytkownika.
     *
     * @param userName (String) - imię użytkownika
     * @return "true" - informacja o zalogowaniu
     */
    @RequestMapping("/api/login")
    public String login(@RequestParam("userName") String userName) {
        UserDetails userDetails = ProjektSwApplication.getInMemoryUserDetailsManager().loadUserByUsername(userName);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userName, "true", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "true";
    }
}
