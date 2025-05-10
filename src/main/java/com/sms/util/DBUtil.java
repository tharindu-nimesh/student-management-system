package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBUtil {
    // Don't keep a static connection - create a new one for each request

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Properties prop = new Properties();
            InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // This method should be called when you're done with a connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
