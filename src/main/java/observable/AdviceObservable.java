package observable;

import observer.AdviceObserver;

public interface AdviceObservable {

    void registerObserver(AdviceObserver adviceObserver);
    void unregisterObserver(AdviceObserver adviceObserver);
    void notifyObservers();
}
