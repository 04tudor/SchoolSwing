package Views;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(2, 253, 102)); // Set background color

        JLabel welcomeLabel = new JLabel("School Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(new Color(50, 50, 50));

        JLabel property = new JLabel("Coretchi Tudor P-2042");
        property.setFont(new Font("Arial", Font.BOLD, 10)); // Increased font size
        property.setHorizontalAlignment(SwingConstants.CENTER);
        property.setForeground(new Color(50, 50, 50)); // Adjusted text color

        ImageIcon logoIcon = new ImageIcon("src/main/resources/photo/logoSchool.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(welcomeLabel, BorderLayout.NORTH);
        contentPanel.add(property, BorderLayout.CENTER);
        contentPanel.add(logoLabel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
    }
}
