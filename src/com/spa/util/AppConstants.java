package com.spa.util;

import java.awt.Color;
import java.awt.Font;

/**
 * Stores common application constants used by the GUI and services.
 */
public final class AppConstants {
    public static final String APP_NAME = "Student Performance Analyzer";
    public static final String REPORT_FOLDER = "reports";

    public static final Color PRIMARY = new Color(32, 86, 173);
    public static final Color PRIMARY_DARK = new Color(18, 48, 99);
    public static final Color BACKGROUND = new Color(239, 245, 255);
    public static final Color CARD = Color.WHITE;
    public static final Color TEXT = new Color(34, 44, 62);
    public static final Color SUCCESS = new Color(22, 138, 78);
    public static final Color WARNING = new Color(211, 128, 12);
    public static final Color DANGER = new Color(190, 46, 46);

    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);

    private AppConstants() {
        // Utility class: prevent object creation.
    }
}
