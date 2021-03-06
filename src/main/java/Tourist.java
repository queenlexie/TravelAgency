import java.io.Serializable;
import java.time.LocalDate;

public class Tourist extends Person implements Serializable, Cloneable, Comparable<Tourist> {
    public enum Catering{
        HB, AllIncusive, BB, NoCatering;
    }
    private String country;
    private String hotel;
    private Catering catering;

    //<editor-fold desc="getters, setters">
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Catering getCatering() {
        return catering;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }
    //</editor-fold>

    //<editor-fold desc="constructors">
    public Tourist() {
        super();
    }
    public Tourist(Person p){
        super(p.getName(), p.getSurname(),p.getDateOfBirth(),p.getPESEL(),p.getSex());
    }

    public Tourist(String name, String surname) {
        super(name, surname);
    }

    public Tourist(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex){
        super(name, surname, dateOfBirth, PESEL, sex);
    }

    public Tourist(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex, String country, String hotel, Catering catering){
        super(name, surname, dateOfBirth, PESEL, sex);
        this.country = country;
        this.hotel = hotel;
        this.catering = catering;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname() + " " + this.getDateOfBirth() + " " + this.getPESEL() + " " + this.getSex() + " " + "Tourist{" +
                "country='" + country + '\'' +
                ", hotel='" + hotel + '\'' +
                ", catering='" + catering + '\'' +
                '}';
    }
    public Tourist clone()throws CloneNotSupportedException{
        return (Tourist) super.clone();
    }
    @Override
    public int compareTo(Tourist other){
        return this.getPESEL().compareTo(other.getPESEL());
    }
}
