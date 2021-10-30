package model;

import observable.EditObservable;
import observer.EditObserver;

import java.util.ArrayList;

public class Edit implements EditObservable {

    private final ArrayList<EditObserver> observers = new ArrayList<>();
    private ArrayList<Node> nodes;

    private ArrayList<Integer> deletedQuestionIds = new ArrayList<>();
    private ArrayList<Integer> deletedAnswerIds = new ArrayList<>();
    private ArrayList<Integer> deletedAdviceIds = new ArrayList<>();

    public void resetDeletedState() {
        this.deletedQuestionIds = new ArrayList<>();
        this.deletedAnswerIds = new ArrayList<>();
        this.deletedAdviceIds = new ArrayList<>();
    }

    public void changeQuestion(Integer questionID, String newValue) {
        Question question = findQuestionWithID(questionID);
        if (question != null) {
            question.setValue(newValue);
            notifyObservers();
        }
    }

    public void changeAnswer(Integer questionID, Integer answerID, String newValue) {
        Answer answer = findAnswerWithQuestionIDAndAnswerID(questionID, answerID);
        if (answer != null) {
            answer.setValue(newValue);
            notifyObservers();
        }
    }

    public void changeAdvice(Integer adviceID, String newValue) {
        Advice advice = findAdviceWithAdviceID(adviceID);
        if (advice != null) {
            advice.setValue(newValue);
            notifyObservers();
        }
    }

    public void changeAdviceDescription(Integer adviceID, String newDescription) {
        Advice advice = findAdviceWithAdviceID(adviceID);
        if (advice != null) {
            advice.setDescription(newDescription);
            notifyObservers();
        }
    }

    public void changeAdviceMoreInfoURL(Integer adviceID, String newURL) {
        Advice advice = findAdviceWithAdviceID(adviceID);
        if (advice != null) {
            advice.setMoreInfoURL(newURL);
            notifyObservers();
        }
    }

    public void changeAdviceVideoURL(Integer adviceID, String newURL) {
        Advice advice = findAdviceWithAdviceID(adviceID);
        if (advice != null) {
            advice.setVideoURL(newURL);
            notifyObservers();
        }
    }

    public void changeAdviceOtherFundURL(Integer adviceID, String newURL) {
        Advice advice = findAdviceWithAdviceID(adviceID);
        if (advice != null) {
            advice.setOtherFundURL(newURL);
            notifyObservers();
        }
    }

    public void addQuestion() {
        final String DEFAULT_VALUE = "";
        Integer questionID = generateUniqueQuestionID();
        Question question = new Question(questionID, DEFAULT_VALUE);
        Node toInsertNode = new Node(question);

        // Insert new Question before Node with Advice
        int insertIndex = 0;
        for (Node node : this.nodes) {
            if (node.getQuestion() != null) {
                insertIndex++;
            } else {
                break;
            }
        }
        nodes.add(insertIndex, toInsertNode);
        notifyObservers();
    }

    public void addAnswer(Integer questionID) {
        Node node = findNodeWithQuestionID(questionID);
        if (node != null) {
            final String DEFAULT_VALUE = "";
            Integer answerID = generateUniqueAnswerID();
            Answer answer = new Answer(answerID, DEFAULT_VALUE, questionID);
            node.getAnswers().add(answer);
            notifyObservers();
        }
    }

    public void addAdvice() {
        Advice advice = new Advice(generateUniqueAdviceID());
        Node node = new Node(advice);
        nodes.add(node);
        notifyObservers();
    }

    public void removeQuestion(Integer questionID) {
        Node node = findNodeWithQuestionID(questionID);
        if (node != null) {

            deletedQuestionIds.add(questionID);
            for (Answer answer : node.getAnswers()) {
                deletedAnswerIds.add(answer.getId());
            }

            removeAllReferencesToQuestion(questionID);
            nodes.remove(node);
            notifyObservers();
        }
    }

    public void removeAnswer(Integer questionID, Integer answerID) {
        Node node = findNodeWithQuestionID(questionID);
        Answer answer = findAnswerWithQuestionIDAndAnswerID(questionID, answerID);

        if (node != null && answer != null) {
            deletedAnswerIds.add(answerID);
            node.getAnswers().remove(answer);
            notifyObservers();
        }
    }

    public void removeAdvice(Integer adviceID) {
        Node node = findNodeWithAdviceID(adviceID);
        if (node != null) {
            deletedAdviceIds.add(adviceID);
            removeAllReferencesToAdvice(adviceID);
            nodes.remove(node);
            notifyObservers();
        }
    }

    public void changeReference(Integer questionID, Integer answerID, Question question) {
        Answer answer = findAnswerWithQuestionIDAndAnswerID(questionID, answerID);
        if (answer != null) {
            answer.setAdviceId(null);
            answer.setNextQuestionId(question.getId());
            notifyObservers();
        }
    }

