package assets;

/**
 * Created by Bob, Bram en Michael on 6-2-2017.
 */

public class Performance {
    private Artist artist;
    private Stage stage;
    private double begin;
    private double length;

    public Performance() {
        artist = new Artist();
        stage = new Stage();
        begin = 2.0;
        length = 3.0;
    }

    public Performance(Artist artist, Stage stage, int begin, int length) {
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
            if(end >= 24){
                end -= 24;
                return end;
            }
            else{
                return end;
            }
    }
}
