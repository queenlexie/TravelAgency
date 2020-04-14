import java.io.Serializable;
import java.time.LocalDate;

public class Tourist extends Person implements Serializable, Cloneable, Comparable<Tourist> {
    private String country;
    private String hotel;
    private String catering;

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

    public String getCatering() {
        return catering;
    }

    public void setCatering(String catering) {
        this.catering = catering;
    }
    //</editor-fold>

    //<editor-fold desc="constructors">
    public Tourist() throws WrongPESELException {
        super();
    }

    public Tourist(String name, String surname) {
        super(name, surname);
    }

    public Tourist(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex) throws WrongPESELException {
        super(name, surname, dateOfBirth, PESEL, sex);
    }

    public Tourist(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex, String country, String hotel, String catering) throws WrongPESELException {
        super(name, surname, dateOfBirth, PESEL, sex);
        this.country = country;
        this.hotel = hotel;
        this.catering = catering;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Tourist{" +
                "country='" + country + '\'' +
                ", hotel='" + hotel + '\'' +
                ", catering='" + catering + '\'' +
                '}';
    }
    public Object clone()throws CloneNotSupportedException{
        return (Tourist)super.clone();
    }
    @Override
    public int compareTo(Tourist other){
        return this.getPESEL().compareTo(other.getPESEL());
    }
}
