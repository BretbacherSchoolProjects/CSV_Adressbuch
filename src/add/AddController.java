package add;

import adressbuch.Controller;
import adressbuch.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    //Variables
    @FXML
    TextField txtf_firstName=new TextField();
    @FXML
    TextField txtf_lastName=new TextField();
    @FXML
    TextField txtf_number=new TextField();
    @FXML
    TextField txtf_address=new TextField();

    AddModel model=new AddModel();
    Stage stage;

    Controller parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public static void show(Stage stage, Controller parentController)throws Exception{
        FXMLLoader loader = new FXMLLoader(AddController.class.getResource("addView.fxml"));
        Parent root = loader.load();

        AddController ctrl = loader.getController();
        ctrl.setStage(stage);
        ctrl.setParent(parentController);

        stage.setTitle("Moin");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handle_add(){
        model.add(parent.getModel(), txtf_firstName.getText(), txtf_lastName.getText(), txtf_number.getText(), txtf_address.getText());
        stage.close();
        parent.update(false);

    }

}
