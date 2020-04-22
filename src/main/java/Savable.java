import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Savable {
    void saveAsBIN(String name);
    void saveAsXML(String name);
    void writeAsJSON(String name);
}
