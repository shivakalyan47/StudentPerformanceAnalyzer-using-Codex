package com.spa.ui;

import com.spa.util.AppConstants;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Login module. Uses a simple fixed username and password for demonstration.
 */
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle(AppConstants.APP_NAME + " - Login");
        setSize(760, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 430));

        add(createMainPanel());
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new GridBagLayout());

        JPanel card = new JPanel(new BorderLayout(10, 15));
        card.setBackground(AppConstants.CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 229, 245)),
                BorderFactory.createEmptyBorder(30, 35, 30, 35)
        ));
        card.setPreferredSize(new Dimension(420, 320));

        JLabel titleLabel = new JLabel("Student Performance Analyzer");
        titleLabel.setFont(AppConstants.TITLE_FONT);
        titleLabel.setForeground(AppConstants.PRIMARY_DARK);

        JLabel hintLabel = new JLabel("Login with username: admin and password: admin");
        hintLabel.setFont(AppConstants.NORMAL_FONT);
        hintLabel.setForeground(new Color(89, 99, 120));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppConstants.CARD);
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(hintLabel, BorderLayout.SOUTH);

        card.add(headerPanel, BorderLayout.NORTH);
        card.add(createFormPanel(), BorderLayout.CENTER);
        card.add(createButtonPanel(), BorderLayout.SOUTH);

        mainPanel.add(card);
        return mainPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(AppConstants.CARD);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        usernameField = new JTextField();
        usernameField.setFont(AppConstants.NORMAL_FONT);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        passwordField = new JPasswordField();
        passwordField.setFont(AppConstants.NORMAL_FONT);
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(AppConstants.CARD);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(AppConstants.BUTTON_FONT);
        loginButton.setBackground(AppConstants.PRIMARY);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(event -> login());

        buttonPanel.add(loginButton);
        return buttonPanel;
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if ("admin".equals(username) && "admin".equals(password)) {
            new DashboardFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            GradientPaint paint = new GradientPaint(0, 0, new Color(229, 240, 255),
                    getWidth(), getHeight(), new Color(193, 214, 255));
            graphics2D.setPaint(paint);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
