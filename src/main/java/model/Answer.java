package model;

public class Answer {

    private int id;
    private String value;
    private int originQuestionId;
    private Integer nextQuestionId;
    private Integer adviceId;

    public Answer(Integer nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

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

    public Integer getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(Integer nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }
}
