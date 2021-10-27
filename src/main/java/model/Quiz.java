package model;

import observable.QuizObservable;
import observer.QuizObserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Quiz implements QuizObservable {

    public final String uuid;
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

    public Quiz() {
        uuid = UUID.randomUUID().toString();
    }

    public void addNewQuestionAndAnswers(Question question, ArrayList<Answer> answers){
        this.questions.add(question);
        this.answers.add(answers);
        notifyObservers();

    }

    public void deleteLastQuestionAndAnswers(){
        this.questions.remove(this.questions.size() - 1);
        this.answers.remove(this.answers.size() - 1);
        this.stats.remove(this.stats.size() - 1);
        notifyObservers();
    }

    public void addStats(String question, String answer, String advice) {
        int unixTime = (int) (new Date().getTime() / 1000L);
        int index = this.stats.size();
        this.stats.add(new Stats(uuid, index, question, answer, advice, unixTime));
    }

    public ArrayList<Stats> getStats() {
        return stats;
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
