package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Question {
    private StringProperty question; // Вопрос
    private ArrayList<StringProperty> answerGood; // Правильные ответы
    private ArrayList<StringProperty> badAnswer; // Неправильные ответы


    public Question(String question) {
        this.question = new SimpleStringProperty(question);
        this.answerGood = new ArrayList<>();
        this.badAnswer = new ArrayList<>();
    }

    public int addTrue(String answer) {
        answerGood.add(new SimpleStringProperty(answer));
        return answerGood.size();
    }

    public int addFalse(String answer) {
        badAnswer.add(new SimpleStringProperty(answer));
        return badAnswer.size();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public ArrayList<StringProperty> getAnswerGood() {
        return answerGood;
    }

    public ArrayList<StringProperty> getBadAnswer() {
        return badAnswer;
    }


    public String getQuestion() {
        return question.get();
    }

    public StringProperty questionProperty() {
        return question;
    }
}