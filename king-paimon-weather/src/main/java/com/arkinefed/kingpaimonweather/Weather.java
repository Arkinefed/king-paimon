package com.arkinefed.kingpaimonweather;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@Document(collection = "weather")
public class Weather {
    @Id
    private String id;
    private String city;
    private LocalDateTime lastRefresh;
    private String weather;
}
