package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String query = String.format("create table if not exists %s()", tableName);
        executeQuery(query);
    }

    public void dropTable(String tableName) {
        String query = String.format("drop table if exists %s", tableName);
        executeQuery(query);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String query = String.format("alter table if exists "
                + "%s add if not exists %s %s;", tableName, columnName, type);
        executeQuery(query);
    }

    public void dropColumn(String tableName, String columnName) {
        String query = String.format("alter table if exists "
                + "%s drop column if exists %s;", tableName, columnName);
        executeQuery(query);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String query = String.format("alter table if exists "
                + "%s rename column %s to %s;", tableName, columnName, newColumnName);
        executeQuery(query);

    }

    private void executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        Properties properties = new Properties();
        config.load();
        properties.setProperty("driver", config.value("hibernate.connection.driver_class"));
        properties.setProperty("url", config.value("hibernate.connection.url"));
        properties.setProperty("username", config.value("hibernate.connection.username"));
        properties.setProperty("password", config.value("hibernate.connection.password"));
        try {
            TableEditor tableEditor = new TableEditor(properties);
            tableEditor.createTable("demo");
            tableEditor.addColumn("demo", "id", "serial primary key");
            tableEditor.renameColumn("demo", "id", "newid");
            System.out.println(getTableScheme(tableEditor.connection, "demo"));
            tableEditor.dropColumn("demo", "newid");
            tableEditor.dropTable("demo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
