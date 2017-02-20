package gui;

import assets.Artist;
import assets.Festival;
import assets.Performance;
import assets.Stage;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Stijn on 6-2-2017.
 */
public class Main {

    public static AgendaForm mp;
    public static Festival festival;

    public static void main(String[] args){
        createTestFest();
        mp = new AgendaForm();
    }

    public JFrame getMainPanel(){
        return mp;
    }

    public static void createTestFest(){

        festival = new Festival(10000, 10, LocalDate.parse("2017-05-23", DateTimeFormatter.ISO_DATE), LocalTime.parse("06:00"), LocalTime.parse("23:00"), "testfest");

        festival.addStage(new Stage("Stage1", ""));
        festival.addStage(new Stage("Stage2", ""));
        festival.addStage(new Stage("Stage3", ""));
        festival.addStage(new Stage("Stage4", ""));
        festival.addStage(new Stage("Stage5", ""));
        festival.addStage(new Stage("Stage6", ""));

        festival.addPerformance(new Performance(new Artist("testartist1", "gen1", 2), festival.getStage(1), LocalTime.parse("05:31"), LocalTime.parse("07:15")));
        festival.addPerformance(new Performance(new Artist("testartist2", "gen1", 4), festival.getStage(2 ), LocalTime.parse("22:00"), LocalTime.parse("23:12")));
        festival.addPerformance(new Performance(new Artist("testartist3", "gen2", 10000), festival.getStage(3), LocalTime.parse("22:15"), LocalTime.parse("23:55")));
        festival.addPerformance(new Performance(new Artist("testartist4", "gen3", 1000), festival.getStage(4), LocalTime.parse("22:45"), LocalTime.parse("23:55")));
        festival.addPerformance(new Performance(new Artist("testartist5", "gen2", 300), festival.getStage(5), LocalTime.parse("22:27"), LocalTime.parse("23:55")));
    }
}
