package delete;

import add.AddController;
import adressbuch.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {
    //Variables
    DeleteModel model=new DeleteModel();
    Stage stage;

    Controller parent;

    @FXML
    TextField txtf_contactToDelete=new TextField();


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
        FXMLLoader loader = new FXMLLoader(DeleteController.class.getResource("deleteView.fxml"));
        Parent root = loader.load();

        DeleteController ctrl = loader.getController();
        ctrl.setStage(stage);
        ctrl.setParent(parentController);

        stage.setTitle("Moin");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handle_delete(){
        model.delete(parent.getModel(), parent, txtf_contactToDelete.getText());
        stage.close();
        parent.update(false);
    }
}
