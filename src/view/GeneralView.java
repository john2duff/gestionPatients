package view;

import controller.Controller;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Patient;
import model.DataBasePatient;

import java.util.ArrayList;

public class GeneralView extends BorderPane {

    private Controller ctrl;
    private MainToolbar toolBarPrincipal;

    private PatientListView vueListPatient;
    private PatientView vuePatient;

    private TabPane tab;
    private Tab tabListPatient;
    private Tab tabPatient;
    public StatusBar statusBar;

    public GeneralView(Controller ctrl) {

        this.ctrl = ctrl;

        vueListPatient = new PatientListView(ctrl);
       // vuePatient = new PatientView(ctrl);

        //center
        tab = new TabPane();
        tab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabListPatient = new Tab();
        tabListPatient.setText("Patients");
        tabListPatient.setContent(vueListPatient);

        tab.getTabs().addAll(tabListPatient);
        setCenter(tab);

        //top
        toolBarPrincipal = new MainToolbar(ctrl);
        setTop(toolBarPrincipal);

        //bottom
        statusBar = new StatusBar();
        setBottom(statusBar);
    }

}
