package assets;

import java.util.*;
/**
 * Created by Bob, Bram en Michael on 6-2-2017.
 */
<<<<<<< HEAD

public class Schedule extends Festival {
    private ArrayList<Artist> artist;
    private java.util.ArrayList<Visitor> visitor;
    private java.util.ArrayList<Stage> stage;
=======
public class Schedule {
    private Artist artist;
    private Stage stage;
    private double begin;
    private double length;

    public Schedule() {
        artist = new Artist();
        stage = new Stage();
        begin = 2.0;
        length = 3.0;

    }

    public Schedule(Artist artist, Stage stage, int begin, int length) {
        this.artist = artist;
        this.stage = stage;
        this.begin = begin;
        this.length = length;

    }

    public double getBegin(){
        return begin;
    }

    public Artist getAtrist(){
        return artist;
    }

    public Stage getStage(){
        return stage;
    }

    public double getLength(){
        return length;
    }

    public double getEnd(){
        double end;
        end = begin + length;
        if(end <= 24){
            end -= 24;
            return end;
        }
        else{
            return end;
        }

    }
>>>>>>> 53382ad0d38a457fffa288e5ac03100ea0473b5a
}
