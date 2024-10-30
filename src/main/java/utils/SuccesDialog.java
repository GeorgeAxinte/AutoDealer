package utils;

import javax.swing.*;
import java.awt.*;

public class SuccesDialog {

    public static void showSuccesDialog(Component parent, String message) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(parent);
        JDialog dialog = new JDialog(frame, "Success", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 204));
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(153, 0, 0));
        panel.add(label, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(204, 0, 0));
        okButton.setForeground(Color.BLACK);
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.addActionListener(e -> dialog.dispose());

        panel.add(okButton, BorderLayout.SOUTH);

        dialog.getContentPane().add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
