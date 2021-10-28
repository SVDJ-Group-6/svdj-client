package observable;

import observer.RecoveryCodeObserver;

public interface RecoveryCodeObservable {
    void registerObserver(RecoveryCodeObserver recoveryCodeObserver);
    void unregisterObserver(RecoveryCodeObserver recoveryCodeObserver);
    void notifyObservers();
}
