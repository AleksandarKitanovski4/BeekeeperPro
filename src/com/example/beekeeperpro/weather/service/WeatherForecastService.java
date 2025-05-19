package com.example.beekeeperpro.weather.service;

import com.example.beekeeperpro.weather.client.WeatherApiClient;
import com.example.beekeeperpro.weather.dto.ForecastResponse;

public class WeatherForecastService {

    private final WeatherApiClient weatherApiClient;

    public WeatherForecastService(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    public ForecastResponse get5DayForecast(String city) {
        return null;
    }
}
