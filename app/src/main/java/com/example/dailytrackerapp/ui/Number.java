package com.example.dailytrackerapp.ui;

/**
 * This number class handless the number, "name" and the description of it of this app.
 * @author Silja
 */

public class Number {
    private String description, number;

    public Number(String n, String des) {
        this.number = n;
        this.description = des;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return number;
    }
}
