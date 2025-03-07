package com.appsecco.dvja;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author qubu
 */
public class Config {

    Properties configFile;

    public Config() {

        configFile = new java.util.Properties();
        try {
            String path = this.getClass().getClassLoader().getResource("").getPath();
            String fullPath = URLDecoder.decode(path, "UTF-8");
            String pathArr[] = fullPath.split("/classes/");
            fullPath = pathArr[0];
            System.out.println(fullPath);
            configFile.load(new FileInputStream(fullPath + "/config.properties"));
        } catch (Exception eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = this.configFile.getProperty(key);
        return value;
    }

 public static void main(String[] args) {
        Config config = new Config();
    String url = "jdbc:mysql://"+config.getProperty("mysql.host")+"/"+config.getProperty("mysql.db")+"?autoReconnect=true&useSSL=false";

     System.out.printf("url: %s username: %s password: %s",url, config.getProperty("mysql.username"), config.getProperty("mysql.password"));
}

}

