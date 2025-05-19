package com.example.beekeeperpro.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String DB_NAME = isTestMode() ? "beekeeper_pro_test" : "beekeeper_pro";
    private static final String URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String USER = System.getenv("SQL_USER");
    private static final String PASS = System.getenv("SQL_PASS");

    public static Connection getConnection() throws SQLException {
        System.out.println("Connecting to: " + URL);
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private static boolean isTestMode() {
        return System.getProperty("test") != null ||
                java.lang.management.ManagementFactory.getRuntimeMXBean()
                        .getInputArguments().toString().contains("junit");
    }
}
