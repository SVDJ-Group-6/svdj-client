package model;

import observable.EditObservable;
import observer.EditObserver;

import java.util.ArrayList;

public class Edit implements EditObservable {

    private ArrayList<EditObserver> observers = new ArrayList<>();
    private ArrayList<Node> nodes;

    private ArrayList<Integer> deletedQuestionIds = new ArrayList<>();
    private ArrayList<Integer> deletedAnswerIds = new ArrayList<>();
    private ArrayList<Integer> deletedAdviceIds = new ArrayList<>();

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
        notifyObservers();
    }

    public void setDeletedQuestionIds(ArrayList<Integer> deletedQuestionIds) {
        this.deletedQuestionIds = deletedQuestionIds;
    }

    public void setDeletedAnswerIds(ArrayList<Integer> deletedAnswerIds) {
        this.deletedAnswerIds = deletedAnswerIds;
    }

    public void setDeletedAdviceIds(ArrayList<Integer> deletedAdviceIds) {
        this.deletedAdviceIds = deletedAdviceIds;
    }

    public void addDeletedQuestionID(int questionId) {
        deletedQuestionIds.add(questionId);
    }

    public void addDeletedAnswerID(int answerId) {
        deletedAnswerIds.add(answerId);
    }

    public void addDeletedAdviceID(int adviceId) {
        deletedAdviceIds.add(adviceId);
    }

    public ArrayList<Integer> getDeletedQuestionIds() {
        return deletedQuestionIds;
    }

    public ArrayList<Integer> getDeletedAnswerIds() {
        return deletedAnswerIds;
    }

    public ArrayList<Integer> getDeletedAdviceIds() {
        return deletedAdviceIds;
    }

    @Override
    public void registerObserver(EditObserver editObserver) {
        observers.add(editObserver);
    }

    @Override
    public void unregisterObserver(EditObserver editObserver) {
        observers.remove(editObserver);
    }

    @Override
    public void notifyObservers() {
        for (EditObserver editObserver: observers) {
            editObserver.update(this);
        }
    }
}
