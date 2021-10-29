package observable;

import observer.EmailObserver;

public interface EmailObservable {
    void registerObserver(EmailObserver emailObserver);
    void unregisterObserver(EmailObserver emailObserver);
    void notifyObservers();
}
