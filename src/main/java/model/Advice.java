package model;

import observable.AdviceObservable;
import observer.AdviceObserver;

import java.util.ArrayList;

public class Advice implements AdviceObservable {

    private final ArrayList<AdviceObserver> observers = new ArrayList<>();

    private int id;
    private String value;
    private String description;
    private String moreInfoUrl;
    private String videoUrl;
    private String otherFundUrl;

    public Advice() {}

    public Advice(int id, String value, String description, String moreInfoUrl, String videoUrl, String otherFundUrl) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.moreInfoUrl = moreInfoUrl;
        this.videoUrl = videoUrl;
        this.otherFundUrl = otherFundUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyObservers();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public String getMoreInfoUrl() {
        return moreInfoUrl;
    }

    public void setMoreInfoUrl(String moreInfoUrl) {
        this.moreInfoUrl = moreInfoUrl;
        notifyObservers();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        notifyObservers();
    }

    public String getOtherFundUrl() {
        return otherFundUrl;
    }

    public void setOtherFundUrl(String otherFundUrl) {
        this.otherFundUrl = otherFundUrl;
        notifyObservers();
    }

    public void replaceAdvice(Advice newAdvice) {
        this.id = newAdvice.getId();
        this.value = newAdvice.getValue();
        this.description = newAdvice.getDescription();
        this.moreInfoUrl = newAdvice.getMoreInfoUrl();
        this.videoUrl = newAdvice.getVideoUrl();
        this.otherFundUrl = newAdvice.getOtherFundUrl();
        notifyObservers();
    }

    @Override
    public void registerObserver(AdviceObserver adviceObserver) {
        observers.add(adviceObserver);
    }

    @Override
    public void unregisterObserver(AdviceObserver adviceObserver) {
        observers.remove(adviceObserver);
    }

    @Override
    public void notifyObservers() {
        for (AdviceObserver adviceObserver: observers) {
            adviceObserver.update(this);
        }
    }
}
