package controller;

import Admin.AdminVariables;
import service.StatsService;
import view.LoginView;
import view.ThemeView;
import view.EditView;

public class DashboardController {
    static private DashboardController dashboardController;
    private StatsService statsService = StatsService.getInstance();

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
        // StatsService.getStatsAsCSVFormat();
    }
}
