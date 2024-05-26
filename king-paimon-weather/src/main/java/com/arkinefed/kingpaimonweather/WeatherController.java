package com.arkinefed.kingpaimonweather;

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
        if (!weatherService.cityExists(city)) {
            weatherService.addWeather(Weather.builder()
                    .city(city)
                    .lastRefresh(LocalDateTime.now())
                    .weather("sun")
                    .build());
        }

        for (Weather w : weatherService.findAll()) {
            System.out.println(w);
        }

        return city;
    }
}
