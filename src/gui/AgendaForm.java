package gui;

import javax.swing.*;
import java.awt.*;

public class AgendaForm extends JFrame {
    private JPanel mainPanel;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private SchedulePainter schedulePainter;
    private JMenuBar menuBar;
    private JMenu menuFile;

    final static int H_SPACING = 70;
    final static int V_SPACING = 50;

    static int scrollBar1Pos;
    static int scrollBar2Pos;

    public AgendaForm() {
        super("Agenda");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setPreferredSize(new Dimension(screensize.width, 70 + V_SPACING * Main.festival.stages.size() + V_SPACING));

        //setResizable(false);

        //Scrollbar stuff
        scrollBar2.addAdjustmentListener(e -> schedulePainter.scrollPane(e.getValue()));
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