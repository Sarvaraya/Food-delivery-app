package com.fda.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

    private static final String DEFAULT_URL =
        "jdbc:h2:file:./data/food_delivery;MODE=MySQL;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;NON_KEYWORDS=USER,ORDERS;INIT=RUNSCRIPT FROM 'classpath:schema.sql'";
    private static final String URL = getEnvOrDefault("DB_URL", DEFAULT_URL);
    private static final String USER = getEnvOrDefault("DB_USER", "sa");
    private static final String PASSWORD = getEnvOrDefault("DB_PASSWORD", "");

    // Static block to load the JDBC driver class once when the class is loaded
    static {
        try {
            Class.forName(resolveDriverClass(URL));
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String getEnvOrDefault(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }

    private static String resolveDriverClass(String url) {
        if (url.startsWith("jdbc:mysql:")) {
            return "com.mysql.cj.jdbc.Driver";
        }
        if (url.startsWith("jdbc:h2:")) {
            return "org.h2.Driver";
        }
        throw new IllegalArgumentException("Unsupported JDBC URL: " + url);
    }
}

