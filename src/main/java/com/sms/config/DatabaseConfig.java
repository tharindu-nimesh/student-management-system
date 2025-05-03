package com.sms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseConfig {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
