package com.example.beekeeperpro.weather.client;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class WeatherApiClient {

    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getCurrentWeather(String city) {
        try {
            String urlString = BASE_URL + "?q=" + city + "&appid=" + apiKey + "&units=metric";
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            reader.close();
            return responseBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching weather data";
        }
    }

    public WeatherApiClient() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                this.apiKey = prop.getProperty("weather.api.key");
            } else {
                throw new FileNotFoundException("application.properties not found");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
