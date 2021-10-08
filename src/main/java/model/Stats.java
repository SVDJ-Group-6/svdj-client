package model;

public class Stats {
    private String uuid;
    private int order;
    private String question;
    private String answer;
    private String advice;
    private int timestampUNIX;

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
