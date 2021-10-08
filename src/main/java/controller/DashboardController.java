package controller;

public class DashboardController {

    private static DashboardController dashboardController;

    public void navigateThemeView(){}
    public void navigateEditView(){}
    public void saveStats(){}
    //Todo
    public static DashboardController getInstance(){
        return dashboardController;
    }
}
