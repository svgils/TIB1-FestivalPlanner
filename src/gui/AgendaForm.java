package gui;

import assets.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
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

    static Set<Stage> stages = new HashSet<>();

    public AgendaForm() {
        super("Agenda");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        stages.add(new Stage("asdsgsadfadj"));
        stages.add(new Stage("vgdehnj"));
        stages.add(new Stage("vghnj"));
        stages.add(new Stage("vgsdnj"));
        stages.add(new Stage("vgdasd"));

        mainPanel.setPreferredSize(new Dimension(1000, 70 + V_SPACING * stages.size()));

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
}
