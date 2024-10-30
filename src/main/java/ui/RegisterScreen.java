package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.LoginController;
import utils.ErrorDialog;
import utils.SuccesDialog;

public class RegisterScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    private LoginController loginController;

    public RegisterScreen(LoginController loginController) {
        this.loginController = loginController;

        setTitle("Auto Dealer - Register");
        setSize(300, 200);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(187, 187, 187));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        registerButton = new JButton("Register");

        addComponents(gbc);
        styleComponents();
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
        add(registerButton, gbc);
    }

    private void styleComponents() {
        registerButton.setBackground(new Color(255, 78, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));

        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void addRegisterAction() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    ErrorDialog.showErrorDialog(RegisterScreen.this, "Username or password is missing!");
                    return;
                }

                if (loginController.register(username, password)) {
                    SuccesDialog.showSuccesDialog(RegisterScreen.this, "Registration successful!");
                    dispose();
                } else {
                    ErrorDialog.showErrorDialog(RegisterScreen.this, "Username already exists!");
                }
            }
        });
    }
}
