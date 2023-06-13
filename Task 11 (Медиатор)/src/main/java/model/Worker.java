package model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Worker extends Colleague {
    private int NUMBER = 5;//константа NUMBER изменена на private для указания того что это поле экземпляра

    public Worker(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void notifyColleague(ArrayList<Question> message) {
        VBox questionPane = new VBox();
        Random random = new Random();

        for (int iqw = 0; iqw < NUMBER && iqw < message.size(); iqw++) {
            Label questionField = new Label();
            questionField.textProperty().bind(message.get(iqw).questionProperty());
            questionPane.getChildren().add(questionField);

            Separator separator = new Separator();
            separator.setMaxWidth(20);
            questionPane.getChildren().add(separator);

            CheckBox answerGoodField = new CheckBox();
            CheckBox[] answerBadField = new CheckBox[3];

            int k = random.nextInt(message.get(iqw).getAnswerGood().size());

            answerGoodField.textProperty().bind(message.get(iqw).getAnswerGood().get(k));
            answerGoodField.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {BackgroundFill background_fill = new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY);

                if (new_val) {
                    answerGoodField.setBackground(new Background(background_fill));

                    for (int i = 0; i < 3; i++) {
                        answerBadField[i].setSelected(false);
                    }
                }
            });

            int[] count = new int[message.get(iqw).getBadAnswer().size()];

            for (int i = 0; i < message.get(iqw).getBadAnswer().size(); i++) {
                count[i] = 1;
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(message.get(iqw).getBadAnswer().size());
                k = random.nextInt(message.get(iqw).getBadAnswer().size());

                if (count[k] == 1) {
                    CheckBox cb = answerBadField[i] = new CheckBox();

                    answerBadField[i].textProperty().bind(message.get(iqw).getBadAnswer().get(k));
                    answerBadField[i].selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {BackgroundFill background_fill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);
                        if (new_val) {
                            answerGoodField.setSelected(false);

                            for (int j = 0; j < 3; j++) {
                                if (answerBadField[j] != cb) {
                                    answerBadField[j].setSelected(false);
                                }
                            }
                            cb.setBackground(new Background(background_fill));
                        }
                    });
                    count[k] = 0;
                } else {
                    i--;
                }
            }
            k = random.nextInt(4);
            for (int i = 0; i < 3; i++) {
                if (i != k) {
                    questionPane.getChildren().add(answerBadField[i]);
                } else {
                    questionPane.getChildren().add(answerGoodField);
                }
            }
            if (k == 3) {
                questionPane.getChildren().add(answerGoodField);
            } else {
                questionPane.getChildren().add(answerBadField[k]);
            }
        }
        mediator.setView(questionPane);
    }

    public void receive(ArrayList<Question> message) {
        ArrayList<Question> currenttest = new ArrayList<>();
        ArrayList<Question> test = new ArrayList<>(message);

        for (int i = 0; i < NUMBER && i < test.size(); i++) {int index = (int) (Math.random() * test.size());
            if (index == test.size()) {
                index--;
            }
            currenttest.add(test.get(index));
            test.remove(index);
        }
        super.receive(currenttest);
    }
}