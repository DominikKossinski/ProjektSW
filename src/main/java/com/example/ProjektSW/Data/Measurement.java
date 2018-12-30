package com.example.ProjektSW.Data;

/**
 * Klasa reprezentująca pomiar dokonywany przez platformę.
 */
public class Measurement {

    /**
     * Pole przechowujące datę pomiaru.
     */
    private String date;

    /**
     * Pole przechowujące godzinę pomiaru.
     */
    private String time;
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
    public Measurement(String date, String time, float temperature, float humidity) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    /**
     * Metoda, która przedstwia obiekt w formacie json.
     *
     * @return tekst, który może zostać sparsowany do obiektu JSON
     */
    public String toJsonString() {
        return "{ \"date\": \"" + date + "\", \"time\":\"" + time + "\", \"temperature\":" +
                temperature + ", \"humidity\":" + humidity + "}";
    }
}
