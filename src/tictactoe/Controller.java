package tictactoe;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tictactoe.event.GameWonEvent;
import tictactoe.event.RequestNewGameEvent;

public class Controller {

    protected ApplicationController mainController;

    public void setMainController(ApplicationController mainController) {
        this.mainController = mainController;
        mainController.registerHandler(RequestNewGameEvent.class, event -> startNewGame());//jak dostaniesz to zdarzenie, które jest tej klasy, to rozpocznij nową gr. Tu rejestrujemy obsługę zdarzenia.
    }

    @FXML
    protected GridPane grid;

    protected boolean gameEnded;
    protected Board board;

    public void initialize() {//tutaj mieliśmy planszę i w niej jest grid 3x3, ale barzdo niewygodnie się
        // dodaje hurtowo event-listaenery w fxml-u/scene builderze, więc dodajmy je ręcznie.
        // w momencie gdy stworzyliśmy tę planszę 3x3 tam się nic nie dzieje. WIęc teraz mówimy, że jak już
        // stworzysz planszę dla każdej komórki zrób tak:
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            child.setOnMouseClicked(event -> handleMove(row, column));//dodaj mu handlee, że jak na mnie klikniesz,
            // to obsłóż ruch w tym konkretnym wierszu i kolumnie
        }
        startNewGame();//i mówimy rozpocznij nową grę.
    }

    private void handleMove(Integer row, Integer column) {
        if (!gameEnded) {
            if (board.canYouMakeAMove(row, column)) {
                board.makeMove(row, column);
                if (!checkVictoryShowAndRegister()) {
                    board.makeComputerMove();
                }
            }
            drawBoard();
            if (!gameEnded) {
                checkVictoryShowAndRegister();
            }
        }//skończyliśmy wykonywać kod. Doopiero jak wykonamy 3. ruch: pokaże się okienko. Skąd? (*)
        //póżniej. Nic się nie zadzieje dopóki nie naciśniemy nowa gra.
    }

    private boolean checkVictoryShowAndRegister() { //(*) ta metoda nam skończy grę
        Player wins = board.checkVictory();
        if (wins != null) {
            gameEnded = true;
            showVictoryMessage(wins);
            mainController.handleEvent(new GameWonEvent(wins));//główny kontroler dostał info: obsłuż zdarzenie: "ktoś wygrał"
            return true;
        }
        return false;
    }

    private void drawBoard() {//to kontroler odpowiada za odzwierciedlenie modelu na widok
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            Pane pane = (Pane) child;
            pane.getChildren().clear();
            Player inCell = board.getPlayerForField(row, column);
            if (inCell != null) {
                Label label = new Label(inCell.getSign());
                label.setPrefWidth(30.0);
                label.setPrefHeight(30.0);
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
            }
        }
    }

    private void showVictoryMessage(Player wins) {//pokażemy tzw. okno modalne. Ciekawa rzecz: ani się nie pokazał 3. krzyżyk, ani się nie wykonały statystyki
        //Wyświetlenie komunikatów zatrzymuje flow. Okno modalne to taki stary dobry komunikat. Okno modalne wymusza akcję użytnownika. Nic więcej się nie będzie dziać, dopóki użytkownik go nie obsłuży.
        Alert alert = new Alert(AlertType.WARNING, "Player " + wins + " wins!", ButtonType.OK);
        alert.showAndWait();
    }

    private void startNewGame() {
        gameEnded = false;
        board = new Board();
        drawBoard();

        //(**) tu dorabiamy nowy gridpane
        //potem nowe panele
        //pane.setOnMouseClicked(click -> handleMove(i,j)
        //i zamiast wczytywać z fxmla planszę 3x3 programistycznie stworzymy planszę 19x19

        //VARIABLE USED IN LAMBDA EXPRESSION SHOULD BE FINAL OR EFFECTIVELY FINAL: o co chodzi?
        //trzeba zrobić "zamrożoną kopię" zmiennej, która nam nie wchodzi. Takie "int row = i". I podać row zamiast i.
        //bo row jest wewnętrzne i zawsze jest równe i. A i się zmienia. Raz jest 5, w kolejnej iteracji jest 6.

    }
}









