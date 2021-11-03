package model;

import observable.RecoveryCodeObservable;
import observer.RecoveryCodeObserver;

import java.util.ArrayList;

public class RecoveryCode implements RecoveryCodeObservable {
    private Boolean isMatching;
    private ArrayList<RecoveryCodeObserver> observers = new ArrayList<>();


    public RecoveryCode() {

    }

    public Boolean getMatching() {
        return isMatching;
    }

    public void setMatching(Boolean matching) {
        isMatching = matching;
        notifyObservers();
    }

    @Override
    public void registerObserver(RecoveryCodeObserver recoveryCodeObserver) {
        observers.add(recoveryCodeObserver);
    }

    @Override
    public void unregisterObserver(RecoveryCodeObserver recoveryCodeObserver) {
        observers.remove(recoveryCodeObserver);
    }

    @Override
    public void notifyObservers() {
        for (RecoveryCodeObserver recoveryCodeObserver: observers) {
            recoveryCodeObserver.update(this);
        }
    }
}
