package assets;

import java.util.*;

/**
 * Created by Bob, Bram en Michel on 6-2-2017.
 */

public class Festival {
    private int visitorAmount;
    private int price;  // prijs in Euro's
    private int end;    // In uur
    private int start;  // In Uur
    private String name;
    private String day;
    private ArrayList<Artist> artists;
    private ArrayList<Visitor> visitors;
    private ArrayList<Stage> stages;

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


    }

    public void changeVisitorAmount(int visitorAmount){
        this.visitorAmount = visitorAmount;
    }

    public void changePrice(int price){
        this.price = price;
    }

    public void changeEnd(int end){
        this.end = end;
    }

    public void changeStart(int start){
        this.start = start;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeDay(String day){
        this.day = day;
    }

    public void addArtist(Artist artist){
        artists.add (artist);
    }

    public void addStage(Stage stage{
        stages.add (stage);
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
