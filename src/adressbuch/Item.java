package adressbuch;

public class Item implements Comparable{
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNr;

    public void setAttributesFromString(String csvLine, String regex){
        String[] split = csvLine.split(regex);
        firstName = split[0];
        lastName = split[1];
        phoneNr = split[2];
        address = split[3];
    }

    public String convertToCSV(String regex){
        return firstName + regex + lastName + regex + phoneNr + regex + address;
    }

    public Item(String firstName, String lastName, String address, String phoneNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNr = phoneNr;
    }

    public Item(String csvLine, String regex){
        setAttributesFromString(csvLine, regex);
    }

    @Override
    public String toString() {
        return firstName + ";" + lastName + ";" + phoneNr + ";" + address + ";";
    }

    @Override
    public int compareTo(Object o) {
        Item compared = (Item) o;
        return this.getFirstName().compareToIgnoreCase(compared.getFirstName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
