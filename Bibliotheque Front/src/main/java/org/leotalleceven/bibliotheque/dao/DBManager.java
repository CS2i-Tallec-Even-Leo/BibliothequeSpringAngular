package org.leotalleceven.bibliotheque.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static Connection _connect;

    private static void initConnexion(String aDriver, String aUrl, String aLogin, String aPassword) throws SQLException {
        try {
            Class.forName(aDriver); // Charger le driver MySQL 8
            _connect = DriverManager.getConnection(aUrl, aLogin, aPassword);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver non trouvé : " + aDriver, e);
        }
    }


    //Singleton
    public static Connection getConnect() throws SQLException {
        if (_connect == null || _connect.isClosed()) {

            // Paramètres à ADAPTER à ta config
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3307";
            String login = "root";
            String pwd = "password";

            initConnexion(driver, url, login, pwd);
        }

        return _connect;
    }
}