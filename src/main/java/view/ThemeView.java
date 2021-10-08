package view;

import controller.ThemeController;
import model.Theme;
import observer.ThemeObserver;

public class ThemeView implements ThemeObserver {

    private ThemeController themeController;
    private Theme theme;

    @Override
    public void update(Theme theme) {

    }
}
