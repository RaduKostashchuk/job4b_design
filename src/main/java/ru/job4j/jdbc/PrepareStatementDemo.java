package ru.job4j.jdbc;

import javax.swing.plaf.SliderUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() {
        initConnection();
    }

    public void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/idea_db";
            String login = "postgres";
            String password = "password";
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into cities(name, population) values(?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "update cities set name = ?, population = ? where id = ?"
        )) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(City city) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "delete from cities where id = ?"
        )) {
            statement.setInt(1, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from cities"
        )) {
            try (ResultSet rset = statement.executeQuery()) {
                while (rset.next()) {
                    result.add(new City(
                            rset.getInt("id"),
                            rset.getString("name"),
                            rset.getInt("population")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        PrepareStatementDemo obj = new PrepareStatementDemo();
        obj.insert(new City(1, "Tomsk", 300000));
        obj.insert(new City(2, "Omsk", 600000));
        System.out.println(obj.update(new City(1, "Tomsk", 350000)));
        obj.delete(new City(1, "Tomsk", 300000));
        System.out.println(obj.findAll());
    }

}
