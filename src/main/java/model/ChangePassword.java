package model;

import observable.ChangePasswordObservable;
import observer.ChangePasswordObserver;

import java.util.ArrayList;

public class ChangePassword implements ChangePasswordObservable {
    private ArrayList<ChangePasswordObserver> observers = new ArrayList<>();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    @Override
    public void registerObserver(ChangePasswordObserver changePasswordObserver) {
        observers.add(changePasswordObserver);
    }

    @Override
    public void unregisterObserver(ChangePasswordObserver changePasswordObserver) {
        observers.remove(changePasswordObserver);
    }

    @Override
    public void notifyObservers() {
        for (ChangePasswordObserver changePasswordObserver: observers) {
            changePasswordObserver.update(this);
        }
    }
}
