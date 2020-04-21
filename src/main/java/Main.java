
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String args[]) throws WrongPESELException, IOException {
        Person p1 = Person.makePerson("Anna", "Nowak");
        Person p2 = Person.makePerson("Jan", "Nowak");
        Person p3 = Person.makePerson("Joanna", "Kowalska", LocalDate.of(1996, Month.MAY, 12), "96051212345", Person.Sex.F );
        Person p4 = Person.makePerson("Pimpon", "Kot", LocalDate.of(2017, Month.MAY, 14), "17051412375", Person.Sex.M);
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4);
        Tourist t1= new Tourist(p1);
        Tourist t2= new Tourist(p2);
        Tourist t3= new Tourist(p3);
        Tourist t4= new Tourist(p4);
        Tourist t5= new Tourist("Jan", "But", LocalDate.of(1980, Month.APRIL, 10), "80041012335", Person.Sex.M, "Japan", "Plaza", Tourist.Catering.BB);
        Tourist t6= new Tourist("Elzieta", "But", LocalDate.of(1982, Month.DECEMBER, 21), "82122134567", Person.Sex.F, "Japan", "Plaza", Tourist.Catering.HB );
//        System.out.println(t1);
//        System.out.println(t2);
//        System.out.println(t3);
//        System.out.println(t4);
//        System.out.println(t5);
//        System.out.println(t6);

        TravelGuide guide = new TravelGuide("Andrzej", "Grabowski", LocalDate.of(1973, Month.FEBRUARY, 2), "73020234517", Person.Sex.M, 10 );
        List<Tourist> touristList = new ArrayList<>();
        Travel t = new Travel("Rainbow", guide ,touristList);
        t.addTourist(t1);
        t.addTourist(t2);
        t.addTourist(t3);
        t.addTourist(t4);
        t.addTourist(t5);
        t.addTourist(t6);
        //System.out.println(t);

        t.savaAsBIN("travel.bin");
        //System.out.println(t.readBIN("travel.bin"));
        t.saveAsXML("travel.xml");
        System.out.println(Travel.deserializeFromXML("travel.xml"));

    }
}
