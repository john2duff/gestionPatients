package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import view.GeneralView;

import java.io.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class Controller {

    private final String dataBasePatientName = "dataBasePatient.xml";
    private GeneralView vueGeneral;
    private Stage primaryStage;
    public DataBasePatient currentDataBase = null;
    private final String dossierRacine = "/DataBase/";

    final Image APPLICATION_ICON = chargeImage("/img/main.png");

    public Controller(Stage primaryStage) {

        this.primaryStage = primaryStage;

        vueGeneral = new GeneralView(this);
        Parent root = vueGeneral;
        Scene scenePrincipal = new Scene(root, 800, 700);
        scenePrincipal.getStylesheets().add("view/Style/Style.css");
        primaryStage.setScene(scenePrincipal);

        primaryStage.getIcons().add(chargeImage("/img/main.png"));

        newDataBasePatient();
        primaryStage.setTitle(currentDataBase.nomDataBase);

        primaryStage.show();
    }

    public ImageView chargeImageView(String chemin){
        InputStream input = getClass().getResourceAsStream(chemin);
        Image image;
        ImageView imageView;
        try{
            image = new Image(input);
            imageView = new ImageView(image);
            input.close();
            return imageView;
        }catch (Exception e){
            System.out.println("Image introuvable");
            return null;
        }
    }

    public Image chargeImage(String chemin){
        InputStream input = getClass().getResourceAsStream(chemin);
        Image image;
        try{
            image = new Image(input);
            return image;
        }catch (Exception e){
            System.out.println("Image introuvable");
            return null;
        }
    }

    public void backupDataBase() {

    }

    public String getCheminDataBase(){

        File dossier = new File(getRepertoireCourant() + dossierRacine);
        if (!dossier.exists()){
            dossier.mkdirs();
        }
        return dossier.getAbsolutePath();
    }

    public String getRepertoireCourant(){
        return new java.io.File("").getAbsolutePath();
    }


    public boolean databaseExist(String file){
        File dataBase = new File(file);
        return dataBase.exists();
    }

    //si nouveau existe alors on l'ouvre sinon on le créé
    public void newDataBasePatient() {
        if (databaseExist(getCheminDataBase() + "/" + dataBasePatientName)){
            ouvreDataBase(getCheminDataBase() + "/" + dataBasePatientName);
            updateInfo("Ouverture de la base de données existante.");
        }else{
            currentTournoi = new Tournoi("Nouveau tournoi", Tournoi.TypeTournoi.SIMPLE, 5, 5);
            try{
                enregistreTournoi();
                primaryStage.setTitle(currentTournoi.nomTournoi);
                vueGeneral.ouvreTournoi();
            }catch( Exception e){
                updateInfo("Problème pour enregistrer Nouveau tournoi.trn");
                currentTournoi = null;
            }
        }
    }

    public void updateInfo(String txt) {
        vueGeneral.statusBar.updateInfo(txt);
    }

    public void ajouteNouveauPatient() {

        Dialog dialog = new Dialog();
        dialog.setTitle("Nom du patient");
        dialog.setHeaderText("Vous allez renommer le tournoi");
        dialog.setContentText("Entrez le nom ici :");

        Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if (patientExist(getCheminDataBase() + "/" + result.get() + ".trn")){
                updateInfo("Patient déjà existant");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Patient existant");
                alert.setHeaderText("Le fichier " + result.get() + " existe déjà.");
                alert.setContentText("Voulez-vous l'écraser ?");

                Stage dialogStage2 = (Stage) alert.getDialogPane().getScene().getWindow();
                dialogStage2.getIcons().add(APPLICATION_ICON);

                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.get() == ButtonType.OK){
                    currentTournoi.setNomTournoi(result.get());
                    enregistreTournoi();
                } else {
                    updateInfo("Renommage annulé");
                }
            }else{
                renommerTournoi2(getCheminTournois()+ "/" + currentTournoi.nomTournoi + ".trn", getCheminTournois()+ "/" + result.get() + ".trn", result.get());
                String save = currentTournoi.nomTournoi;
                currentTournoi.setNomTournoi(result.get());
                if (enregistreTournoi()){
                    primaryStage.setTitle(currentTournoi.nomTournoi);
                }else{
                    currentTournoi.setNomTournoi(save);
                }
            }

        }
    }
}
