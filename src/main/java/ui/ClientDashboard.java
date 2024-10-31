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

        setTitle("Auto Dealer - Client Dashboard");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(187, 187, 187));
        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\cariconrosu.png");
        setIconImage(icon.getImage());

        JLabel welcomeLabel = new JLabel("Welcome, Client!", SwingConstants.CENTER);
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
        JButton buyCarButton = createStyledButton("Purchase Car", new Color(24, 166, 255));
        buyCarButton.addActionListener(e -> purchaseCar());
        buttonPanel.add(buyCarButton);

        JButton reserveCarButton = createStyledButton("Reserve Car", new Color(24, 166, 255));
        reserveCarButton.addActionListener(e -> reserveCar());
        buttonPanel.add(reserveCarButton);

        JButton sellCarButton = createStyledButton("Sell Car", new Color(24, 166, 255));
        sellCarButton.addActionListener(e -> sellCar());
        buttonPanel.add(sellCarButton);

        JButton viewCarsButton = createStyledButton("View Cars", new Color(24, 166, 255));
        viewCarsButton.addActionListener(e -> viewCars());
        buttonPanel.add(viewCarsButton);

        JButton logOutButton = createStyledButton("Log Out", new Color(255, 78, 0));
        logOutButton.addActionListener(e -> logOut());
        buttonPanel.add(logOutButton);
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

    private void logOut() {
        dispose();
        new LoginApp(loginController, carController).setVisible(true);
    }

    private void purchaseCar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField carIdField = new JTextField(15);

        ImageIcon originalIcon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\caricon.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);

        panel.add(imageLabel);
        panel.add(new JLabel("Enter Car ID to purchase:"));
        panel.add(carIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Purchase Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int carId = Integer.parseInt(carIdField.getText());
                if (carController.purchaseCar(carId, userId)) {
                    SuccesDialog.showSuccesDialog(this, "Car purchased successfully!");
                } else {
                    ErrorDialog.showErrorDialog(this, "Failed to purchase car. Please check if the car exists or if you have sufficient funds.");
                }
            } catch (NumberFormatException e) {
                ErrorDialog.showErrorDialog(this, "Please enter a valid car ID.");
            }
        }
    }

    private void reserveCar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField carIdField = new JTextField(15);

        ImageIcon originalIcon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\caricon.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);

        panel.add(imageLabel);
        panel.add(new JLabel("Enter Car ID to reserve:"));
        panel.add(carIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Reserve Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(carIdField.getText());
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
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField carIdField = new JTextField(15);

        ImageIcon originalIcon = new ImageIcon("C:\\Program Files (x86)\\AutoDealer\\src\\cariconrosu.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);

        panel.add(imageLabel);
        panel.add(new JLabel("Enter Car ID to sell:"));
        panel.add(carIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Sell Car", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int carId = Integer.parseInt(carIdField.getText());
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