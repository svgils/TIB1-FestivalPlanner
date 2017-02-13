package assets;

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
    private java.util.ArrayList<Artist> artist;
    private java.util.ArrayList<Visitor> visitor;
<<<<<<< HEAD
    private java.util.ArrayList<Stage> stage;

    public Festival(int visitorAmount, int price, String day, int start, int end, String name) {
        this.visitorAmount = visitorAmount;
        this.price = price;
        this.day = day;
        this.start = start;
        this.end = end;
        this.name = name;
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
=======





>>>>>>> 53382ad0d38a457fffa288e5ac03100ea0473b5a
}
