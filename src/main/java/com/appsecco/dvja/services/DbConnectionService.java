package com.appsecco.dvja.services;

import com.appsecco.dvja.Config;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
import java.util.Enumeration;

public class DbConnectionService {
    public static String driver = "com.mysql.jdbc.Driver";
    public static Connection cnn;
    private static Config config = null;

    public static void setConfig(Config config) {
        DbConnectionService.config = config;
    }

    public static void cleanup() {
        try {
            // Unregister JDBC drivers
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }

            // Stop MySQL cleanup thread
            AbandonedConnectionCleanupThread.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean open() {
        try {
            if (config == null) {
                DbConnectionService.setConfig(new Config());
            }
            if (cnn == null || cnn.isClosed()) {
                Class.forName(driver);
                String url = "jdbc:mysql://" + config.getProperty("mysql.host") + "/" + config.getProperty("mysql.db") + "?autoReconnect=true&useSSL=false";

                cnn = DriverManager.getConnection(url, config.getProperty("mysql.username"), config.getProperty("mysql.password"));
                return true;
            }

        } catch (SQLException ex) {
            //xu ly ex
        } catch (ClassNotFoundException ex) {
            //xu ly ex

        }

        return false;

    }

    public static void close() {
        try {
            if (cnn != null && !cnn.isClosed()) {

                cnn.close();
            }
        } catch (SQLException ex) {
            //xu ly ex
        }
    }

    public static void close(PreparedStatement ps) {
        try {

            if (ps != null && !ps.isClosed()) {

                ps.close();

            }
        } catch (SQLException ex) {
            //xu ly ex
        }
        close();
    }

    public static void close(PreparedStatement ps, ResultSet rs) {
        try {

            if (rs != null && !rs.isClosed()) {

                rs.close();

            }
        } catch (SQLException ex) {
            //xu ly ex
        }

        close(ps);
    }
}
