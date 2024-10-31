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

        setTitle("Auto Dealer - Dealer Dashboard");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(187, 187, 187));
        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\cariconrosu.png");
        setIconImage(icon.getImage());

        JLabel welcomeLabel = new JLabel("Welcome, Dealer!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(24, 166, 255));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBackground(new Color(187, 187, 187));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        addButtons(buttonPanel);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButtons(JPanel buttonPanel) {
        JButton addCarButton = createStyledButton("Add Car", new Color(24, 166, 255));
        addCarButton.addActionListener(e -> addCar());
        buttonPanel.add(addCarButton);

        JButton deleteCarButton = createStyledButton("Delete Car", new Color(24, 166, 255));
        deleteCarButton.addActionListener(e -> deleteCar());
        buttonPanel.add(deleteCarButton);

        JButton viewCarsButton = createStyledButton("View Cars", new Color(24, 166, 255));
        viewCarsButton.addActionListener(e -> viewCars());
        buttonPanel.add(viewCarsButton);

        JButton logoutButton = createStyledButton("Log Out", new Color(255, 78, 0));
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(logoutButton);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        return button;
    }

    private void viewCars() {
        String cars = carController.getAllCarsAsString();

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setBackground(new Color(240, 240, 240));
        textArea.setForeground(Color.BLACK);
        textArea.setText(cars);

        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Available Cars", JOptionPane.INFORMATION_MESSAGE);
    }

    private void logout() {
        dispose();
        new LoginApp(loginController, carController).setVisible(true);
    }

    private void addCar() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        ImageIcon originalIcon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\caricon.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        panel.add(imageLabel, gbc);

        String[] labels = { "Make:", "Model:", "Year:", "Price:", "Color:", "Mileage:" };
        JTextField[] fields = { new JTextField(15), new JTextField(15), new JTextField(15), new JTextField(15), new JTextField(15), new JTextField(15) };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridheight = 1;
            gbc.gridx = 1;
            gbc.gridy = i;
            JLabel label = new JLabel(labels[i]);
            label.setFont(label.getFont().deriveFont(12f));
            panel.add(label, gbc);

            gbc.gridx = 2;
            panel.add(fields[i], gbc);
        }

        int option = JOptionPane.showConfirmDialog(this, panel, "Add Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String make = fields[0].getText();
                String model = fields[1].getText();
                int year = Integer.parseInt(fields[2].getText());
                BigDecimal price = new BigDecimal(fields[3].getText());
                String color = fields[4].getText();
                int mileage = Integer.parseInt(fields[5].getText());

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
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        ImageIcon originalIcon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\cariconrosu.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        panel.add(imageLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        JLabel label = new JLabel("Enter Car ID to delete:");
        label.setFont(label.getFont().deriveFont(12f));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField carIdField = new JTextField(15);
        carIdField.setText("");
        panel.add(carIdField, gbc);

        int option = JOptionPane.showConfirmDialog(this, panel, "Delete Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(carIdField.getText());
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