    public void changeReference(Integer questionID, Integer answerID, Advice advice) {
        Answer answer = findAnswerWithQuestionIDAndAnswerID(questionID, answerID);
        if (answer != null) {
            answer.setNextQuestionId(null);
            answer.setAdviceId(advice.getId());
            notifyObservers();
        }
    }

    private Node findNodeWithQuestionID(Integer questionID) {
        Question question;
        for (Node node : this.nodes) {
            question = node.getQuestion();
            if (question != null && question.getId() == questionID) {
                return node;
            }
        }
        return null;
    }

    private Node findNodeWithAdviceID(Integer adviceID) {
        Advice advice;
        for (Node node : this.nodes) {
            advice = node.getAdvice();
            if (advice != null && advice.getId() == adviceID) {
                return node;
            }
        }
        return null;
    }

    private Question findQuestionWithID(Integer questionID) {
        Node node = findNodeWithQuestionID(questionID);
        if (node != null) {
            return node.getQuestion();
        }
        return null;
    }

    private Answer findAnswerWithQuestionIDAndAnswerID(Integer questionID, Integer answerID) {
        Node node = findNodeWithQuestionID(questionID);
        if (node != null) {
            for (Answer answer : node.getAnswers()) {
                if (answer.getId() == answerID) {
                    return answer;
                }
            }
        }
        return null;
    }

    private Advice findAdviceWithAdviceID(Integer adviceID) {
        Node node = findNodeWithAdviceID(adviceID);
        if (node != null) {
            return node.getAdvice();
        }
        return null;
    }

    private Integer generateUniqueQuestionID() {
        int id = -1;
        Question question;
        for (Node node : this.nodes) {
            question = node.getQuestion();
            if (question != null && question.getId() > id) {
                id = question.getId();
            }
        }
        return id + 1;
    }

    private Integer generateUniqueAnswerID() {
        int id = -1;
        ArrayList<Answer> answers;
        for (Node node : this.nodes) {
            answers = node.getAnswers();
            if (answers != null) {
                for (Answer answer : answers) {
                    if (answer.getId() > id) {
                        id = answer.getId();
                    }
                }
            }
        }
        return id + 1;
    }

    private Integer generateUniqueAdviceID() {
        int id = -1;
        Advice advice;
        for (Node node : this.nodes) {
            advice = node.getAdvice();
            if (advice != null && advice.getId() > id) {
                id = advice.getId();
            }
        }
        return id + 1;
    }

    private void removeAllReferencesToQuestion(Integer questionID) {
        for (Node node : nodes) {
            if (node.getAnswers() != null) {
                ArrayList<Answer> answers = node.getAnswers();
                for (Answer answer : answers) {
                    Integer nextQuestionID = answer.getNextQuestionId();
                    if (nextQuestionID != null && nextQuestionID == questionID) {
                        answer.setNextQuestionId(null);
                    }
                }
            }
        }
    }

    private void removeAllReferencesToAdvice(Integer adviceID) {
        for (Node node : nodes) {
            if (node.getAnswers() != null) {
                ArrayList<Answer> answers = node.getAnswers();
                for (Answer answer : answers) {
                    Integer nextAdviceID = answer.getAdviceId();
                    if (nextAdviceID != null && nextAdviceID == adviceID) {
                        answer.setAdviceId(null);
                    }
                }
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
        notifyObservers();
    }

    public ArrayList<Integer> getDeletedQuestionIds() {
        return deletedQuestionIds;
    }

    public ArrayList<Integer> getDeletedAnswerIds() {
        return deletedAnswerIds;
    }

    public ArrayList<Integer> getDeletedAdviceIds() {
        return deletedAdviceIds;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        Question question;
        for (Node node : this.nodes) {
            question = node.getQuestion();
            if (question != null) {
                questions.add(question);
            }
        }
        return questions;
    }

    public ArrayList<Advice> getAllAdvices() {
        ArrayList<Advice> advices = new ArrayList<>();
        Advice advice;
        for (Node node : this.nodes) {
            advice = node.getAdvice();
            if (advice != null) {
                advices.add(advice);
            }
        }
        return advices;
    }

    public ArrayList<Answer> getAllAnswers() {
        ArrayList<Answer> answers = new ArrayList<>();
        ArrayList<Answer> tmpAnswers;
        for (Node node : this.nodes) {
            tmpAnswers = node.getAnswers();
            if (tmpAnswers != null) {
                answers.addAll(tmpAnswers);
            }
        }
        return answers;
    }

    @Override
    public void registerObserver(EditObserver editObserver) {
        observers.add(editObserver);
    }

    @Override
    public void unregisterObserver(EditObserver editObserver) {
        observers.remove(editObserver);
    }

    @Override
    public void notifyObservers() {
        for (EditObserver editObserver: observers) {
            editObserver.update(this);
        }
    }
}
