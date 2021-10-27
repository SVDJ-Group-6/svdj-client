package model;

import java.util.ArrayList;

public class Node {

    private Question question;
    private ArrayList<Answer> answers;
    private Advice advice;

    public Node(Question question, ArrayList<Answer> answers){
        this.question = question;
        this.answers = answers;
    }
    public Node(Advice advice){
        this.advice = advice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
