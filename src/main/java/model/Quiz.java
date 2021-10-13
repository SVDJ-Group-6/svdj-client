package model;

import observable.QuizObservable;
import observer.QuizObserver;

import java.util.ArrayList;

public class Quiz implements QuizObservable {

    private ArrayList<QuizObserver> observers = new ArrayList<>();

    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<ArrayList<Answer>> answers = new ArrayList<>();
    private ArrayList<Stats> stats = new ArrayList<Stats>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<ArrayList<Answer>> getAnswers() {
        return answers;
    }

    public void addNewQuestionAndAnswers(Question question, ArrayList<Answer> answers){
        this.questions.add(question);
        this.answers.add(answers);
        notifyObservers();

    }
    public void deleteLastQuestionAndAnswers(){
        this.questions.remove(this.questions.size() - 1);
        this.answers.remove(this.answers.size() - 1);
        notifyObservers();
    }

    @Override
    public void registerObserver(QuizObserver quizObserver) {
        observers.add(quizObserver);
    }

    @Override
    public void unregisterObserver(QuizObserver quizObserver) {
        observers.remove(quizObserver);
    }

    @Override
    public void notifyObservers() {
        for (QuizObserver quizObserver: observers) {
            quizObserver.update(this);
        }
    }
}
