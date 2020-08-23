package model;

import controller.CustomerController;

public class City {
    private String cityName;
    private int cityID;

    public City(int cityID, String cityName) {
        this.cityName = cityName;
        this.cityID = cityID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
