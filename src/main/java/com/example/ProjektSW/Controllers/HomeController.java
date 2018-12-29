package com.example.ProjektSW.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Klasa będąca kontrolerem widoku strony głównej (home).
 */
@Controller
public class HomeController {

    /**
     * Metoda odpowiadająca za wywołanie odpowiedniego widoku (home).
     *
     * @return "home" - nazwa widoku strony głównej.
     */
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
