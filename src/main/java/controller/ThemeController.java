package controller;

import DAO.ThemeDAO;
import model.Theme;
import view.DashboardView;
import Admin.AdminVariables;

import javafx.scene.paint.Color;
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
        return new Theme(
                "#ffffff",
                "#000000",
                "#989898",
                "#9cc2d4",
                "#afccdb",
                "#e4f6ff",
                "#ffffff"
        );
    }

    public void submitColors(Theme theme) throws IOException {
        themeDAO.postTheme(theme);
    }

    public void navigateBack() {
        AdminVariables.stage.setScene(new DashboardView().getDashboardScene());
    }

    public String toRGBCode(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    public static ThemeController getInstance() {
        if (themeController == null) {
            themeController = new ThemeController();
        }
        return themeController;
    }
}
