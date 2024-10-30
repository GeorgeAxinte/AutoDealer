package ui;

import controllers.CarController;
import models.Car;
import utils.ErrorDialog;
import utils.SuccesDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class MainMenu extends JFrame {
    private CarController carController;

    public MainMenu(CarController carController) {
        this.carController = carController;

        setTitle("Auto Dealer - Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to AutoDealer!", SwingConstants.CENTER);
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

        JButton sellCarButton = new JButton("Sell Car");
        sellCarButton.addActionListener(e -> sellCar());
        buttonPanel.add(sellCarButton);

        JButton purchaseCarButton = new JButton("Purchase Car");
        purchaseCarButton.addActionListener(e -> purchaseCar());
        buttonPanel.add(purchaseCarButton);

        JButton reserveCarButton = new JButton("Reserve Car");
        reserveCarButton.addActionListener(e -> reserveCar());
        buttonPanel.add(reserveCarButton);
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

    private void sellCar() {
        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to sell:");
        String userId = JOptionPane.showInputDialog(this, "Enter Buyer User ID:");
        if (carId != null && userId != null) {
            try {
                int id = Integer.parseInt(carId);
                int buyerId = Integer.parseInt(userId);
                if (carController.sellCar(id, buyerId)) {
                    SuccesDialog.showSuccesDialog(this, "Car sold successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to sell car. It may not exist or be already sold.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Please enter valid IDs.");
            }
        }
    }

    private void purchaseCar() {
        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to purchase:");
        String buyerId = JOptionPane.showInputDialog(this, "Enter Your User ID:");
        if (carId != null && buyerId != null) {
            try {
                int id = Integer.parseInt(carId);
                int userId = Integer.parseInt(buyerId);
                if (carController.purchaseCar(id, userId)) {
                    SuccesDialog.showSuccesDialog(this, "Car purchased successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to purchase car. It may not be available.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Please enter valid IDs.");
            }
        }
    }

    private void reserveCar() {
        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to reserve:");
        if (carId != null) {
            try {
                int id = Integer.parseInt(carId);
                if (carController.reserveCar(id)) {
                    SuccesDialog.showSuccesDialog(this, "Car reserved successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to reserve car. It may not be available.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Please enter a valid car ID.");
            }
        }
    }

    public static void main(String[] args) {
        CarController carController = new CarController();
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu(carController);
            mainMenu.setVisible(true);
        });
    }
}
