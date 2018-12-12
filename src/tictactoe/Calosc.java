package tictactoe;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * Created by pwilkin on 15-Nov-18.
 */
public class Calosc {

    @FXML
    protected GridPane grid; //bo ma adnotację fxml, to od razu przypisuje wartość z pliku fxml

    protected ApplicationController controller;

    public ApplicationController getController() {
        return controller;
    }

    public void initialize() { //javafx po stworzeniu kontrolera wywołuje metodę initialize
        try {
            controller = new ApplicationController();//tworzymy nowy aplication contoller (ten globalny aplikacji)
            FXMLLoader ticTacToeLoader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));//nowy wizard ładujący z konkretnego pliku fxml komponenty graficzne
            Node plansza = ticTacToeLoader.load();//ładujemy planszę z tego pliku. Jej kontroler dostaniemy z tego wizarda linijkę wyżej
            Controller boardController = ticTacToeLoader.getController();//
            boardController.setMainController(controller);//głównym kontrolerem aplikacji będzie ten kontroler
            FXMLLoader opponentsLoader = new FXMLLoader(getClass().getResource("opponents.fxml"));//analogiocznie robimy z drugim okienkiem ze statystykami
            Node opponents = opponentsLoader.load();
            Opponent opponentController = opponentsLoader.getController();
            opponentController.setMainController(controller);//temu kontrolerowi planszy podpinamy główny kontroler aplikacyjny
            grid.add(plansza, 0, 0);//no to do lewej komóeki tego grida, co go stworzyliśmy włóż planszę
            grid.add(opponents, 1, 0);//a do prawej statystyki
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void initialize() { //javafx po stworzeniu kontrolera wywołuje metodę initialize
//        try {
//            controller = new ApplicationController();//tworzymy nowy aplication contoller (ten globalny aplikacji)
//            FXMLLoader ticTacToeLoader = new FXMLLoader(getClass().getResource("tictactoe.fxml"));//nowy wizard ładujący z konkretnego pliku fxml komponenty graficzne
//            Node plansza = ticTacToeLoader.load();//ładujemy planszę z tego pliku. Jej kontroler dostaniemy z tego wizarda linijkę wyżej
//            Controller boardController = ticTacToeLoader.getController();//
//            boardController.setMainController(controller);//głównym kontrolerem aplikacji będzie ten kontroler
//            FXMLLoader statsLoader = new FXMLLoader(getClass().getResource("stats.fxml"));//analogiocznie robimy z drugim okienkiem ze statystykami
//            Node statystyki = statsLoader.load();
//            Stats statsController = statsLoader.getController();
//            statsController.setMainController(controller);//temu kontrolerowi planszy podpinamy główny kontroler aplikacyjny
//            grid.add(plansza, 0, 0);//no to do lewej komóeki tego grida, co go stworzyliśmy włóż planszę
//            grid.add(statystyki, 1, 0);//a do prawej statystyki
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
