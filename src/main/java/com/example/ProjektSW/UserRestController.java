package com.example.ProjektSW;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.ProjektSW.ProjektSwApplication.getJdbcTemplate;

@RestController
public class UserRestController {

    @RequestMapping("/api/getLoggedUser")
    public String getLoggedUser(@RequestParam("name") String name) {
        List<User> users = getJdbcTemplate().query("SELECT * FROM uzytkownicy where imie = '" + name + "'", (rs, arg1)->{
            return  new User(rs.getString("imie"), rs.getString("rfid"), rs.getBoolean("zalogowany"));
        });
        if(users.size() > 1) {
            return "ERROR";
        } else if(users.size() == 1) {
            return users.get(0).toJsonString();
        } else {
            return "No user with name = " + name;
        }

    }
}
