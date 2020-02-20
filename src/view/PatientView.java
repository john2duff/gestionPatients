package view;

import controller.Controller;
import javafx.scene.layout.BorderPane;

public class PatientView extends BorderPane {
    private Controller ctrl;

    public PatientView(Controller ctrl) {
        this.ctrl = ctrl;

    }
}
