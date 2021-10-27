package model;

import observable.LoginObservable;
import observer.LoginObserver;

import java.util.ArrayList;

public class Login implements LoginObservable {

    private String message;
    private final ArrayList<LoginObserver> observers = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    @Override
    public void registerObserver(LoginObserver loginObserver) {
        observers.add(loginObserver);
    }

    @Override
    public void unregisterObserver(LoginObserver loginObserver) {
        observers.remove(loginObserver);
    }

    @Override
    public void notifyObservers() {
        for(LoginObserver loginObserver : observers) {
            loginObserver.update(this);
        }
    }
}
