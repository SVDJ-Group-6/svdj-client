package model;

import observable.LoginObservable;
import observer.LoginObserver;

public class Login implements LoginObservable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void registerObserver(LoginObserver loginObserver) {

    }

    @Override
    public void unregisterObserver(LoginObserver loginObserver) {

    }

    @Override
    public void notifyObservers() {

    }
}
