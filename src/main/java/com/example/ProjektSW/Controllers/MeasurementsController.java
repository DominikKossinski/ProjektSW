package com.example.ProjektSW.Controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Klasa będąca kontrolerem widoku strony wyświetlającej pomiary (measurements).
 */
@Controller
public class MeasurementsController {

    /**
     * Metoda klasy odpowiadająca za wywołanie odpowiedniego widoku (measurements).
     *
     * @return "measurements" - nazwa widoku strony wyświetlającej pomiary.
     */
    @GetMapping("/measurements")
    public String getMeasurements(Model model) {
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().compareTo("ROLE_ADMIN") == 0);
        model.addAttribute("isAdmin", isAdmin);
        return "measurements";
    }
}
