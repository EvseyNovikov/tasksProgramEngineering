package model;

import java.util.ArrayList;

public abstract class Colleague {
    protected Mediator mediator;
    protected ArrayList<Question> receivedMessage;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
        this.receivedMessage = new ArrayList<>();
    }

    public abstract void notifyColleague(ArrayList<Question> message);

    public void receive(ArrayList<Question> message) {
        this.receivedMessage = message;
    }

    public ArrayList<Question> getReceivedMessage() {
        return receivedMessage;
    }
}