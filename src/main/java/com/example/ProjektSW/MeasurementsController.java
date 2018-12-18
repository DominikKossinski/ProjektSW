package com.example.ProjektSW;

import org.springframework.stereotype.Controller;
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
    public String getMeasurements() {
        return "measurements";
    }
}
