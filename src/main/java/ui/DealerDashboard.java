package ui;

import controllers.CarController;
import models.Car;
import utils.ErrorDialog;
import utils.SuccesDialog;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class DealerDashboard extends JFrame {
    private CarController carController;

    public DealerDashboard(CarController carController) {
        this.carController = carController;

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
    }

    private void addCar() {
        JTextField makeField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] message = {
                "Make:", makeField,
                "Model:", modelField,
                "Year:", yearField,
                "Price:", priceField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Car", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String make = makeField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                BigDecimal price = new BigDecimal(priceField.getText());

                Car car = new Car(make, model, year, price);
                if (carController.addCar(car, 1)) {
                    SuccesDialog.showSuccesDialog(this, "Car added successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to add car.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Invalid input. Please enter correct values.");
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
                ErrorDialog.showErrorDialog(this, "Please enter a valid car ID.");
            }
        }
    }
}
