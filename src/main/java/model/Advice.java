package model;

import com.google.gson.JsonObject;
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

    public Advice() {
    }

    public Advice(int id) {
        this.id = id;
    }

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

    public String getMoreInfoURL() {
        return moreInfoURL;
    }

    public void setMoreInfoURL(String moreInfoURL) {
        this.moreInfoURL = moreInfoURL;
        notifyObservers();
    }

    public String getVideoURL() {
        return videoURL;
    }

    public boolean hasVideoURL() {
        if (videoURL == null) {
            return false;
        }
        return true;
    }

    public boolean hasOtherFundURL() {
        if (otherFundURL == null) {
            return false;
        }
        return true;
    }

    public boolean hasHyperlinkURL() {
        if (moreInfoURL == null){
            return false;
        }
        return true;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        notifyObservers();
    }

    public String getOtherFundURL() {
        return otherFundURL;
    }

    public void setOtherFundURL(String otherFundURL) {
        this.otherFundURL = otherFundURL;
        notifyObservers();
    }

    public void replaceAdvice(Advice newAdvice) {
        this.id = newAdvice.getId();
        this.value = newAdvice.getValue();
        this.description = newAdvice.getDescription();
        this.moreInfoURL = newAdvice.getMoreInfoURL();
        this.videoURL = newAdvice.getVideoURL();
        this.otherFundURL = newAdvice.getOtherFundURL();
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

    public String toJsonString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("value", value);
        jsonObject.addProperty("description", description);
        jsonObject.addProperty("moreInfoURL", moreInfoURL);
        jsonObject.addProperty("videoURL", videoURL);
        jsonObject.addProperty("otherFundURL", otherFundURL);
        return jsonObject.toString();
    }
}
