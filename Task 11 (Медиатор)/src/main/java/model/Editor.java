package model;

import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Editor extends Colleague {
    public Editor(Mediator mediator) {
        super(mediator);
    }
    @Override
    public void notifyColleague(ArrayList<Question> message) {
        VBox questionPane = new VBox();

        for (int iqw = 0; iqw < message.size(); iqw++) {
            TextField questionField = new TextField();
            questionField.textProperty().bindBidirectional(message.get(iqw).questionProperty());
            questionPane.getChildren().add(questionField);

            Separator separator = new Separator();
            separator.setMaxWidth(20);
            questionPane.getChildren().add(separator);

            for (int i = 0; i < message.get(iqw).getAnswerGood().size(); i++) {
                TextField goodAnswerField = new TextField();
                goodAnswerField.textProperty().bindBidirectional(message.get(iqw).getAnswerGood().get(i));
                questionPane.getChildren().add(goodAnswerField);
            }

            Separator separator2 = new Separator();
            separator2.setMaxWidth(40);
            questionPane.getChildren().add(separator2);

            for (int i = 0; i < message.get(iqw).getBadAnswer().size(); i++) {
                TextField badAnswerField = new TextField();
                badAnswerField.textProperty().bindBidirectional(message.get(iqw).getBadAnswer().get(i));
                questionPane.getChildren().add(badAnswerField);
            }
        }
        mediator.setView(questionPane);
    }
}
