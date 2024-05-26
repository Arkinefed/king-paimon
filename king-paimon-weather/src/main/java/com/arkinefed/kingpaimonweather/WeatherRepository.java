package com.arkinefed.kingpaimonweather;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {
    boolean existsByCity(String city);

    Weather findByCity(String city);
}
