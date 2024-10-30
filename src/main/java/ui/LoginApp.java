package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.LoginController;
import controllers.CarController;
import utils.ErrorDialog;
import utils.SuccesDialog;

public class LoginApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private LoginController loginController;
    private CarController carController;

    public LoginApp(LoginController loginController, CarController carController) {
        this.loginController = loginController;
        this.carController = carController;

        setTitle("Auto Dealer - Login");
        setSize(300, 200);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(187, 187, 187));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        addComponents(gbc);
        styleComponents();
        addLoginAction();
        addRegisterAction();
    }

    private void addComponents(GridBagConstraints gbc) {
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(loginButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        add(registerButton, gbc);
    }

    private void styleComponents() {
        loginButton.setBackground(new Color(255, 78, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        registerButton.setBackground(new Color(24, 166, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));

        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void addLoginAction() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    ErrorDialog.showErrorDialog(LoginApp.this, "Username or password is missing!");
                    return;
                }

                if (loginController.login(username, password)) {
                    SuccesDialog.showSuccesDialog(LoginApp.this, "Login successful!");
                    dispose();
                    new MainMenu(carController).setVisible(true);
                } else {
                    ErrorDialog.showErrorDialog(LoginApp.this, "Invalid username or password!");
                }
            }
        });
    }

    private void addRegisterAction() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen(loginController).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        CarController carController = new CarController();

        loginController.addUser(new models.User("george", utils.EncryptionUtil.hashPassword("parola")));

        SwingUtilities.invokeLater(() -> {
            LoginApp app = new LoginApp(loginController, carController);
            app.setVisible(true);
        });
    }
}
