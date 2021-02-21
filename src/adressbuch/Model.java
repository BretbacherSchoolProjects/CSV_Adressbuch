package adressbuch;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model{
    final String PATH_TO_FILE = System.getProperty("user.dir") + "\\src\\Adressbuch.csv";
    int displayedRow=0;
    ArrayList<String> contacts=new ArrayList<>();

    public void nextRow(){
        if (contacts.size()>=1) {
            if (displayedRow < contacts.size() - 1)
                displayedRow++;
            else
                displayedRow = 0;
        }
    }

    public void prevRow(){
        if (contacts.size()>=1) {
        if (displayedRow>0)
            displayedRow--;
        else
            displayedRow=contacts.size()-1;
        }
    }

    public int getDisplayedRow() {
        return displayedRow;
    }

    public void setDisplayedRow(int displayedRow) {
        this.displayedRow = displayedRow;
    }

    public void add(String name, String surname, String number, String adress){
        String line = "\n" + name + ";" + surname + ";" + number + ";" + adress;
        contacts.add(line);
    }

    public void deleteEntry(String number){
        for (int i=0;i<contacts.size();i++) {
            if (contacts.get(i).contains(number)){
                contacts.remove(i);
                break;
            }
        }
    }

    public void delete(int index){
        contacts.remove(index);
    }

    public void saveChanges(int index, String fullname, String adress, String number){
        String[] name=fullname.split(" ");

        String line = "\n" + name[0] + ";" + name[1] + ";" + number + ";" + adress;
        contacts.set(index, line);
    }

    public void loadFromCSV()throws Exception{
        BufferedReader br=new BufferedReader(new FileReader(PATH_TO_FILE));
        contacts.clear();
        String line;
        while ((line = br.readLine())!=null){
            contacts.add(line);
        }
        br.close();
    }

    public void saveToCSV()throws Exception{
        BufferedWriter bw=new BufferedWriter(new FileWriter(PATH_TO_FILE));

        for (String contact :contacts) {
            bw.write(contact);
        }

        bw.close();
    }

    /* mein versuch an einer sortier Methode.
    public void sortContacts() {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        for (int i = 0; i < contacts.size(); i++) {
            String[] split = contacts.get(i).split(";");
            lines.add(split);
        }
        Comparator<String[]> comp = new Comparator<>() {
            public int compare(String[] line1, String[] line2) {
                return line1[0].compareTo(line2[2]);
            }
        };
        Collections.sort(lines, comp);

        contacts.clear();
        for (int i = 0; i < lines.size(); i++) {
            StringBuilder string= new StringBuilder();
            for (int j = 0; j < lines.get(i).length; j++) {
                string.append(lines.get(i)[j]).append(";");
                contacts.add(string.toString());
            }
        }
    }

     */

    public String[] search(String term){
        displayedRow=0;
        for (String contact : contacts) {
            if (contact.contains(term))
                return contact.split(";");
            displayedRow++;
        }
        return new String[4];
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }
}
