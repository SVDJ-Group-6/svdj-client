package model;

import observable.EmailObservable;
import observer.EmailObserver;

import java.util.ArrayList;

public class Email implements EmailObservable {
    private Boolean isValidEmail;
    private ArrayList<EmailObserver> observers = new ArrayList<>();

    public Email(){};

    public boolean getValidEmail() {
        return isValidEmail;
    }

    public void setValidEmail(boolean validEmail) {
        isValidEmail = validEmail;
        notifyObservers();
    }

    @Override
    public void registerObserver(EmailObserver emailObserver) {
        observers.add(emailObserver);
    }

    @Override
    public void unregisterObserver(EmailObserver emailObserver) {
        observers.remove(emailObserver);
    }

    @Override
    public void notifyObservers() {
        for (EmailObserver emailObserver: observers) {
            emailObserver.update(this);
        }
    }

}