/*

package tictactoe;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tictactoe.event.GameWonEvent;
import tictactoe.event.RequestNewGameEvent;

public class Controller {

    protected ApplicationController mainController;

    public void setMainController(ApplicationController mainController) {
        this.mainController = mainController;
        mainController.registerHandler(RequestNewGameEvent.class, event -> startNewGame());//jak dostaniesz to zdarzenie, które jest tej klasy, to rozpocznij nową gr. Tu rejestrujemy obsługę zdarzenia.
    }

    @FXML
    protected GridPane grid;

    protected boolean gameEnded;
    protected Board board;

    public void initialize() {//tutaj mieliśmy planszę i w niej jest grid 3x3, ale barzdo niewygodnie się dodaje hurtowo event-listaenery w fxml-u/scene builderze, więc dodajmy je ręcznie. w momencie gdy stworzyliśmy tę planszę 3x3 tam się nic nie dzieje. WIęc teraz mówimy, że jak już stworzysz planszę dla każdej komórki zrób tak:
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            child.setOnMouseClicked(event -> handleMove(row, column));//dodaj mu handlee, że jak na mnie klikniesz, to obsłóż ruch w tym konkretnym wierszu i kolumnie
        }
        startNewGame();//i mówimy rozpocznij nową grę.
    }

    private void handleMove(Integer row, Integer column) {
        if (!gameEnded) {
            if (board.canYouMakeAMove(row, column)) {
                board.makeMove(row, column);
                if (!checkVictoryShowAndRegister()) {
                    board.makeComputerMove();
                }
            }
            drawBoard();
            if (!gameEnded) {
                checkVictoryShowAndRegister();
            }
        }//skończyliśmy wykonywać kod. Doopiero jak wykonamy 3. ruch: pokaże się okienko. Skąd? (*)
        //póżniej. Nic się nie zadzieje dopóki nie naciśniemy nowa gra.
    }

    private boolean checkVictoryShowAndRegister() { //(*) ta metoda nam skończy grę
        Player wins = board.checkVictory();
        if (wins != null) {
            gameEnded = true;
            showVictoryMessage(wins);
            mainController.handleEvent(new GameWonEvent(wins));//główny kontroler dostał info: obsłuż zdarzenie: "ktoś wygrał"
            return true;
        }
        return false;
    }

    private void drawBoard() {//to kontroler odpowiada za odzwierciedlenie modelu na widok
        for (Node child : grid.getChildren()) {
            Integer row = Optional.ofNullable(GridPane.getRowIndex(child)).orElse(0);
            Integer column = Optional.ofNullable(GridPane.getColumnIndex(child)).orElse(0);
            Pane pane = (Pane) child;
            pane.getChildren().clear();
            Player inCell = board.getPlayerForField(row, column);
            if (inCell != null) {
                Label label = new Label(inCell.getSign());
                label.setPrefWidth(30.0);
                label.setPrefHeight(30.0);
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
            }
        }
    }

    private void showVictoryMessage(Player wins) {//pokażemy tzw. okno modalne. Ciekawa rzecz: ani się nie pokazał 3. krzyżyk, ani się nie wykonały statystyki
    	//Wyświetlenie komunikatów zatrzymuje flow. Okno modalne to taki stary dobry komunikat. Okno modalne wymusza akcję użytnownika. Nic więcej się nie będzie dziać, dopóki użytkownik go nie obsłuży.
        Alert alert = new Alert(AlertType.WARNING, "Player " + wins + " wins!", ButtonType.OK);
        alert.showAndWait(); 
    }

    private void startNewGame() {
        gameEnded = false;
        board = new Board();
        drawBoard();
        
        //(**) tu dorabiamy nowy gridpane
        
        
    }
}


*/