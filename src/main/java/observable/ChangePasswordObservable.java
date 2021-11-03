package observable;

import observer.ChangePasswordObserver;

public interface ChangePasswordObservable {
    void registerObserver(ChangePasswordObserver changePasswordObserver);
    void unregisterObserver(ChangePasswordObserver changePasswordObserver);
    void notifyObservers();
}
