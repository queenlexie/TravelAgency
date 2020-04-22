
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Person implements Serializable {
    public enum Sex { F, M };
    private String name;
    private String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String PESEL;
    private Sex sex;

    //<editor-fold desc="constructors">
    protected Person() {
        setName("");
        setSurname("");
        setSex(Sex.M);
        setPESEL(RandomStringUtils.randomNumeric(2) +  String.format("%02d",RandomUtils.nextInt(12)+1) +  String.format("%02d",RandomUtils.nextInt(28)+1) + RandomStringUtils.randomNumeric(5) );
        setDateOfBirth(LocalDate.parse(getPESEL().substring(0, 6), DateTimeFormatter.ofPattern("yyMMdd")));
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
    protected static void validatePESEL(String PESEL, LocalDate dateOfBirth, Sex sex) throws WrongPESELException {
        if (PESEL.length() != 11) throw new WrongPESELException("Wrong length of PESEL ");
        if (!PESEL.substring(0, 6).equals(dateOfBirth.format(DateTimeFormatter.ofPattern("yyMMdd")))) throw new WrongPESELException("PESEL differs from date of birth!");
        if (!isDigitsOnly(PESEL)) throw new WrongPESELException("PESEL should have only digits");
        if((int) PESEL.charAt(9)%2==0 && sex.equals(Sex.M)) throw new WrongPESELException("PESEL is dedicated for man, but sex is female!");
        if((int) PESEL.charAt(9)%2!=0 && sex.equals(Sex.F)) throw new WrongPESELException("PESEL is dedicated for woman, but sex is male!");
    }
    public static Person makePerson(){
        return new Person();
    }
    public static Person makePerson(String name, String surname){
        return new Person(name, surname);
    }
    public static Person makePerson(String name, String surname, LocalDate dateOfBirth, String PESEL, Sex sex) throws WrongPESELException {
        validatePESEL(PESEL, dateOfBirth, sex);
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
    public boolean equals(Person other) {
        if (other==null)
            return false;
        return this.getPESEL().equals(other.getPESEL());}


}
