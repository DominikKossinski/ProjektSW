package com.example.ProjektSW.RestControllers;

import com.example.ProjektSW.Data.Measurement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.ProjektSW.ProjektSwApplication.getJdbcTemplate;

/**
 * Klasa będąca RestController'em odpowiadająca za żądania dotyczące pomierów.
 */
@RestController
public class MeasurementsRestController {

    /**
     * Metoda odpowiadająca za pobranie oraz zwrócenie listy wszystkich pomiarów.
     *
     * @return jsonArray zawierające wszystkie pomiary.
     */
    @RequestMapping("/api/getMeasurements")
    public String getExample() {
        List<Measurement> measurements = getJdbcTemplate().query("SELECT * FROM pomiary order by data", (rs, arg1) -> {
            return new Measurement(rs.getDate("data"), rs.getFloat("temperatura"), rs.getFloat("wilgotność"));
        });
        JSONArray array = new JSONArray();
        JSONParser parser = new JSONParser();
        for(Measurement measurement : measurements) {
            try {
                array.add((JSONObject) parser.parse(measurement.toJsonString()));
            } catch (ParseException e) {
                e.printStackTrace();
                JSONObject object = new JSONObject();
                object.put("responseStatus", "error");
                return object.toJSONString();
            }
        }
        JSONObject object = new JSONObject();
        object.put("responseStatus", "ok");
        object.put("measurements", array);
        return object.toJSONString();
    }
}
