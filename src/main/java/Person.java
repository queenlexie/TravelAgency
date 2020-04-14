
import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person implements Serializable {
    public enum Sex { F, M };
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String PESEL;
    private Sex sex;

    //<editor-fold desc="constructors">
    public Person() throws WrongPESELException {
        setName("");
        setSurname("");
        setDateOfBirth(LocalDate.now());
        setPESEL(RandomStringUtils.randomAlphanumeric(11).toUpperCase());
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public Person(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex) throws WrongPESELException {
        this(name, surname);
        this.dateOfBirth = dateOfBirth;
        setPESEL(PESEL);
        this.sex = sex;
    }
    //</editor-fold>

    //<editor-fold desc="getters, setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPESEL() {
        return PESEL;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
    public void setPESEL(String pesel) throws WrongPESELException {
        if (pesel.length() != 11) throw new WrongPESELException("Wrong length of PESEL ");
        if (pesel.substring(0, 6) != dateOfBirth.format(DateTimeFormatter.ofPattern("yyMMdd"))) throw new WrongPESELException("PESEL differs from date of birth!");
        if (!isDigitsOnly(pesel)) throw new WrongPESELException("PESEL should have only digits");
        PESEL = pesel;
    }
    //</editor-fold>
    boolean isDigitsOnly(String str) {
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9')
                return false; }
      return true;
    }
    public int Age() {
        return Period.between(getDateOfBirth(), LocalDate.now()).getYears();
    }
    @Override
    public String toString() {
        return getName() + " " + getSurname() + " " + getDateOfBirth() + " " + getPESEL() + " " + getSex();
    }
    public boolean Equals(Person other) {
        if (other == null)
            return false;
        return this.getPESEL() == other.getPESEL();}


}
