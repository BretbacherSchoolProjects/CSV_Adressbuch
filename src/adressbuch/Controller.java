package adressbuch;

import add.AddController;
import delete.DeleteController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Variables
    Model model=new Model();
    @FXML
    Label contactID=new Label();
    @FXML
    TextField fullname=new TextField();
    @FXML
    TextField address=new TextField();
    @FXML
    TextField number=new TextField();
    @FXML
    TextField searchterm=new TextField();

    public Controller() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update(true);
    }

    public static void show(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(Controller.class.getResource("mainView.fxml"));
            Parent root = loader.load();

            stage.setTitle("Moin");
            stage.setScene(new Scene(root));

            stage.show();

        }catch(Exception ignored){
            System.out.println("Error");
        }
    }

    public void handle_Prev(){
        model.prevRow();
        update(true);
    }

    public void handle_next(){
        model.nextRow();
        update(true);
    }

    public void handle_add()throws Exception{
        AddController.show(new Stage(), this);
    }

    public void handle_delete()throws Exception{
        DeleteController.show(new Stage(), this);
    }

    public void handle_save()throws Exception{
        model.saveToCSV();
    }

    public void handle_saveChanges(){
        model.saveChanges(model.getDisplayedRow(), fullname.getText(), address.getText(), number.getText());
        update(false);
    }

    public void handle_load()throws Exception{
        model.loadFromCSV();
        update(true);
    }

    public void handle_search(){
        Item contact=model.search(searchterm.getText());
        fullname.setText(contact.getFirstName() + " " + contact.getLastName());
        address.setText(contact.getAddress());
        number.setText(contact.getPhoneNr());
        update(true);
    }

    public void update(boolean isNavigated){
        int currentIndex;

        if (isNavigated)
            currentIndex=model.getDisplayedRow();
        else {
            currentIndex = 0;
            model.setDisplayedRow(currentIndex);
        }

        if (model.getContacts().size()>=1) {
            Item contact = model.getContacts().get(currentIndex);
            String name =contact.getFirstName() + " " + contact.getLastName();
            fullname.setText(name);
            number.setText(contact.getPhoneNr());
            address.setText(contact.getAddress());
            contactID.setText((currentIndex + 1) + "/" + model.getContacts().size());
        }
    }

    public Model getModel() {
        return model;
    }
}
