package controller;

public class HomeController {

    private static HomeController homeController;

    public void startQuiz(){}

    //Todo
    public static HomeController getInstance() {
        return homeController;
    }
}
