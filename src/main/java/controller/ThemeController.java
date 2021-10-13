package controller;

import ClientApplication.ClientVariables;
import DAO.ThemeDAO;
import model.Theme;

import java.io.IOException;

public class ThemeController {
    private static ThemeController themeController;
    private ThemeDAO themeDAO = ThemeDAO.getInstance();

    public Theme getTheme(){
        try {
            return themeDAO.getTheme();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO If theme fetch fails, return default theme
        return new Theme();
    }

    public static ThemeController getInstance() {
        if (themeController == null) {
            themeController = new ThemeController();
        }
        return themeController;
    }
}
