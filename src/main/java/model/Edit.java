package model;

import observable.EditObservable;
import observer.EditObserver;

import java.util.ArrayList;

public class Edit implements EditObservable {

    private ArrayList<Node> nodes;

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void registerObserver(EditObserver editObserver) {

    }

    @Override
    public void unregisterObserver(EditObserver editObserver) {

    }

    @Override
    public void notifyObservers() {

    }
}
