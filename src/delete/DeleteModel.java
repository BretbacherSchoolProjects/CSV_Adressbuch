package delete;

import adressbuch.Controller;
import adressbuch.Model;

import java.text.ParseException;

public class DeleteModel {
    public void delete(Model mainModel, Controller mainController, String contact){
            //Integer.parseInt(contact);
            mainModel.deleteEntry(contact);
    }
}
