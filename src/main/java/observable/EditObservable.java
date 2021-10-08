package observable;


import observer.EditObserver;

public interface EditObservable {

    void registerObserver(EditObserver editObserver);
    void unregisterObserver(EditObserver editObserver);
    void notifyObservers();
}
