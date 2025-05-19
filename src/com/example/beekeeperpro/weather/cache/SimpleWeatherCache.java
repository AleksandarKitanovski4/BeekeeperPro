package com.example.beekeeperpro.weather.cache;

import com.example.beekeeperpro.weather.dto.CurrentWeatherResponse;

import java.util.HashMap;
import java.util.Map;

public class SimpleWeatherCache {

    private final Map<String, CurrentWeatherResponse> cache = new HashMap<>();

    public void put(String city, CurrentWeatherResponse response) {
        cache.put(city.toLowerCase(), response);
    }

    public CurrentWeatherResponse get(String city) {
        return cache.get(city.toLowerCase());
    }

    public boolean contains(String city) {
        return cache.containsKey(city.toLowerCase());
    }
}
