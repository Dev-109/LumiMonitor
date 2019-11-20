package com.n01249089.lumimonitor;

public class DataStructure {
    public String temperature;
    public String humidity;
    public String lightLevel;
    public String micIn;
    public String micOut;
    public String timestamp;
    public String rgb;

    public DataStructure() {
    }

    public DataStructure(String temperature, String humidity, String lightLevel, String micIn, String micOut, String rgb,String timestamp) {


        this.temperature = temperature;
        this.humidity = humidity;
        this.lightLevel = lightLevel;
        this.micIn = micIn;
        this.micOut= micOut;
        this.rgb = rgb;
        this.timestamp = timestamp;

    }

    public String getMicIn() {
        return micIn;
    }

    public void setMicIn(String micIn){
        this.micIn = micIn;
    }

    public String getRgb() {

        return rgb;

    }

    public void setRgb(String rgb){

        this.rgb = rgb;

    }

    public String getLightLevel() {

        return lightLevel;

    }

    public void setLightLevel(String lightLevel) {

        this.lightLevel = lightLevel;

    }

    public void setMicOut(String micOut){

        this.micOut = micOut;

    }
    public String getMicOut() {

        return micOut;

    }

    public String getHumidity() {

        return humidity;

    }

    public void setHumidity(String humidity){

        this.humidity = humidity;

    }
    public String getTemp() {

        return temperature;

    }

    public void setTemp(String temperature) {

        this.temperature = temperature;

    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}