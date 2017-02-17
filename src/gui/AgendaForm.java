package gui;

import assets.Festival;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AgendaForm extends JFrame {
    private JPanel mainPanel;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private SchedulePainter schedulePainter;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem saveItem;
    private JMenuItem loadItem;

    final static int H_SPACING = 70;
    final static int V_SPACING = 50;

    static int scrollBar1Pos;
    static int scrollBar2Pos;

    public AgendaForm() {
        super("Agenda");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setPreferredSize(new Dimension(screensize.width, 70 + V_SPACING * Main.festival.getStages().size() + V_SPACING));

        //setResizable(false);

        //Scrollbar stuff
        scrollBar2.addAdjustmentListener(e -> {
            schedulePainter.createVertLines(49, ((double)e.getValue()/90));
            schedulePainter.repaint();
            //System.out.println(scrollBar2Pos);
            System.out.println(e.getValue());
        });
        scrollBar1.addAdjustmentListener(e -> scrollBar1Pos = e.getValue());

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");

        saveItem = new JMenuItem("save file");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.festival.save("./file.json");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        loadItem = new JMenuItem("load file");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.festival.load("./file.json");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menuFile.add(saveItem);
        menuFile.add(loadItem);

        menuBar.add(menuFile);

        setJMenuBar(menuBar);
        setContentPane(mainPanel);
        setVisible(true);
        pack();
    }
}