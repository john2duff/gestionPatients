package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Patient;

import java.util.ArrayList;

public class PatientListView extends BorderPane {

    private final ScrollPane container;
    private Controller ctrl;
    private PatientListToolbar patientListToolbar;
    private FlowPane vboxPatientList = new FlowPane();

    public PatientListView(Controller ctrl) {

        getStylesheets().add(getClass().getResource("Style/Style.css").toExternalForm());
        this.ctrl = ctrl;

        //top
        patientListToolbar = new PatientListToolbar(ctrl);
        setTop(patientListToolbar);

        //center
        container = new ScrollPane();
        container.setPadding(new Insets(5,5,5,5));
        container.setContent(vboxPatientList);
        vboxPatientList.setHgap(5);
        vboxPatientList.setVgap(5);
        vboxPatientList.setAlignment(Pos.TOP_LEFT);
        container.setFitToWidth(true);

        setCenter(container);
    }

    public void refreshPatientListView(ArrayList<Patient> listPatient) {
        vboxPatientList.getChildren().clear();
        for (int i = 0; i < listPatient.size(); i++){
            PatientCell patientCell = new PatientCell(listPatient.get(i));
            vboxPatientList.getChildren().add(patientCell);
        }
        patientListToolbar.refresh();
    }

}
