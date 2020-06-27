package com.example.grybos.dreamcar;

import java.io.Serializable;

public class Record implements Serializable {

    private String id;
    private String path;
    private String name;
    private String year;
    private String power;
    private String engine;
    private String price;

    public Record(String id, String path, String name, String year, String power, String engine, String price) {

        this.id = id;
        this.path = path;
        this.name = name;
        this.year = year;
        this.power = power;
        this.engine = engine;
        this.price = price;
    }

    public String getId(){
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
