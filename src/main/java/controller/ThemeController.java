package controller;

import DAO.ThemeDAO;
import model.Theme;

public class ThemeController {
    private static ThemeController themeController;
    private ThemeDAO themeDAO;

    //Todo what does this return ?
    public Theme getTheme(){
        return new Theme();
    }
    public void saveTheme(){}
    //Todo
    public static ThemeController getInstance() {
        return themeController;
    }
}
