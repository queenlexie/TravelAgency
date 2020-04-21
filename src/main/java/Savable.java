import java.io.FileNotFoundException;
import java.io.IOException;

public interface Savable {
    void savaAsBIN(String name);
    Object readBIN(String name) throws IOException;
    void saveAsXML(String name);
}
