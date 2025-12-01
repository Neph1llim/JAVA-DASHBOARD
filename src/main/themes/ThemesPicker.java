/*
TODO:

fix code 
clean code 
fuck me 

*/

package main.themes;

import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ThemesPicker {
    //    private static Themes currentTheme;

    public enum ThemeType {
        LIGHT,
        DARK,
        CUSTOM
    }
    
    public static void setTheme(ThemeType type) {
        /*
        switch (type) {
            case LIGHT -> currentTheme = Themes.lightTheme();
            case DARK -> currentTheme =  Themes.darkTheme(); 
            default -> throw new IllegalArgumentException("Custom theme must be set manually.");
        }
        */
        applyThemeToUI();
    }
    /*
    public static void setCustomTheme(CustomTheme theme) {
        //currentTheme = (Themes) theme;
        applyThemeToUI();
    }

    public static Themes get() {
        return currentTheme;
    }
    */
    private static void applyThemeToUI() {
        /*
        Themes theme = currentTheme;
        
        UIManager.put("Panel.background", theme.getBackgroundColor());
        UIManager.put("Label.foreground", theme.getTextColor());
        UIManager.put("Button.background", theme.getAccentColor());
        UIManager.put("Button.foreground", theme.getTextColor());
        UIManager.put("Button.font", theme.getFont());
        UIManager.put("Label.font", theme.getFont());
        UIManager.put("TextField.font", theme.getFont());
        UIManager.put("TextField.foreground", theme.getTextColor());
        UIManager.put("TextField.background", theme.getBackgroundColor());
        */
        // Refresh UI
        for (Window window : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
    }
}
