package com.example.ProjektSW.RestControllers;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ProjektSW.ProjektSwApplication.getJdbcTemplate;

/**
 * RestController umożliwiający sprawdzenie rfid karty.
 */
@RestController
public class CardTestRestController {

    /**
     * Metoda umożliwiająca rozpoczęcie testowania kart rfid.
     *
     * @return true - jeśli udało się zacząć testowanie, false w przeciwnym wypadku
     */
    @RequestMapping("/api/startTestingCart")
    public String startTestingCart() {
        int rowNum = getJdbcTemplate().update("update flagi set flaga = true where nazwa = 'Próbna Karta'");
        if (rowNum == 1) {
            getJdbcTemplate().execute("commit");
            return "true";
        } else {
            getJdbcTemplate().execute("rollback");
            return "false";
        }
    }

    /**
     * Metoda umożliwiająca zakończenie testowania kart rfid.
     *
     * @return true - jeżeli udało się zakończyć testowanie, false w przeciwnym wypadku.
     */
    @RequestMapping("/api/stopTestingCart")
    public String stopTestingCart() {
        int rowNum = getJdbcTemplate().update("update flagi set flaga = false, opis = '' where nazwa = 'Próbna Karta'");
        if (rowNum == 1) {
            getJdbcTemplate().execute("commit");
            return "true";
        } else {
            getJdbcTemplate().execute("rollback");
            return "false";
        }
    }

    /**
     * Metoda zwracająca rfid ostatniej przyłożonej karty.
     *
     * @return rfid ostatniej przyłożonej karty.
     */
    @RequestMapping("/api/readCart")
    public String readCart() {
        String rfid = getJdbcTemplate().queryForObject("select opis from flagi where nazwa = 'Próbna Karta'", String.class);
        JSONObject object = new JSONObject();
        object.put("rfid", rfid);
        return object.toJSONString();
    }

}
