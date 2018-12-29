package com.example.ProjektSW.Data;

import java.sql.Date;

/**
 * Klasa reprezentująca pomiar dokonywany przez platformę.
 */
public class Measurement {

    /**
     * Pole przechowujące datę pomiaru.
     */

    private Date date;
    /**
     * Pole przechowujące zarejestrowaną temperaturę.
     */
    private float temperature;
    /**
     * Pole przechowujące zarejestrowaną wilgotność.
     */
    private float humidity;

    /**
     * Publiczny konstruktor klasy.
     *
     * @param date        (Date) - data pomiaru
     * @param temperature (float) - zarejestrowana temperatura
     * @param humidity    (float) - zarejestrowana wilgotność
     */
    public Measurement(Date date, float temperature, float humidity) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    /**
     * Metoda, która przedstwia obiekt w formacie json.
     *
     * @return tekst, który może zostać sparsowany do obiektu JSON
     */
    public String toJsonString() {
        return "{ \"date\": \"" + date + "\", \"temperature\":" + temperature + ", \"humidity\":" + humidity + "}";
    }
}
