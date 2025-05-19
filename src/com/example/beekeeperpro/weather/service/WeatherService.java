package com.example.beekeeperpro.weather.service;

import com.example.beekeeperpro.weather.client.WeatherApiClient;
import com.example.beekeeperpro.weather.dto.CurrentWeatherResponse;
import org.json.JSONObject;

public class WeatherService {

    private final WeatherApiClient apiClient;

    public WeatherService(WeatherApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public CurrentWeatherResponse getCurrentWeatherForCity(String city) {
        String json = apiClient.getCurrentWeather(city);
        try {
            JSONObject root = new JSONObject(json);
            JSONObject main = root.getJSONObject("main");
            JSONObject wind = root.getJSONObject("wind");
            String description = root.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = main.getDouble("temp");
            double humidity = main.getDouble("humidity");
            double windSpeed = wind.getDouble("speed");

            return new CurrentWeatherResponse(city, temperature, humidity, windSpeed, description);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
