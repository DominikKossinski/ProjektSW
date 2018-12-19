package com.example.ProjektSW;

/**
 * Klasa reprezentująca użytkownika.
 */
public class User {

    /**
     * Pole przechowujące imię użytkownika.
     */
    private String name;
    /**
     * Pole przechowujące identyfikator karty użytkownika.
     */
    private String rfid;
    /**
     * Pole przechowujące status zalogowania użytkownika.
     */
    private boolean logged;

    /**
     * Publiczny konstruktor klasy.
     *
     * @param name   (String) - imię użytkownika
     * @param rfid   (Stirng) - identyfikator karty użytkownika.
     * @param logged (boolean) - status zalogowania użytkownika.
     */
    public User(String name, String rfid, boolean logged) {
        this.name = name;
        this.rfid = rfid;
        this.logged = logged;
    }

    /**
     * Metoda przedstawiająca obiekt za pomocą tekstu, który można sparsować do obiektu JSON.
     * @return tekst, który można sparsować do obiektu JSON
     */
    public String toJsonString() {
        return "{ \"name\":\"" + name + "\", \"rfid\":\"" + rfid + "\", \"logged\":" +logged + "}";
    }

    /**
     * Metoda zwracająca imię użytkownika.
     *
     * @return imię użytkownika
     */
    public String getName() {
        return name;
    }


}
