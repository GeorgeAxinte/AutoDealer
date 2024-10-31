package ui;

import controllers.CarController;
import controllers.LoginController;
import utils.ErrorDialog;
import utils.SuccesDialog;

import javax.swing.*;
import java.awt.*;

public class ClientDashboard extends JFrame {
    private CarController carController;
    private LoginController loginController;
    private int userId;

    public ClientDashboard(CarController carController, LoginController loginController, int userId) {
        this.carController = carController;
        this.loginController = loginController;
        this.userId = userId;

        setTitle("Client Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, Client!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        addButtons(buttonPanel);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButtons(JPanel buttonPanel) {
        JButton buyCarButton = new JButton("Purchase Car");
        buyCarButton.addActionListener(e -> purchaseCar());
        buttonPanel.add(buyCarButton);

        JButton reserveCarButton = new JButton("Reserve Car");
        reserveCarButton.addActionListener(e -> reserveCar());
        buttonPanel.add(reserveCarButton);

        JButton sellCarButton = new JButton("Sell Car");
        sellCarButton.addActionListener(e -> sellCar());
        buttonPanel.add(sellCarButton);

        JButton viewCarsButton = new JButton("View Cars");
        viewCarsButton.addActionListener(e -> viewCars());
        buttonPanel.add(viewCarsButton);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(e -> logOut());
        buttonPanel.add(logOutButton);
    }

    private void viewCars() {
        String cars = carController.getAllCarsAsString();
        JOptionPane.showMessageDialog(this, cars, "Available Cars", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logOut() {
        dispose();
        new LoginApp(loginController, carController).setVisible(true);
    }

    private void purchaseCar() {
        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to purchase:");
        if (carId != null) {
            try {
                int id = Integer.parseInt(carId);
                if (carController.purchaseCar(id, userId)) {
                    SuccesDialog.showSuccesDialog(this, "Car purchased successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to purchase car. It may not be available.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Please enter a valid car ID.");
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

    private void sellCar() {
        String carIdStr = JOptionPane.showInputDialog(this, "Enter Car ID to sell:");
        if (carIdStr != null) {
            try {
                int carId = Integer.parseInt(carIdStr);

                if (carController.sellCar(carId, userId)) {
                    SuccesDialog.showSuccesDialog(this, "Car sold successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to sell car. You may not be the owner or the car may not exist.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Please enter a valid car ID.");
            }
        }
    }


}