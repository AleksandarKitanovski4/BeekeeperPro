package com.example.beekeeperpro.utility;

import com.example.beekeeperpro.repository.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseCleaner {

    public static void clearDatabase() {
        String sql = "TRUNCATE TABLE apiary, beehive, finance, inspection, product, users, weather_data RESTART IDENTITY CASCADE";

        try (Connection conn = DbConnection.getConnection()) {
            String dbUrl = conn.getMetaData().getURL();
            if (!dbUrl.toLowerCase().contains("_test")) {
                throw new IllegalArgumentException("Clean operation blocked! Not a test database.");
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
                System.out.println("Test database cleaned successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
