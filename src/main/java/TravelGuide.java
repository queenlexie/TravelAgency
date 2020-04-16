import java.io.Serializable;
import java.time.LocalDate;

public class TravelGuide extends Person implements Cloneable, Serializable {
    private int expierience;

    public int getExpierience() {
        return expierience;
    }

    public void setExpierience(int expierience) {
        this.expierience = expierience;
    }

    public TravelGuide() throws WrongPESELException {
        super();
    }

    public TravelGuide(String name, String surname) {
        super(name, surname);
    }

    public TravelGuide(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex) throws WrongPESELException {
        super(name, surname, dateOfBirth, PESEL, sex);
    }

    public TravelGuide(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex, int expierience) throws WrongPESELException {
        super(name, surname, dateOfBirth, PESEL, sex);
        this.expierience = expierience;
    }
    @Override
    public String toString(){
        return this.getName() + " " + this.getSurname() + " " + this.getDateOfBirth() + " " + this.getPESEL() + " " + this.getSex() + " "+ this.getExpierience();
    }
}
