package add;

import adressbuch.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class AddModel {
    //Variables
    public void add(Model mainModel, String firstName, String lastName, String number, String address){
        mainModel.add(firstName, lastName, number, address);
    }
}
