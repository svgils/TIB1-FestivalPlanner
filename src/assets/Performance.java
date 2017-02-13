package assets;

import java.time.LocalTime;

/**
 * Created by Bob, Bram en Michaelleelal on 6-2-2017.
 */

public class Performance {
    private Artist artist;
    private Stage stage;
    private LocalTime begin;
    private LocalTime end;

    public Performance(Artist artist, Stage stage, LocalTime begin, LocalTime end) {
        this.artist = artist;
        this.stage = stage;
        this.begin = begin;
        this.end = end;
    }

    public LocalTime getBegin(){
        return begin;
    }

    public Artist getAtrist(){
        return artist;
    }

    public Stage getStage(){
        return stage;
    }

    public LocalTime getEnd(){
        /*double end;
        end = begin + end;
            if(end >= 24){
                end -= 24;
                return end;
            }
            else{
                return end;
            }
            */
        return end;
    }
}
