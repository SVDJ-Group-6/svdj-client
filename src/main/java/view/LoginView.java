package view;

import controller.LoginController;
import model.Login;
import observer.LoginObserver;

public class LoginView implements LoginObserver {
    private LoginController loginController;

    @Override
    public void update(Login login) {

    }
}
