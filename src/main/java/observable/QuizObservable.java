package observable;


import observer.QuizObserver;

public interface QuizObservable {

    void registerObserver(QuizObserver quizObserver);
    void unregisterObserver(QuizObserver quizObserver);
    void notifyObservers();
}
