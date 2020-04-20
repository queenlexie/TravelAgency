import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class Testing {
    Person p =new Person("Anna", "Nowak");
    @Test
    public void shouldSaveBinFile() throws IOException {
    }
    @Test
    public void shouldNotReturnPESELException() throws WrongPESELException {
        Person.validatePESEL("96051412345", LocalDate.of(1996, Month.MAY, 14));
    }
    @Test(expected = WrongPESELException.class)
    public void shouldReturnExceptionForPESEL() throws WrongPESELException {
        Person.validatePESEL("96051412345", LocalDate.of(1994, Month.MAY, 14));
    }
    @Test(expected = WrongPESELException.class)
    public void shouldReturnExceptionForNotLongEnoughPESEL() throws WrongPESELException {
        Person.validatePESEL("12345", LocalDate.of(1994, Month.MAY, 14));
    }
    @Test(expected = WrongPESELException.class)
    public void shouldReturnExceptionForNotDigitsOnlyPESEL() throws WrongPESELException {
        Person.validatePESEL("12345aaaaaa", LocalDate.of(1994, Month.MAY, 14));
    }
    @Test
    public void dateOfBirthAndPESELShouldBeEqual(){
        String pesel= "94051412345";
        LocalDate date=LocalDate.of(1994, Month.MAY, 14);
        assertEquals(date.format(DateTimeFormatter.ofPattern("yyMMdd")), pesel.substring(0, 6));
    }
}
