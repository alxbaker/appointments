package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class City {
    public static ObservableList<City> cities = FXCollections.observableArrayList();
    private String cityName;
    private int cityID;

    public City(int cityID, String cityName) {
        this.cityName = cityName;
        this.cityID = cityID;
        cities.add(this);
    }

    public static City getCity(int id) {
        for (City city : cities) {
            if (city.cityID == id) {
                return city;
            }
        }
        return null;
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
