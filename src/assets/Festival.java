package assets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by Bob, Bram en Michel on 6-2-2017.
 */

public class Festival {
    private int visitorAmount;
    private int price;  // prijs in Euro's
    private LocalTime end;    // In uur
    private LocalTime start;  // In Uur
    private String name;
    private LocalDate day;
    private ArrayList<Artist> artists;
    private ArrayList<Visitor> visitors;
    public ArrayList<Stage> stages;
    private ArrayList<Performance> performances;

    private Schedule schedule;

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

        schedule = new Schedule();
    }

    public void changeVisitorAmount(int visitorAmount) {
        this.visitorAmount = visitorAmount;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeEnd(LocalTime end) {
        this.end = end;
    }

    public void changeStart(LocalTime start) {
        this.start = start;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDay(LocalDate day) {
        this.day = day;
    }

    public void addPerformance(Performance performance) {
        performances.add(performance);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }

    public Schedule getSchedule() {
        return schedule;
    }

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
}
