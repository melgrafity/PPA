package tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tictactoe.event.GameWonEvent;
import tictactoe.event.RequestNewGameEvent;

/**
 * Created by pwilkin on 15-Nov-18.
 */
public class Stats {

    int krzyzykWins = 0;
    int kolkoWins = 0;

    protected ApplicationController mainController;

    public void setMainController(ApplicationController mainController) {
        this.mainController = mainController;
        mainController.registerHandler(GameWonEvent.class, event -> updateWins(event.getWhoWon()));
    }

    @FXML
    protected Label krzyzyk;

    @FXML
    protected Label kolko;

    public void newGame(ActionEvent actionEvent) {
        mainController.handleEvent(new RequestNewGameEvent());//kontrolerze, obsłuż wysokopoziomowe zdarzenie "nową grę"
    }

    private void updateWins(Player wins) {//zwiększ licznik wygranych i uaktualnij etykietki
        if (wins == Player.CROSS) {
            krzyzykWins++;
        } else {
            kolkoWins++;
        }
        updateLabels();
    }

    private void updateLabels() {
        krzyzyk.setText("Gracz X: " + krzyzykWins);
        kolko.setText("Gracz O: " + kolkoWins);
    }
}
