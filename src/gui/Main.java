package gui;

import assets.Artist;
import assets.Festival;
import assets.Performance;
import assets.Stage;
import gui.AgendaForm;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Stijn on 6-2-2017.
 */
public class Main {

    public static AgendaForm mp;
    public static Festival festival;
    public static LocalDateTime currentTime = LocalDateTime.MIN;

    public static void main(String[] args) throws IOException {
        createTestFest();
        //try {
            mp = new AgendaForm();
//        }catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Velden niet juist ingevoerd");
//        }
    }

    public JFrame getMainPanel(){
        return mp;
    }

    public static void createTestFest(){

        festival = new Festival(10000, 10, LocalDate.parse("2017-05-23", DateTimeFormatter.ISO_DATE), LocalTime.parse("06:00"), LocalTime.parse("23:00"), "testfest");
        festival.addStage(new Stage("Stage1", "", festival.getStages().size()));
        festival.addStage(new Stage("Stage2", "", festival.getStages().size()));
        festival.addStage(new Stage("Stage3", "", festival.getStages().size()));

        festival.addPerformance(new Performance(new Artist("example1", "gen1", 2), festival.getStage(0), LocalTime.parse("05:31"), LocalTime.parse("07:15")));
        festival.addPerformance(new Performance(new Artist("example2", "gen1", 4), festival.getStage(1 ), LocalTime.parse("09:17"), LocalTime.parse("12:47")));
        festival.addPerformance(new Performance(new Artist("example3", "gen2", 10000), festival.getStage(2), LocalTime.parse("22:15"), LocalTime.parse("23:55")));
        festival.addPerformance(new Performance(new Artist("example4", "gen3", 1000), festival.getStage(0), LocalTime.parse("00:11"), LocalTime.parse("02:34")));
        festival.addPerformance(new Performance(new Artist("example5", "gen3", 1000), festival.getStage(2), LocalTime.parse("03:23"), LocalTime.parse("05:16")));
        festival.addPerformance(new Performance(new Artist("example6", "gen3", 1000), festival.getStage(1), LocalTime.parse("13:06"), LocalTime.parse("15:00")));
        festival.addPerformance(new Performance(new Artist("example7", "gen3", 1000), festival.getStage(0), LocalTime.parse("17:12"), LocalTime.parse("21:27")));
    }
}
