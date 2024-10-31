package controllers;

import models.Car;
import database.DatabaseConnection;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class CarController {

    public boolean addCar(Car car, int userId) {
        String sql = "INSERT INTO Cars (make, model, year, price, color, mileage, userId, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'available')";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, car.getMake());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setBigDecimal(4, car.getPrice());
            statement.setString(5, car.getColor());
            statement.setInt(6, car.getMileage());
            statement.setInt(7, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                BigDecimal price = rs.getBigDecimal("price");
                int mileage = rs.getInt("mileage");
                String color = rs.getString("color");
                String status = rs.getString("status");

                Car car = new Car(id, make, model, year, price, color, mileage, status, "");
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public String getAllCarsAsString() {
        List<Car> cars = getAllCars();

        if (cars.isEmpty()) {
            return "No cars available.";
        }

        StringBuilder stringBuilder = new StringBuilder("Available Cars:\n\n");
        for (Car car : cars) {
            stringBuilder.append("ID: ").append(car.getId())
                    .append(" | ").append(car.getMake()).append(" ")
                    .append(car.getModel()).append(" | Year: ")
                    .append(car.getYear()).append(" | Price: $")
                    .append(car.getPrice()).append(" | Color: ")
                    .append(car.getColor()).append(" | Mileage: ")
                    .append(car.getMileage()).append(" km | Status: ")
                    .append(car.getStatus()).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean deleteCar(int carId) {
        String sql = "DELETE FROM Cars WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean purchaseCar(int carId, int userId) {
        String sql = "UPDATE Cars SET status = 'sold', userId = ? WHERE id = ? AND status = 'available'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reserveCar(int carId) {
        String sql = "UPDATE Cars SET status = 'reserved' WHERE id = ? AND status = 'available'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sellCar(int carId, int userId) {
        String sql = "UPDATE Cars SET status = 'available' WHERE id = ? AND userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carId);
            statement.setInt(2, userId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
