package com.example.beekeeperpro.weather.dto;

public class CurrentWeatherResponse {

    private final String city;
    private final double temperature;
    private final double humidity;
    private final double windSpeed;
    private final String description;

    public CurrentWeatherResponse(String city, double temperature, double humidity, double windSpeed, String description) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Weather in " + city + " → " + temperature + "°C, Humidity: " + humidity + "%, Wind: " + windSpeed + " m/s, " + description;
    }
}
