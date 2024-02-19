package edu.school21.chat.models.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceCreate {
    private static final String propertyFilename = "src/main/resources/jdbc-connect.properties";
    Properties properties;

    private void setProperties() {
        try {
            properties = new Properties();
            properties.load(new FileReader(propertyFilename));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public DataSource createDataSource() throws SQLException {
        setProperties();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("URL"));
        hikariConfig.setUsername(properties.getProperty("USER"));
        hikariConfig.setPassword(properties.getProperty("PASSWORD"));
        try {
            return new HikariDataSource(hikariConfig);
        } catch (HikariPool.PoolInitializationException | IllegalArgumentException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
