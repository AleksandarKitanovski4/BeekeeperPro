package com.example.beekeeperpro.weather.dto;

import java.util.List;

public class ForecastResponse {

    private final String city;
    private final List<String> dailyForecast;

    public ForecastResponse(String city, List<String> dailyForecast) {
        this.city = city;
        this.dailyForecast = dailyForecast;
    }

    public String getCity() {
        return city;
    }

    public List<String> getDailyForecast() {
        return dailyForecast;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Forecast for " + city + ":\n");
        for (String f : dailyForecast) {
            sb.append(" - ").append(f).append("\n");
        }
        return sb.toString();
    }
}
