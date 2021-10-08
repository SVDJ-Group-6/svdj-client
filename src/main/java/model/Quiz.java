package model;

import observable.QuizObservable;
import observer.QuizObserver;

import java.util.ArrayList;

public class Quiz implements QuizObservable {

    private ArrayList<Question> questions;
    private ArrayList<ArrayList<Answer>> answers;
    private ArrayList<ArrayList<Stats>> stats;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<ArrayList<Answer>> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<ArrayList<Answer>> answers) {
        this.answers = answers;
    }

    public void addNewQuestionAndAnswers(Question question, ArrayList<Answer> answers){}
    public void deleteLastQuestionAndAnswers(){}

    @Override
    public void registerObserver(QuizObserver quizObserver) {

    }

    @Override
    public void unregisterObserver(QuizObserver quizObserver) {

    }

    @Override
    public void notifyObservers() {

    }
}
