package model;

import observable.AdviceObservable;
import observer.AdviceObserver;

import java.util.ArrayList;

public class Advice implements AdviceObservable {

    private final ArrayList<AdviceObserver> observers = new ArrayList<>();

    private int id;
    private String value;
    private String description;
    private String moreInfoURL;
    private String videoURL;
    private String otherFundURL;

    public Advice() {}

    public Advice(int id, String value, String description, String moreInfoURL, String videoURL, String otherFundURL) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.moreInfoURL = moreInfoURL;
        this.videoURL = videoURL;
        this.otherFundURL = otherFundURL; 
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
        return moreInfoURL;
    }

    public void setMoreInfoUrl(String moreInfoUrl) {
        this.moreInfoURL = moreInfoUrl;
        notifyObservers();
    }

    public String getVideoUrl() {
        return videoURL;
    }

    public boolean hasVideoUrl() {
        if (videoURL == null){
            return false;
        } else {
            return true;
        }
    }

    public void setVideoUrl(String videoUrl) {
        this.videoURL = videoUrl;
        notifyObservers();
    }

    public String getOtherFundUrl() {
        return otherFundURL;
    }

    public void setOtherFundUrl(String otherFundUrl) {
        this.otherFundURL = otherFundUrl;
        notifyObservers();
    }

    public void replaceAdvice(Advice newAdvice) {
        this.id = newAdvice.getId();
        this.value = newAdvice.getValue();
        this.description = newAdvice.getDescription();
        this.moreInfoURL = newAdvice.getMoreInfoUrl();
        this.videoURL = newAdvice.getVideoUrl();
        this.otherFundURL = newAdvice.getOtherFundUrl();
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
