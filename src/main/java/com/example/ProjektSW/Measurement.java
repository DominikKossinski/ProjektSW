package com.example.ProjektSW;

import java.security.MessageDigest;
import java.sql.Date;

public class Measurement {

    private Date date;
    private float temperature;
    private float humidity;

    public Measurement(Date date, float temperature, float humidity) {
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public String toJsonString() {
        return "{ \"date\": \"" + date + "\", \"temperature\":" + temperature + ", \"humidity\":" + humidity + "}";
    }
}
