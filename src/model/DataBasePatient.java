package model;

import controller.Controller;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.naming.ldap.Control;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataBasePatient {

    public String nomDataBase;
    public ArrayList<Patient> listPatient;
    public Controller ctrl;

    public DataBasePatient(String nomDataBase, Controller ctrl) {
        this.nomDataBase = nomDataBase;
        this.listPatient = new ArrayList<>();
        this.ctrl = ctrl;
    }

    public void save(){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("patients");
            doc.appendChild(rootElement);

            for (Patient value : this.listPatient) {
                // patient
                Element patient = doc.createElement("patient");
                rootElement.appendChild(patient);
                // prenom
                Attr prenom = doc.createAttribute("prenom");
                prenom.setValue(value.prenom);
                patient.setAttributeNode(prenom);
                // nom
                Attr nom = doc.createAttribute("nom");
                nom.setValue(value.nom);
                patient.setAttributeNode(nom);
            }

            /*
            // carname element
            Element carname = doc.createElement("carname");
            Attr attrType = doc.createAttribute("type");
            attrType.setValue("formula one");
            carname.setAttributeNode(attrType);
            carname.appendChild(doc.createTextNode("Ferrari 101"));
            supercar.appendChild(carname);

            Element carname1 = doc.createElement("carname");
            Attr attrType1 = doc.createAttribute("type");
            attrType1.setValue("sports");
            carname1.setAttributeNode(attrType1);
            carname1.appendChild(doc.createTextNode("Ferrari 202"));
            supercar.appendChild(carname1);
*/

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File( this.ctrl.getCheminDataBase()));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
