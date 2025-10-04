package ru.itis.repository.User;

import lombok.AllArgsConstructor;
import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserRepositoryJdbcImpl implements UserRepository {

    private Connection connection;

    private static final String SQL_INSERT_DRIVER = "INSERT INTO driver(first_name, last_name, age) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_DRIVER_BY_CAR = "SELECT * FROM driver WHERE car IS NOT NULL";
    private static final String SQL_SELECT_DRIVER_BY_COUNTRY = "SELECT * FROM driver WHERE country IS NOT NULL";
    private static final String SQL_SELECT_DRIVER_BY_SALARY = "SELECT * FROM driver WHERE salary IS NOT NULL";

    @Override
    public void saveUserByFullInfo(String firstName, String lastName, int age) {
        try (PreparedStatement res = connection.prepareStatement(SQL_INSERT_DRIVER)) {
            res.setString(1, firstName);
            res.setString(2, lastName);
            res.setInt(3, age);

            res.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findByCar() {
        return findDrivers(SQL_SELECT_DRIVER_BY_CAR);
    }

    @Override
    public List<User> findByCountry() {
        return findDrivers(SQL_SELECT_DRIVER_BY_COUNTRY);
    }

    @Override
    public List<User> findBySalary() {
        return findDrivers(SQL_SELECT_DRIVER_BY_SALARY);
    }

    private List<User> findDrivers(String sql) {
        List<User> drivers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setAge(rs.getInt("age"));
                    user.setCar(rs.getString("car"));
                    user.setCountry(rs.getString("country"));
                    user.setSalary(rs.getDouble("salary"));
                    drivers.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

}
