package delete;

import adressbuch.Model;

import java.text.ParseException;

public class DeleteModel {
    public void delete(Model mainModel, String contact){
            Integer.parseInt(contact);
            mainModel.deleteEntry(contact);
    }
}
