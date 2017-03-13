package assets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Bob, Bram en Michel on 6-2-2017.
 */

public class Festival implements Serializable {
    private int visitorAmount;
    private int price;  // price in Euro's
    private LocalTime end;    // In hours
    private LocalTime start;  // In hours
    private String name;
    private LocalDate day;
    private ArrayList<Artist> artists;
    private ArrayList<Visitor> visitors;
    private ArrayList<Stage> stages;
    private ArrayList<Performance> performances;

    public Festival()
    {
        this(0, 0, LocalDate.parse("2017-05-23", DateTimeFormatter.ISO_DATE), LocalTime.parse("06:00"), LocalTime.parse("23:00"), "fest");
    }

    public Festival(int visitorAmount, int price, LocalDate day, LocalTime start, LocalTime end, String name) {
        this.visitorAmount = visitorAmount;
        this.price = price;
        this.day = day;
        this.start = start;
        this.end = end;
        this.name = name;

        artists = new ArrayList<>();
        visitors = new ArrayList<>();
        stages = new ArrayList<>();
        performances = new ArrayList<>();
    }

    public void setDay(LocalDate day) { this.day = day; }
    public void setStart(LocalTime start) { this.start = start; }
    public void setEnd(LocalTime end) { this.end = end; }
    public void setVisitorAmount(int visitorAmount) { this.visitorAmount = visitorAmount; }
    public void setPrice(int price) { this.price = price; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDay() { return this.day; }
    public LocalTime getStart() { return this.start; }
    public LocalTime getEnd() { return this.end; }
    public int getVisitorAmount() { return this.visitorAmount; }
    public int getPrice() { return this.price; }
    public String getName() { return this.name; }

    public void addPerformance(Performance performance) {
        performances.add(performance);
    }
    public void addArtist(Artist artist) {
        artists.add(artist);
    }
    public void addStage(Stage stage) {
        stages.add(stage);
    }
    public void addVisitor(Visitor visitor) { visitors.add(visitor); }

    public void addPerformances(ArrayList<Performance> performances) { this.performances = performances; }
    public void addArtists(ArrayList<Artist> artists) { this.artists = artists; }
    public void addStages(ArrayList<Stage> stages) { this.stages = stages; }
    public void addVisitors(ArrayList<Visitor> visitors) { this.visitors = visitors; }

    public Performance getPerformance(int index) { return performances.get(index); }
    public Artist getArtist(int index) { return artists.get(index); }
    public Stage getStage(int index) {
        return stages.get(index);
    }
    public Visitor getVisitor(int index) { return visitors.get(index); }

    public ArrayList<Performance> getPerformances() { return performances; }
    public ArrayList<Stage> getStages() { return stages; }
    public ArrayList<Artist> getArtists() {
        return artists;
    }
    public ArrayList<Visitor> getVisitors() { return visitors; }

    public String toString(){
        String lijst = "";
        lijst = "AantalBezoekers: " + visitorAmount + "\n"
                + "Prijs: " + price + " euro" + "\n"
                + "Dag: " + day + "\n"
                + "Begintijd: " + start + "\n"
                + "Eindtijd : " + end + "\n"
                + "Naam festival: " + name + "\n";
        return lijst;
    }
    public void Print(){
        System.out.println(toString());
    }

    public static void save(String path, Festival festival) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File output = new File(path);
        FileWriter writer = new FileWriter(output);
        gson.toJson(festival, writer);
        writer.close();
    }

    public static Festival load(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(path), Festival.class);

//        Type listOfTestObject = new TypeToken<List<TestObject>>(){}.getType();
//        String s = gson.toJson(list, listOfTestObject);
//        List<TestObject> list2 = gson.fromJson(s, listOfTestObject);

//        this.setDay(f.getDay());
//        this.setEnd(f.getEnd());
//        this.setName(f.getName());
//        this.setPrice(f.getPrice());
//        this.setVisitorAmount(f.getVisitorAmount());
//        this.setStart(f.getStart());
//
//        this.addPerformances(f.getPerformances());
//        this.addStages(f.getStages());
//        this.addArtists(f.getArtists());
//        this.addVisitors(f.getVisitors());
    }
}
