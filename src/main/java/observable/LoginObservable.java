package observable;


import observer.LoginObserver;

public interface LoginObservable {

    void registerObserver(LoginObserver loginObserver);
    void unregisterObserver(LoginObserver loginObserver);
    void notifyObservers();
}
