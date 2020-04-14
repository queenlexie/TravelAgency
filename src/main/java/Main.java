
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main (String args[]) throws WrongPESELException {
        Person t1 = new Person("Anna", "Nowak");
        Person t2 = new Person("Jan", "Nowak");
        Person t3 = new Person("Joanna", "Kowalska", LocalDate.of(2017, Month.MAY, 14), "96031212345", Person.Sex.F );
        Person t4 = new Person("Pimpon", "Kot", LocalDate.of(2017, Month.MAY, 14), "17051412345", Person.Sex.F );
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
    }
}
