package com.arkinefed.kingpaimonweather;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkinefed.kingpaimonweather.misc.CommonVariables;

@RestController
@RequestMapping(CommonVariables.API_V1)
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city) {
        if (weatherService.cityExists(city)) {
            Weather weather = weatherService.findByCity(city);

            if (weather.getLastRefresh().plusHours(3).isBefore(LocalDateTime.now())) {
                try {
                    Location locationData = WeatherAPICaller.getLocationData(city);
                    CurrentWeather currentWeather = WeatherAPICaller.getCurrentWeather(locationData);

                    locationData.setCurrentWeather(currentWeather);

                    weather.setWeather(currentWeather.getWeather().get(0).getMain());
                    weather.setLastRefresh(LocalDateTime.now());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (WeatherAPICaller.CityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            return weather.getWeather();
        } else {
            try {
                Location locationData = WeatherAPICaller.getLocationData(city);
                CurrentWeather currentWeather = WeatherAPICaller.getCurrentWeather(locationData);

                locationData.setCurrentWeather(currentWeather);

                weatherService.addWeather(Weather.builder()
                        .city(city)
                        .lastRefresh(LocalDateTime.now())
                        .weather(currentWeather.getWeather().get(0).getMain())
                        .build());

                return currentWeather.getWeather().get(0).getMain();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (WeatherAPICaller.CityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        return "none";
    }
}
