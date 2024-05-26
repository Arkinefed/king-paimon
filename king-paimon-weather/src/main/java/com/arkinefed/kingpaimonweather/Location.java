package com.arkinefed.kingpaimonweather;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    private String name;
    private double lat;
    private double lon;
    private CurrentWeather currentWeather;
    private WeatherForecast weatherForecast;

    public Location(String name) {
        this.name = name;
    }
}