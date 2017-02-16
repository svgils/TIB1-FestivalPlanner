package gui;

import assets.Festival;
import assets.Stage;
import gui.AgendaForm;

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
        festival = new Festival(10000, 10, LocalDate.parse("2017-05-23", DateTimeFormatter.ISO_DATE),
                LocalTime.parse("06:00"), LocalTime.parse("23:00"), "testfest");
        festival.addStage(new Stage("Stage1", ""));
        festival.addStage(new Stage("Stage2", ""));
        festival.addStage(new Stage("Stage3", ""));
        festival.addStage(new Stage("Stage4", ""));
        festival.addStage(new Stage("Stage4", ""));
        festival.addStage(new Stage("Stage123", ""));
    }
}
