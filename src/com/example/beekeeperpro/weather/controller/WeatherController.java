package com.example.beekeeperpro.weather.controller;

import com.example.beekeeperpro.weather.dto.CurrentWeatherResponse;
import com.example.beekeeperpro.weather.service.WeatherService;

public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void showWeatherForCity(String city) {
        CurrentWeatherResponse response = weatherService.getCurrentWeatherForCity(city);
        if (response != null) {
            System.out.println(response);
        } else {
            System.out.println("Unable to fetch weather data for " + city);
        }
    }
}
