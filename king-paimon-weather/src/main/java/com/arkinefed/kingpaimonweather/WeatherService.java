package com.arkinefed.kingpaimonweather;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public boolean cityExists(String city) {
        return weatherRepository.existsByCity(city);
    }

    public Weather findByCity(String city) {
        return weatherRepository.findByCity(city);
    }

    public Weather addWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }
}
