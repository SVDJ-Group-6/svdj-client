package controller;

import DAO.ThemeDAO;
import model.Theme;
import view.DashboardView;
import Admin.AdminVariables;

import java.io.IOException;

public class ThemeController {
    private static ThemeController themeController;
    private ThemeDAO themeDAO = ThemeDAO.getInstance();

    public Theme getTheme() {
        try {
            return themeDAO.getTheme();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO If theme fetch fails, return default theme
        return new Theme();
    }

    public void submitColors(Theme theme) throws IOException {
        themeDAO.postTheme(theme);
    }

    public void navigateBack() {
        AdminVariables.stage.setScene(new DashboardView().Dashboard());
    }

    public static ThemeController getInstance() {
        if (themeController == null) {
            themeController = new ThemeController();
        }
        return themeController;
    }
}
