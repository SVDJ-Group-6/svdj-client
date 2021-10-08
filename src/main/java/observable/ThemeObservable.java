package observable;


import observer.ThemeObserver;

public interface ThemeObservable {

    void registerObserver(ThemeObserver themeObserver);
    void unregisterObserver(ThemeObserver themeObserver);
    void notifyObservers();
}
