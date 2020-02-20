package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;


public class MainToolbar extends ToolBar {

    private Controller ctrl;
    private Button backup;

    public MainToolbar(Controller ctrl) {

        this.ctrl = ctrl;

        backup = new Button("Backup", ctrl.chargeImageView("/img/up-arrow.png"));
        backup.setOnAction(e -> ctrl.backupDataBase());

        getItems().addAll(backup);

    }

}
