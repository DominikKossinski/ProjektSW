package com.example.ProjektSW;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static com.example.ProjektSW.ProjektSwApplication.getJdbcTemplate;

@RestController
public class MeasurementsRestController {

    @RequestMapping("/api/getMeasurements")
    public String getExample() {
        List<Measurement> measurements = getJdbcTemplate().query("SELECT * FROM pomiary", (rs, arg1) -> {
            return new Measurement(rs.getDate("data"), rs.getFloat("temperatura"), rs.getFloat("wilgotność"));
        });
        JSONArray array = new JSONArray();
        JSONParser parser = new JSONParser();
        for(Measurement measurement : measurements) {
            try {
                array.add((JSONObject) parser.parse(measurement.toJsonString()));
            } catch (ParseException e) {
                e.printStackTrace();
                return "ERROR";
            }
        }

        return array.toJSONString();
    }
}
