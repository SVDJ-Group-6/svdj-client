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
    public void registerObserver(ChangePasswordObserver recoveryCodeObserver) {
        observers.add(recoveryCodeObserver);
    }

    @Override
    public void unregisterObserver(ChangePasswordObserver recoveryCodeObserver) {
        observers.remove(recoveryCodeObserver);
    }

    @Override
    public void notifyObservers() {
        for (ChangePasswordObserver recoveryCodeObserver: observers) {
            recoveryCodeObserver.update(this);
        }
    }
}
