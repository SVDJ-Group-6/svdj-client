package model;

import observable.ThemeObservable;
import observer.ThemeObserver;

public class Theme implements ThemeObservable {
    private String primaryColor;
    private String secondaryColor;
    private String tertaireColor;
    private String ctaButtonColor;
    private String answerButtonColor;
    private String selectedButtonColor;
    private String previousButtonColor;

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getTertaireColor() {
        return tertaireColor;
    }

    public void setTertaireColor(String tertaireColor) {
        this.tertaireColor = tertaireColor;
    }

    public String getCtaButtonColor() {
        return ctaButtonColor;
    }

    public void setCtaButtonColor(String ctaButtonColor) {
        this.ctaButtonColor = ctaButtonColor;
    }

    public String getAnswerButtonColor() {
        return answerButtonColor;
    }

    public void setAnswerButtonColor(String answerButtonColor) {
        this.answerButtonColor = answerButtonColor;
    }

    public String getSelectedButtonColor() {
        return selectedButtonColor;
    }

    public void setSelectedButtonColor(String selectedButtonColor) {
        this.selectedButtonColor = selectedButtonColor;
    }

    public String getPreviousButtonColor() {
        return previousButtonColor;
    }

    public void setPreviousButtonColor(String previousButtonColor) {
        this.previousButtonColor = previousButtonColor;
    }

    @Override
    public void registerObserver(ThemeObserver themeObserver) {

    }

    @Override
    public void unregisterObserver(ThemeObserver themeObserver) {

    }

    @Override
    public void notifyObservers() {

    }
}
