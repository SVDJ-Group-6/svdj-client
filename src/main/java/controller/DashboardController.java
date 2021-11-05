package controller;

import Admin.AdminVariables;
import service.StatsService;
import view.LoginView;
import view.ThemeView;
import view.EditView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DashboardController {
    static private DashboardController dashboardController;

    static public DashboardController getInstance() {
        if (dashboardController == null) {
            dashboardController = new DashboardController();
        }
        return dashboardController;
    }

    public void navigateColorView() {
        AdminVariables.stage.setScene(new ThemeView().getThemeScene());
    }

    public void navigateEditView() {
        AdminVariables.stage.setScene(new EditView().getEditScene());
    }

    public void logout() {
        AdminVariables.token = null;
        AdminVariables.stage.setScene(new LoginView().getLoginScene());
    }

    public void saveStats() {
        try {
            String url = AdminVariables.API_URL + "/api/stats/csv";
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
