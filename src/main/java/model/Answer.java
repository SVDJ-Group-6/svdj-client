package model;

public class Answer {

    private int id;
    private String value;
    private int originQuestionId;
    private int nextQuestionId;
    private int adviceId;

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

    public int getOriginQuestionId() {
        return originQuestionId;
    }

    public void setOriginQuestionId(int originQuestionId) {
        this.originQuestionId = originQuestionId;
    }

    public int getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(int nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public int getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(int adviceId) {
        this.adviceId = adviceId;
    }
}
