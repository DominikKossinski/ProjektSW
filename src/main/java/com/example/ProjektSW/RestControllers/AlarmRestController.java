package com.example.ProjektSW.RestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ProjektSW.ProjektSwApplication.getJdbcTemplate;

/**
 * RestController umożliwiający wywołanie próbnego alarmu pożarowego.
 */
@RestController
public class AlarmRestController {

    /**
     * Metoda służąca do wywołania próbnego alarmu.
     *
     * @return true - jeśli udało się wywołać alarm, false w przeciwnym wypadku.
     */
    @GetMapping("/api/testAlert")
    public String startTrialAlarm() {
        int rowNum = getJdbcTemplate().update("update flagi set flaga = true where nazwa = 'Próbny Alarm'");
        if (rowNum == 1) {
            return "true";
        } else {
            return "false";
        }
    }
}
