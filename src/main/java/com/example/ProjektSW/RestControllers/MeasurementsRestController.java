package com.example.ProjektSW.RestControllers;

import com.example.ProjektSW.Data.Measurement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param date - data (np. 2018-12-29)
     * @return jsonArray zawierające wszystkie pomiary.
     */
    @RequestMapping("/api/getMeasurements")
    public String getExample(
            @RequestParam(value = "date", defaultValue = "", required = false) String date
    ) {
        if (date.isEmpty()) {
            List<Measurement> measurements = getJdbcTemplate().query("SELECT * FROM pomiary order by data", (rs, arg1) -> {
                String[] dateTime = rs.getString("data").split(" ");
                return new Measurement(dateTime[0], dateTime[1], rs.getFloat("temperatura"), rs.getFloat("wilgotność"));
            });
            return response(measurements);
        } else {
            List<Measurement> measurements = getJdbcTemplate().query(" select * from pomiary where DATE(data) = DATE('" + date + "') order by data", (rs, arg1) -> {
                String[] dateTime = rs.getString("data").split(" ");
                return new Measurement(dateTime[0], dateTime[1], rs.getFloat("temperatura"), rs.getFloat("wilgotność"));
            });
            return response(measurements);
        }
    }

    /**
     * Metoda zwracająca informacje o pomiarach z ostatnich 24 min.
     *
     * @return jsonArray zawierające pomiary z ostatnich 24 min.
     */
    @RequestMapping("/api/getLastMeasurements")
    public String getLastMeasurements() {
        List<Measurement> measurements = getJdbcTemplate().query(
                "select * from pomiary where data >= (NOW() - INTERVAL 24 MINUTE) order by data;", (rs, arg1) -> {
                    String[] dateTime = rs.getString("data").split(" ");
                    return new Measurement(dateTime[0], dateTime[1], rs.getFloat("temperatura"), rs.getFloat("wilgotność"));
                });
        return response(measurements);
    }


    /**
     * Metoda formująca odpowiedź serwera.
     *
     * @param measurements - lista pomiarów
     * @return odpowiedź serwera w formacie JSON
     */
    private String response(List<Measurement> measurements) {
        JSONArray array = new JSONArray();
        JSONParser parser = new JSONParser();
        for (Measurement measurement : measurements) {
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

    /**
     * Metoda zwracająca unikalne daty dokonywania pomiarów.
     *
     * @return jsonArray zawierające unikalne daty pomiarów.
     */
    @RequestMapping("/api/getDates")
    public String getDates() {
        List<String> dates = getJdbcTemplate().query("SELECT DISTINCT DATE(data) FROM pomiary;",
                (rs, arg1) -> rs.getString(1));
        JSONArray jsonArray = new JSONArray();
        for (String date : dates) {
            jsonArray.add(date);
        }
        JSONObject object = new JSONObject();
        object.put("responseStatus", "ok");
        object.put("dates", dates);
        return object.toJSONString();
    }

}
