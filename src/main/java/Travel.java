import java.util.ArrayList;
import java.util.List;

public class Travel implements Cloneable, Comparable<Travel>, Savable {

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

    public Travel(int numberOfParticipants, String name, TravelGuide travelGuide, List<Tourist> touristList) {
        super();
        this.numberOfParticipants = numberOfParticipants;
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
        return touristList.stream().filter(o -> o.getPESEL().equals(PESEL)).findFirst().isPresent();
    }
    public boolean isPersonAtTravel(String name, String surname) {
        return touristList.stream().filter(o -> (o.getName().equals(name) && o.getSurname().equals(surname))).findFirst().isPresent();
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
        for(Tourist t : touristList)
            if (t.getCountry() == country) myList.add(t);
        return myList;
    }

    @Override
    public String toString() {
        StringBuilder myStringBuilder = new StringBuilder("Travel: " + name);
        myStringBuilder.append("Your guide is: " + travelGuide);
        for (Tourist c : touristList)
            myStringBuilder.append(c);
        return myStringBuilder.toString();
    }

    @Override
    public int compareTo(Travel travel) {
        return 0;
    }

    @Override
    public void savaAsBIN(String name) {

    }

    @Override
    public Object readBIN(String name) {
        return null;
    }
}
