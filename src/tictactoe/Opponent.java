package tictactoe;

import javafx.event.ActionEvent;

public class Opponent {

    private ApplicationController mainController;


    public void computer(ActionEvent actionEvent) {
    }

    public void human(ActionEvent actionEvent) {
    }

    public void setMainController(ApplicationController mainController) {
        this.mainController = mainController;
        //mainController.registerHandler(GameWonEvent.class, event -> updateWins(event.getWhoWon()));
    }
}

