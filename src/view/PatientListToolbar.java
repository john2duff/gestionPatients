package view;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class PatientListToolbar extends ToolBar {

    private Button nouveauPatient;

    public PatientListToolbar(Controller ctrl) {
        nouveauPatient = new Button("Backup", ctrl.chargeImageView("/img/up-arrow.png"));
        nouveauPatient.setOnAction(e -> ctrl.ajouteNouveauPatient());

        getItems().addAll(nouveauPatient);
    }

    public void refresh(){

    }
}
