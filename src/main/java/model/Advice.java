package model;

import observable.AdviceObservable;
import observer.AdviceObserver;

public class Advice implements AdviceObservable {

    private int id;
    private String value;
    private String description;
    private String moreInfoUrl;
    private String videoUrl;
    private String otherFundUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoreInfoUrl() {
        return moreInfoUrl;
    }

    public void setMoreInfoUrl(String moreInfoUrl) {
        this.moreInfoUrl = moreInfoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOtherFundUrl() {
        return otherFundUrl;
    }

    public void setOtherFundUrl(String otherFundUrl) {
        this.otherFundUrl = otherFundUrl;
    }

    @Override
    public void registerObserver(AdviceObserver adviceObserver) {

    }

    @Override
    public void unregisterObserver(AdviceObserver adviceObserver) {

    }

    @Override
    public void notifyObservers() {

    }
}
