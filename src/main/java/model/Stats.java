package model;

public class Stats {
    private String uuid;
    private int index;
    private String question;
    private String answer;
    private String advice;
    private int timestampUNIX;

    public Stats(String uuid, int index, String question, String answer, String advice, int timestampUNIX) {
        this.uuid = uuid;
        this.index = index;
        this.question = question;
        this.answer = answer;
        this.advice = advice;
        this.timestampUNIX = timestampUNIX;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getTimestampUNIX() {
        return timestampUNIX;
    }

    public void setTimestampUNIX(int timestampUNIX) {
        this.timestampUNIX = timestampUNIX;
    }
}
