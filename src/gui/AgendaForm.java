package gui;

import assets.Artist;
import assets.Festival;
import assets.Performance;
import assets.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class AgendaForm extends JFrame {
    private JPanel mainPanel;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private SchedulePainter schedulePainter;
    private JMenuBar menuBar;
    private JMenu menuFile;

    final static int H_SPACING = 70;
    final static int V_SPACING = 50;

    int scrollBar1Pos;
    int scrollBar2Pos;

    private Festival festival;

    static ArrayList<Stage> stages = new ArrayList<>();

    public AgendaForm() {
        super("Agenda");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        festival = new Festival(1, 1, "18-94-9385", 1, 1, "STAMPPERTJES");

        mainPanel.setPreferredSize(new Dimension(1000, 70 + V_SPACING * stages.size() + V_SPACING));

        //setResizable(false);

        //Scrollbar stuff
        scrollBar2.addAdjustmentListener(e -> scrollBar2Pos = e.getValue());
        scrollBar1.addAdjustmentListener(e -> scrollBar1Pos = e.getValue());

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        setJMenuBar(menuBar);
        setContentPane(mainPanel);
        setVisible(true);
        pack();
    }

    public void createDummyData() {

        festival = new Festival(1, 1, "18-94-9385", 1, 1, "STAMPPERTJES");

        festival.addStage(new Stage("Stage1", ""));
        festival.addStage(new Stage("Stage2", ""));
        festival.addStage(new Stage("Stage3", ""));
        festival.addStage(new Stage("Stage4", ""));
        festival.addStage(new Stage("Stage4", ""));
        festival.addStage(new Stage("Stage123", ""));

        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());
        festival.addArtist(new Artist());

        for(int i = 0; i < 10; i++) {
            festival.addPerformance(
                    new Performance(
                            festival.getArtists()[(int)(Math.random() * festival.getArtists().length)],
                            festival.getStages()[(int)(Math.random() * festival.getStages().length)],
                            LocalTime.now(),
                            LocalTime.now()));
        }
    }

    public  Festival getFestival() {
        return festival;
    }
}