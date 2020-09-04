package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class City {
    //an ObservableList for all cities
    public static ObservableList<City> cities = FXCollections.observableArrayList();
    private String cityName;
    private int cityID;

    public City(int cityID, String cityName) {
        this.cityName = cityName;
        this.cityID = cityID;
        cities.add(this);
    }

    //method to return a city based on city ID
    public static City getCity(int id) {
        for (City city : cities) {
            if (city.getCityID() == id) {
                return city;
            }
        }
        return null;
    }

    public int getCityID() {
        return cityID;
    }

    @Override
    public String toString() {
        return cityName;
    }

}
