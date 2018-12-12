package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application { //bo korzysta z frameworka javafx

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calosc.fxml")); //ładuje startowy ekran z pliku calosc.fxml
        primaryStage.setTitle("Tic-Tac-Toe"); //tytuł głównego okienak
        primaryStage.setScene(new Scene(root, 188, 94)); //ustawiamy
        primaryStage.show();//pokazujemy
    }
//ponieważ kod działa przez refleksję, musimy zajrzeć do pliku fxml
    public static void main(String[] args) {
        launch(args);
    }
}


/*co by trzeba byďż˝o zmieniďż˝, ďż˝eby nasz program obsďż˝uďż˝yďż˝ grďż˝?
- zmieniďż˝ wielkoďż˝ďż˝ planszy: czyli co?
co to jest u nas plansza? 1)obiekt klasy board?
2)dla uďż˝ytkownika koďż˝cowego: siatka, to, co mu siďż˝ wyďż˝wietla
czyli trzeba zarďż˝wno zmieniďż˝ planszďż˝ z 3x3 na 19x19 ORAZ zmieniďż˝ to: czyli tictactoe.fxml lub w kodzie (**)

No i warunki zwyciďż˝stwa:
Najpowaďż˝niejsza zmiana: chechVictory()
Mamy problem ogďż˝lniejszy Kďż˝koIKrzyďż˝ykX.Y X-liczba znaczkďż˝ so zwyciďż˝stwa, Y - rozmiar planszy, np. gomoku: KK5,19
Jak sprawdzamy wiersze? Dla kaďż˝dego wiersza bierzemy wszystkie moďż˝liwe punkty poczďż˝tkowe: jest ich Y-X+1
analogicznie dla kolumn
i analogicznie dla przekďż˝tnych, bo punktďż˝w startowych dla przekďż˝tnych bďż˝dzie mniej, bo nie bďż˝dzie ich tyle ile wierszy, tylko Y-X we sensie: musi byďż˝ potencjalnym poczďż˝tkiem kolumny i potencjalnym poczďż˝tkiem wiersza jednoczeďż˝nie
i dla odwrotnych przekďż˝tnych tak samo
w KK33 nam siďż˝ zdegenerowaďż˝o do 3Kol 3Wier i 2 przekďż˝tnych
19*15 kolumienek
19*15 wierszykďż˝w
15*15 przekďż˝tnych jednych
15*15 przekďż˝tnych drugich
na tyle maďż˝o, ďż˝e moďż˝emy jeszcze to przelecieďż˝

no i ruch komputera. To juďż˝ trudniejsze, bo nawet minimaxy nie dziaďż˝ajďż˝ juďż˝ w gomoku
Jakďż˝ďż˝ prawdziwďż˝ sztuczna Inteligencjďż˝ by wypadaďż˝o


Jeďż˝li chodzi o programy komputerowe, to sďż˝ rďż˝ne stopnie komplikacji
1 - z architektury
2 - z algorytmiki
Warto myďż˝leďż˝ o problemie jako o przypadku jakiegoďż˝ ogďż˝lniejszego problemu i stwierdziďż˝ gdzie poszliďż˝my na skrďż˝ty (np. wpisanďż˝ na staďż˝e liczbďż˝ pďż˝l = 3 i w kwestii przekďż˝tnych (moďż˝e byďż˝ ich wiďż˝cej niďż˝ 1 i 1))

PD: zrobiďż˝ tďż˝ zmodyfikowanďż˝ wersjďż˝ do struktury zdarzeniowekj, ďż˝eby powiedzieďż˝ czy chcemy graďż˝ z komputerem czy nie
gomoku


*/