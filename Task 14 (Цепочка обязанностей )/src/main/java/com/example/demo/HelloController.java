package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.ActionChain;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Player player1 = new Player("Зубенко Мехаил Птрович", 0);
    public Label coinsCount;
    ActionChain action;
    public ImageView view;

    public void onPay(ActionEvent actionEvent) {
        player1.addNumber(1);
    }


    public void onStart(ActionEvent actionEvent) {
        view.setImage(new Image("nap.jpg"));
        if(!init()) return;//проверка ликвидности

        action = new ActionChain(player1);//запуск механизма розыгрыша

    }
    public boolean init(){
        if(!player1.pay(1)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Средств на счете недостаточно, еще монетку плисс!");
            alert.show();

            ActionChain action = null;
            view.setImage(new Image("coin.jpg"));
            return false;
        }

        else return true;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coinsCount.setText("Количество монет: " + player1.getNumber().toString());
        view.setImage(new Image("coin.jpg"));
        view.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            if (action == null) return; //если цепочка обработки отсутствует

            if (action.process()) init();//продолжить играть и проверить наличия монетки у игрока

            else action = null; //завершить игру, сделав обработку недоступной

        });
    }

    public void statusShow() {
        coinsCount.setText("Количество монет: " + player1.getNumber().toString());
    }
}
