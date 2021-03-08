package adressbuch;

import java.io.*;
import java.util.*;

public class Model{
    final String PATH_TO_FILE = System.getProperty("user.dir") + "\\src\\Adressbuch.csv";
    File book = new File(PATH_TO_FILE);

    int displayedRow=0;
    ArrayList<Item> contacts=new ArrayList<>();

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
        contacts.add(new Item(name, surname, adress, number));
        sortContacts();
    }

    public void deleteEntry(String number){
        for (int i=0;i<contacts.size();i++) {
            if (contacts.get(i).getPhoneNr().equals(number)){
                contacts.remove(i);
                System.out.println("Contact Nr. " + number + " removed.");
                break;
            }
        }
    }

    public void delete(int index){
        contacts.remove(index);
    }

    public void saveChanges(int index, String fullname, String adress, String number){
        if (!fullname.equals("") && !adress.equals("") && !number.equals("")) {
            if (contacts.size()>0) {
                String[] name = fullname.split(" ");

                if (name.length == 1) {
                    String first = name[0];
                    name = new String[2];
                    name[0] = first;
                    name[1] = " ";
                }

                contacts.set(index, new Item(name[0], name[1], adress, number));
                System.out.println("Saved changes.");
            }else{
                System.out.println("The phonebook is empty. Consider adding a contact instead.");
            }
        }else{
            System.out.println("Error: Can't save Entry with empty fields!");
        }
        sortContacts();
    }

    public void loadFromCSV()throws Exception{
        System.out.println("Loading...");
        BufferedReader br=new BufferedReader(new FileReader(PATH_TO_FILE));
        contacts.clear();
        Item item;
        String line;
        br.readLine();
        while ((line=br.readLine())!=null){
            item = new Item(line, ";");
            contacts.add(item);
        }
        br.close();
        System.out.println("finished loading.");
    }

    public void sortContacts(){
        contacts.sort(Item::compareTo);
    }

    public void saveToCSV()throws Exception{
        System.out.println("Saving...");
        BufferedWriter bw=new BufferedWriter(new FileWriter(PATH_TO_FILE));
        bw.write("CSVFILE CONTACTS");
        for (Item contact :contacts) {
            bw.newLine();
            bw.write(contact.toString());
            System.out.println(contact);
        }

        bw.close();
        System.out.println("finished saving.");
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

    public Model()throws Exception{
        if (book.createNewFile()){
            System.out.println("File created.");
        }
        loadFromCSV();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    saveToCSV();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }, "Shutdown-thread"));
    }

    public Item search(String term){
        displayedRow=0;
        for (Item contact : contacts) {
            if (contact.toString().contains(term))
                return contact;
            displayedRow++;
        }
        return contacts.get(displayedRow);
    }

    public ArrayList<Item> getContacts() {
        return contacts;
    }
}
