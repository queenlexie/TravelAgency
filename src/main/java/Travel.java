
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;

public class Travel implements Cloneable, Comparable<Travel>, Savable, Serializable {

    private int numberOfParticipants;
    private String name;
    private TravelGuide travelGuide;
    public List<Tourist> touristList;
    public Travel(){
        numberOfParticipants=0;
        name="";
        travelGuide=null;
        touristList= new ArrayList<>();
    }

    public Travel(String name, TravelGuide travelGuide, List<Tourist> touristList) {
        super();
        this.name = name;
        this.travelGuide = travelGuide;
        this.touristList = touristList;
    }

    //<editor-fold desc="getters, setters">
    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TravelGuide getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(TravelGuide travelGuide) {
        this.travelGuide = travelGuide;
    }

    public List<Tourist> getTouristList() {
        return touristList;
    }

    public void setTouristList(List<Tourist> touristList) {
        this.touristList = touristList;
    }
    //</editor-fold>

    public void addTourist(Tourist t){
        touristList.add(t);
        numberOfParticipants++;
    }
    public boolean isPersonAtTravel(String PESEL){
        return touristList.stream().anyMatch(o -> PESEL.equals(o.getPESEL()));
    }
    public boolean isPersonAtTravel(String name, String surname) {
        return touristList.stream().anyMatch(o -> (name.equals(o.getName()) && surname.equals(o.getSurname())));
    }
    public void deleteTourist(Tourist t){
        if(!isPersonAtTravel(t.getPESEL()))
            System.out.println("There is no such a person");
        else
            touristList.remove(t);
    }
    public void deleteTourist(String name, String surname){
        if(!isPersonAtTravel(name, surname))
            System.out.println("There is no such a person");
        else
            touristList.removeIf(s -> (s.getName().equals(name) && s.getSurname().equals(surname)));
    }
    public void endTravel(){
        touristList.clear();
        numberOfParticipants=0;
    }
    public List<Tourist> findTouristsForCountry(String country){
        List<Tourist> myList = new ArrayList<Tourist>();
        for(Tourist t : getTouristList())
            if (country.equals(t.getCountry())) myList.add(t);
        return myList;
    }

    @SuppressWarnings("unchecked")
    public Travel clone() throws CloneNotSupportedException {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream out = new ObjectOutputStream(bos);
//        out.writeObject(t);
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//        ObjectInputStream in = new ObjectInputStream(bis);
//        return (Travel) in.readObject();
        return (Travel) super.clone();
    }
    public void sort(){
        touristList.sort(Tourist::compareTo);
    }
    public void sortByPESEL(){
        touristList.sort(new PESELComparator());
    }
    public boolean isTouristInThisTravel(Tourist t){
        for(Tourist tourist : touristList)
            if(tourist.equals(t))
                return true;
            return false;
    }
    public boolean equals(Travel other){
        if(other==null)
            return false;
        return this.getName().equals(other.getName()) && this.getTravelGuide().equals(other.travelGuide) && this.getNumberOfParticipants()==other.getNumberOfParticipants();
    }

    @Override
    public String toString() {
        StringBuilder myStringBuilder = new StringBuilder("Travel: " + name + ". \n");
        myStringBuilder.append("Your guide is: " + travelGuide + ".\nList of tourists:\n");
        for (Tourist c : touristList)
            myStringBuilder.append(c + "\n");
        return myStringBuilder.toString();
    }

    @Override
    public int compareTo(Travel travel) {
       int result= CharSequence.compare(name, travel.getName());
       if(result==0)
           result=Integer.compare(numberOfParticipants, travel.getNumberOfParticipants());
       return  result;
    }

    //<editor-fold desc="serialization">

    @Override
    public void simpleSerialization(){
        try{
            FileOutputStream fos = new FileOutputStream("file.ser");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File.ser not found");
        } catch (IOException e) {
            System.out.println("ERROR: While Opening the File.ser");
        }
    }
    public static Travel simpleDeserialization(){
        Travel travel=null;
        try{
            FileInputStream fis = new FileInputStream("file.ser");
            ObjectInputStream ois=new ObjectInputStream(fis);
            travel= (Travel) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File.ser not found");
        } catch (IOException e) {
            System.out.println("ERROR: While Opening the File.ser");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Class not found");
        }
        return travel;
    }

    //binary serialization
    @Override
    public void saveAsBIN(String name) {
        try {
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("ERROR: While Creating the File.bin");
        }
    }

    public static Object readBIN(String name) throws IOException {
        FileInputStream fileIn = new FileInputStream(name);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Travel output = null;
        try {
            output = (Travel) in.readObject();
        }
        catch (IOException | ClassNotFoundException exc) {
            System.out.println("ERROR: While Opening the File.bin");
        }
        return output;
    }

    //XML serialization
    @Override
    public void saveAsXML(String name){
        XMLEncoder encoder=null;
        try{
            encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(name)));
        }catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR: While Creating or Opening the File.xml");
        }
        encoder.writeObject(this);
        encoder.close();
    }
    public static Travel readFromXML (String name){
            XMLDecoder decoder=null;
            try {
                decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(name)));
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: File.xml not found");
            }
            return (Travel) decoder.readObject();
        }

        //simple json serialization
    public void writeAsSimpleJSON(String name) throws FileNotFoundException, JsonProcessingException {
        //TODO
//        JSONObject obj = new JSONObject();
//        obj.writeJSONString(Travel.class);
//
//        JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(name));
//        jsonWriter.writeObject(Travel.class);
//        jsonWriter.close();
        //InputStream fis = new FileInputStream(name);

       // JsonParser jsonParser = Json.createParser(fis);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(this);
        //JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(name));
       // jsonWriter.writeObject(jsonString);


        //jsonWriter.close();
    }

    public static void readFromSimpleJSON(String name) throws IOException {
        //TODO
//        InputStream fis = new FileInputStream(name);
//        JsonReader jsonReader = Json.createReader(fis);
//        JsonObject jsonObject = jsonReader.readObject();
//        jsonReader.close();
//        fis.close();
//        return (Travel) jsonObject;

//        JSONObject output=null;
//        JSONParser parser = new JSONParser();
//        try (Reader reader = new FileReader(name)) {
//            output = (JSONObject) parser.parse(reader);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        //return (Travel) output;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(name));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Override
        //json serialization using Jackson
    public void writeAsJSON(String name){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(name), this);}
         catch(JsonGenerationException e){
             System.out.println("ERROR: While Generating the File.json");
        }
        catch(JsonMappingException e){
            System.out.println("ERROR: While Mapping the File.json");
        }
        catch( IOException e){
            System.out.println("ERROR: While Creating the File.json");
        }
    }

    public static Travel readFromJSON(String name) throws IOException {
        Travel output= null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            InputStream input = new FileInputStream(name);
            output= mapper.readValue(input, Travel.class);
        }
        catch(JsonParseException e){
            System.out.println("ERROR: While Parsing the File.json");
        }
        catch(JsonMappingException e){
            System.out.println("ERROR: While Mapping the File.json");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("ERROR: While Opening the File.json");
        }
       return output;

    }
        //</editor-fold>



    class PESELComparator implements Comparator<Tourist> {
        @Override
        public int compare(Tourist tourist, Tourist tourist1) {
            if(tourist!=null && tourist1!=null)
                return tourist.getPESEL().compareTo(tourist1.getPESEL());
            else
                return 0;
        }
    }
}
