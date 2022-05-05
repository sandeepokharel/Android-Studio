package com.example.dailytrackerapp.ui;

/**
 * This number class handless the number, "name" and the description of it.
 * @author Silja
 */

public class Number {
    private String description, number;
    /**
     *This is the constructor for a number and the description instance
     *@param n the number and "name" of it
     *@param des the description of the number
     */
    public Number(String n, String des) {
        this.number = n;
        this.description = des;
    }
    /**
     * @return the description of the number
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * @return to the number
     */
    public String toString() {
        return number;
    }
}
