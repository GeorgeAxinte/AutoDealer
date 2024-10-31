package ui;

import controllers.CarController;
import controllers.LoginController;
import models.Car;
import utils.ErrorDialog;
import utils.SuccesDialog;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class DealerDashboard extends JFrame {
    private CarController carController;
    private LoginController loginController;

    public DealerDashboard(CarController carController, LoginController loginController) {
        this.carController = carController;
        this.loginController = loginController;

        setTitle("Dealer Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, Dealer!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        addButtons(buttonPanel);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButtons(JPanel buttonPanel) {
        JButton addCarButton = new JButton("Add Car");
        addCarButton.addActionListener(e -> addCar());
        buttonPanel.add(addCarButton);

        JButton deleteCarButton = new JButton("Delete Car");
        deleteCarButton.addActionListener(e -> deleteCar());
        buttonPanel.add(deleteCarButton);

        JButton viewCarsButton = new JButton("View Cars");
        viewCarsButton.addActionListener(e -> viewCars());
        buttonPanel.add(viewCarsButton);

        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);
    }

    private void viewCars() {
        String cars = carController.getAllCarsAsString();
        JOptionPane.showMessageDialog(this, cars, "Available Cars", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        dispose();
        new LoginApp(new LoginController(), carController).setVisible(true);
    }


    private void addCar() {
        JTextField makeField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField mileageField = new JTextField();

        Object[] message = {
                "Make:", makeField,
                "Model:", modelField,
                "Year:", yearField,
                "Price:", priceField,
                "Color:", colorField,
                "Mileage:", mileageField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Car", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String make = makeField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                BigDecimal price = new BigDecimal(priceField.getText());
                String color = colorField.getText();
                int mileage = Integer.parseInt(mileageField.getText());

                Car car = new Car(make, model, year, price, color, mileage);
                if (carController.addCar(car, 1)) {
                    SuccesDialog.showSuccesDialog(this, "Car added successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to add car.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Invalid input. Please enter correct values for year, price, and mileage.");
            }
        }
    }



    private void deleteCar() {
        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to delete:");
        if (carId != null) {
            try {
                int id = Integer.parseInt(carId);
                if (carController.deleteCar(id)) {
                    SuccesDialog.showSuccesDialog(this, "Car deleted successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to delete car. It may not exist.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Invalid ID. Please enter a number.");
            }
        }
    }
}