package ru.clevertec.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionPoolManager {

    private static final BasicDataSource dataSource;
    private static final String URL = "url";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "driver";
    private static final String CONFIG_DB = "config.yaml";

    private static Map<String, String> readConfigDBYaml() {

        Yaml yaml = new Yaml();
        InputStream inputStream = ConnectionPoolManager.class.getClassLoader()
                .getResourceAsStream(CONFIG_DB);

        return yaml.load(inputStream);
    }

    private static final Map<String, String> STRING_MAP = readConfigDBYaml();

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(STRING_MAP.get(URL));
        dataSource.setUsername(STRING_MAP.get(USER_NAME));
        dataSource.setPassword(STRING_MAP.get(PASSWORD));
        dataSource.setDriverClassName(STRING_MAP.get(DRIVER));
        dataSource.setDefaultAutoCommit(false);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
