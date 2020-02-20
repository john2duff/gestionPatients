package controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class GestionPatients extends Application {

    private Controller ctrl;

    @Override
    public void start(Stage primaryStage){
        ctrl = new Controller(primaryStage);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()  {
            @Override
            public void handle(WindowEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType buttonTypeOui = new ButtonType("Oui");
                ButtonType buttonTypeNon = new ButtonType("Non");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon, buttonTypeCancel);

                alert.setTitle("Enregistrement");
                alert.setHeaderText("Vous allez quitter l'application");
                alert.setContentText("Voulez-vous enregistrer le tournoi en cours ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOui){
                    ctrl.enregistreTournoi();
                }else if (result.get() == buttonTypeNon ) {
                    System.exit(0);
                }else if (result.get() == buttonTypeCancel){
                    t.consume();
                    primaryStage.show();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
