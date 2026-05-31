package com.spa;

import com.spa.ui.LoginFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Application entry point. It starts the login screen.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception exception) {
                System.out.println("Default look and feel will be used.");
            }
            new LoginFrame().setVisible(true);
        });
    }
}
