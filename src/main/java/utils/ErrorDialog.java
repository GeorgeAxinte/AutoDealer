package utils;

import javax.swing.*;
import java.awt.*;

public class ErrorDialog {
    public static void showErrorDialog(Component parent, String message) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Eroare", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(parent);
        dialog.getContentPane().setBackground(new Color(255, 204, 204));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel messageLabel = new JLabel(message);
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0; gbc.gridy = 0;
        dialog.add(messageLabel, gbc);

        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(204, 0, 0));
        okButton.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(okButton, gbc);

        okButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
}
