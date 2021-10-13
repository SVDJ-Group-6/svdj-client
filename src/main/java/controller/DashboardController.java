package controller;

import Admin.AdminVariables;
import service.StatsService;
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
        AdminVariables.stage.setScene(new ThemeView().Theme());
    }

    public void navigateEditView() {
        AdminVariables.stage.setScene(new EditView().Edit());
    }

    public void saveStats() {
        // StatsService.getStatsAsCSVFormat();
    }
}
