package com.arkinefed.kingpaimonweather;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeather implements Serializable {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord implements Serializable {
        private double lon;
        private double lat;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather implements Serializable {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main implements Serializable {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
        private int sea_level;
        private int grnd_level;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind implements Serializable {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rain implements Serializable {
        private double oneHour;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clouds implements Serializable {
        private int all;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys implements Serializable {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}