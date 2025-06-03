package main;

import com.formdev.flatlaf.FlatLightLaf;
import view.LoginFrame;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    // konstanta untuk UI
    private static final class UIConstants {
        // warna tema 
        static final Color PRIMARY_BLUE = new Color(60, 130, 220);
        static final Color LIGHT_GRAY = new Color(250, 250, 250);
        static final Color DARK_GRAY = new Color(50, 50, 50);
        static final Color SELECTION_BLUE = new Color(230, 240, 255);
        
        // konfigurasi border radius
        static final int BUTTON_ARC = 20;
        static final int COMPONENT_ARC = 15;
        static final int TEXT_COMPONENT_ARC = 10;
        
        // font konfigurasi
        static final String FONT_FAMILY = "Segoe UI";
        static final int FONT_SIZE = 14;
    }
    
    public static void main(String[] args) {
        // konfig sistem properti 
        configureSystemProperties();
        
        // setup look and feel
        setupLookAndFeel();
        
        // jalankan programdi event dispatch thread
        SwingUtilities.invokeLater(App::startApplication);
    }
    
    //konfig sistem properti untuk optimasi program
    private static void configureSystemProperties() {
        // anti aliasing untuk rendering text yang lebih halus
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        
        // optimasi rendering
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.d3d", "false");
    }
    
    // atur look and feel program pake flatlaf
    private static void setupLookAndFeel() {
        try {
            // setup flatlaf 
            FlatLightLaf.setup();
            
            // terapkan kustomisasi ui
            customizeUIComponents();
            
            // set look and feel
            UIManager.setLookAndFeel(new FlatLightLaf());
            
            LOGGER.info("FlatLaf berhasil dimuat dan dikonfigurasi");
            
        } catch (UnsupportedLookAndFeelException e) {
            LOGGER.log(Level.SEVERE, "Gagal memuat FlatLaf Look and Feel", e);
            setupFallbackLookAndFeel();
        }
    }
    
    // kustom komponen ui
    private static void customizeUIComponents() {
        // konfig tombol
        UIManager.put("Button.arc", UIConstants.BUTTON_ARC);
        UIManager.put("Button.background", UIConstants.PRIMARY_BLUE);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.focusedBackground", UIConstants.PRIMARY_BLUE.darker());
        UIManager.put("Button.hoverBackground", UIConstants.PRIMARY_BLUE.brighter());
        
        // konfig komponen umum
        UIManager.put("Component.arc", UIConstants.COMPONENT_ARC);
        UIManager.put("TextComponent.arc", UIConstants.TEXT_COMPONENT_ARC);
        
        // konfig tabel
        UIManager.put("Table.selectionBackground", UIConstants.SELECTION_BLUE);
        UIManager.put("Table.selectionForeground", UIConstants.DARK_GRAY);
        UIManager.put("Table.gridColor", new Color(220, 220, 220));
        
        // konfig panel dan background
        UIManager.put("Panel.background", UIConstants.LIGHT_GRAY);
        UIManager.put("Label.foreground", UIConstants.DARK_GRAY);
        
        // konfig font
        Font defaultFont = new Font(UIConstants.FONT_FAMILY, Font.PLAIN, UIConstants.FONT_SIZE);
        UIManager.put("defaultFont", defaultFont);
        
        // font untuk komponen spesifik
        UIManager.put("Button.font", defaultFont.deriveFont(Font.BOLD));
        UIManager.put("Label.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        
        // konfig input field
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", UIConstants.DARK_GRAY);
        UIManager.put("TextField.selectionBackground", UIConstants.SELECTION_BLUE);
    }
    
    private static void setupFallbackLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            LOGGER.info("Menggunakan System Look and Feel sebagai fallback");
        } catch (Exception fallbackException) {
            LOGGER.log(Level.WARNING, "Gagal memuat System", fallbackException);
        }
    }
    
    // mulai program
    private static void startApplication() {
        try {
            // buat dan tampilkan loginframe
            LoginFrame loginFrame = new LoginFrame();
            
            // konfig frame utama
            configureMainFrame(loginFrame);
            
            // tampilkan program
            loginFrame.setVisible(true);
            
            LOGGER.info("Aplikasi berhasil dimulai");
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Gagal memulai aplikasi", e);
            
            //tampilkan error kepada user
            showErrorDialog("Gagal memulai aplikasi: " + e.getMessage());
        }
    }
    
    // konfig pengaturan dasar untuk frame utama dan frame yang akan dikonfig
    private static void configureMainFrame(JFrame frame) {
        // tetapkan operasi default saat window ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // center frame di layar
        frame.setLocationRelativeTo(null);
        
    }
    
    //menampilkan error kepada user 
    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Error - Aplikasi",
            JOptionPane.ERROR_MESSAGE
        );
    }
}




