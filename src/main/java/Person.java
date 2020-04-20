
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
    protected Person() {
        setName("");
        setSurname("");
        setSex(Sex.M);
        setDateOfBirth(LocalDate.now());
        setPESEL(RandomStringUtils.randomAlphanumeric(11).toUpperCase());
    }
    protected Person(String name, String surname) {
        super();
        this.name = name;
        this.surname = surname;
    }
    protected Person(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex){
        this(name, surname);
        this.dateOfBirth = dateOfBirth;
        this.PESEL = PESEL;
        this.sex = sex;
    }
    protected static void validatePESEL(String PESEL, LocalDate dateOfBirth) throws WrongPESELException {
        if (PESEL.length() != 11) throw new WrongPESELException("Wrong length of PESEL ");
        if (!PESEL.substring(0, 6).equals(dateOfBirth.format(DateTimeFormatter.ofPattern("yyMMdd")))) throw new WrongPESELException("PESEL differs from date of birth!");
        if (!isDigitsOnly(PESEL)) throw new WrongPESELException("PESEL should have only digits");
    }
    public static Person makePerson(){
        return new Person();
    }
    public static Person makePerson(String name, String surname){
        return new Person(name, surname);
    }
    public static Person makePerson(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex) throws WrongPESELException {
        validatePESEL(PESEL, dateOfBirth);
        return new Person(name, surname, dateOfBirth, PESEL, sex);
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
    public void setPESEL(String pesel){ PESEL = pesel; }
    //</editor-fold>
    static boolean isDigitsOnly(String str) {
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
