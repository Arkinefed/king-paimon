package com.arkinefed.kingpaimonweather;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecast implements Serializable {
    private City city;
    private List<Weather> list;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class City implements Serializable {
        private int id;
        private String name;
        private Coord coord;
        private String country;
        private int population;
        private int timezone;
        private int sunrise;
        private int sunset;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord implements Serializable {
        private float lat;
        private float lon;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather implements Serializable {
        private int dt;
        private Main main;
        private List<WeatherDescription> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private double pop;
        private Rain rain;
        private Sys sys;
        private String dt_txt;
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
        private int sea_level;
        private int grnd_level;
        private int humidity;
        private double temp_kf;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherDescription implements Serializable {
        private int id;
        private String main;
        private String description;
        private String icon;
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
    public static class Wind implements Serializable {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rain implements Serializable {
        private double _3h;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys implements Serializable {
        private String pod;
    }
}
