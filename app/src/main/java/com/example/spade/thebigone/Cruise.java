package com.example.spade.thebigone;

import android.net.UrlQuerySanitizer;

import java.net.URL;

public class Cruise {
    private String cruiseLine;
    private String shipName;
    private String cruiseCode;
    private String region;
    private int cruiseLength;
    private double price;
    private double gratuity;
    private String imageURL;

    public Cruise(String cruiseLine, String shipName, String cruiseCode, String region,
                  int cruiseLength, double price, double gratuity, String imageURL){
        this.cruiseLine = cruiseLine;
        this.shipName = shipName;
        this.cruiseCode = cruiseCode;
        this.region = region;
        this.cruiseLength = cruiseLength;
        this.price = price;
        this.gratuity = gratuity;
        this.imageURL = imageURL;
    }
    public Cruise(){
        this.cruiseLine = null;
        this.shipName = null;
        this.cruiseCode = null;
        this.region = null;
        this.cruiseLength = 0;
        this.price = 0;
        this.gratuity = 0;
        this.imageURL = null;


    }

    public String getCruiseLine() {
        return cruiseLine;
    }

    public void setCruiseLine(String cruiseLine) {
        this.cruiseLine = cruiseLine;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getCruiseCode() {
        return cruiseCode;
    }

    public void setCruiseCode(String cruiseCode) {
        this.cruiseCode = cruiseCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCruiseLength() {
        return cruiseLength;
    }

    public void setCruiseLength(int cruiseLength) {
        this.cruiseLength = cruiseLength;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGratuity() {
        return gratuity;
    }

    public void setGratuity(double gratuity) {
        this.gratuity = gratuity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
