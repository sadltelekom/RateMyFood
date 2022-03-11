package com.zeegermans.RateMyFood.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector instance;
    private Connection connection;

    public static synchronized DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
            return instance;
        }
        return instance;
    }

    private DBConnector() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ratemyfood?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "foodadmin",
                    "FeetMakeUsNotHorny"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }


}
