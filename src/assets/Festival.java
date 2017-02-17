package assets;

import com.google.gson.Gson;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by Bob, Bram en Michel on 6-2-2017.
 */

public class Festival implements Serializable {
    private int visitorAmount;
    private int price;  // prijs in Euro's
    private int end;    // In uur
    private int start;  // In Uur
    private String name;
    private String day;
    private ArrayList<Artist> artists;
    private ArrayList<Visitor> visitors;
    private ArrayList<Stage> stages;
    private ArrayList<Performance> performances;

    public Festival(int visitorAmount, int price, LocalDate parse, LocalTime localTime, LocalTime time, String testfest)
    {
        this(0, 0, "", 0, 0, "");
    }

    public Festival(int visitorAmount, int price, String day, int start, int end, String name) {
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

    public void setDay(String day) { this.day = day; }
    public void setStart(int start) { this.start = start; }
    public void setEnd(int end) { this.end = end; }
    public void setVisitorAmount(int visitorAmount) { this.visitorAmount = visitorAmount; }
    public void setPrice(int price) { this.price = price; }
    public void setName(String name) { this.name = name; }

    public String getDay() { return this.day; }
    public int getStart() { return this.start; }
    public int getEnd() { return this.end; }
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

    public void addPerformances(Performance[] performances) { Collections.addAll(this.performances, performances); }
    public void addArtists(Artist[] artists) { Collections.addAll(this.artists, artists); }
    public void addStages(Stage[] stages) { Collections.addAll(this.stages, stages); }
    public void addVisitors(Visitor[] visitors) { Collections.addAll(this.visitors, visitors); }

    public Performance getPerformance(int index) { return performances.get(index); }
    public Artist getArtist(int index) { return artists.get(index); }
    public Stage getStage(int index) {
        return stages.get(index);
    }
    public Visitor getVisitor(int index) { return visitors.get(index); }

    public Performance[] getPerformances() {
        return (Performance[])performances.toArray();
    }
    public Stage[] getStages() {
        return (Stage[])stages.toArray();
    }
    public Artist[] getArtists() {
        return (Artist[])stages.toArray();
    }
    public Visitor[] getVisitors() { return (Visitor[])visitors.toArray();}

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

    public void save(String path) throws IOException {
        Gson gson = new Gson();
        gson.toJson(this, new FileWriter(path));
    }
    public void load(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        Festival f = gson.fromJson(new FileReader(path), Festival.class);

        this.setDay(f.getDay());
        this.setEnd(f.getEnd());
        this.setName(f.getName());
        this.setPrice(f.getPrice());
        this.setVisitorAmount(f.getVisitorAmount());
        this.setStart(f.getStart());

        this.addPerformances(f.getPerformances());
        this.addStages(f.getStages());
        this.addArtists(f.getArtists());
        this.addVisitors(f.getVisitors());
    }
}
