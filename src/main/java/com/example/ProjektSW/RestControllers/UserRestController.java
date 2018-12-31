package com.example.ProjektSW.RestControllers;

import com.example.ProjektSW.Data.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.ProjektSW.ProjektSwApplication.getJdbcTemplate;

/**
 * Klasa będąca RestController'em odpowiada za żądania dotyczące użytkowników.
 */
@RestController
public class UserRestController {

    /**
     * Metoda zwracające aktualnie zalogowanego użytkownika.
     *
     * @return status logowania (jeżeli użytkownik jest zalogowany dołącza dane użytkownika)
     */
    @RequestMapping("/api/getLoggedUser")
    public String getLoggedUser() {
        List<User> users = getJdbcTemplate().query("SELECT * FROM uzytkownicy where zalogowany = true", (rs, arg1) -> {
            return new User(rs.getString("imie"), rs.getString("rfid"),
                    rs.getBoolean("zalogowany"), rs.getString("rola"));
        });
        if(users.size() > 1) {
            getJdbcTemplate().update("update uzytkownicy set zalogowany = false");
            JSONObject object = new JSONObject();
            object.put("logInStatus", "error");
            return object.toJSONString();
        } else if(users.size() == 1) {
            JSONObject object = new JSONObject();
            object.put("logInStatus", "ok");
            JSONParser parser = new JSONParser();
            JSONObject user = null;
            try {
                user = (JSONObject) parser.parse(users.get(0).toJsonString());
            } catch (ParseException e) {
                e.printStackTrace();
                object.put("logInStatus", "error");
                return object.toJSONString();
            }
            object.put("user", user);
            return object.toJSONString();
        } else {
            JSONObject object = new JSONObject();
            object.put("logInStatus", "no user logged");
            return object.toJSONString();

        }
    }

    @RequestMapping(value = "/api/createUser", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String createUser(@RequestBody String userData) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject user = (JSONObject) parser.parse(userData);
            int rowCount = getJdbcTemplate().update("INSERT INTO uzytkownicy (imie, rfid, zalogowany, rola) " +
                    "values ( '" + user.get("name") + "', '" + user.get("rfid") + "', false, '" + user.get("role") + "')");
            if (rowCount == 1) {
                return "added";
            } else {
                return "User already exists";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }


    @RequestMapping(value = "/api/deleteUser", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String deleteUser(@RequestBody String userData) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject user = (JSONObject) parser.parse(userData);
            int rowCount = getJdbcTemplate().update("DELETE FROM uzytkownicy where imie = '" + user.get("name") + "'");
            if (rowCount == 1) {
                return "deleted";
            } else {
                return "not exists";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
