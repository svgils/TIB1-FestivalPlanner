package gui;

import assets.Festival;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AgendaForm extends JFrame {
    private JPanel mainPanel;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    public SchedulePainter schedulePainter;

    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuEdit;
    private JMenuItem saveItem;
    private JMenuItem loadItem;
    private JMenuItem newFestival;
    private JMenuItem addStage;
    private JMenuItem addPerformance;

    final static int H_SPACING = 70;
    final static int V_SPACING = 50;

    static int scrollBar1Pos;
    static int scrollBar2Pos;

    public AgendaForm() {
        super("Agenda");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setPreferredSize(new Dimension(screensize.width, 70 + V_SPACING * Main.festival.getStages().size() + V_SPACING));


        //Scrollbar stuff
        scrollBar2.addAdjustmentListener(e -> {
            schedulePainter.createVertLines(49, ((double)e.getValue()/90));
            schedulePainter.repaint();
        });
        scrollBar1.setVisibleAmount(100);
        scrollBar1.addAdjustmentListener(e -> scrollBar1Pos = e.getValue());


        //Menu stuff
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");

        newFestival = new JMenuItem("New Festival");
        newFestival.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewFestivalForm();
            }
        });

        // Functionality for saving festivals
        saveItem = new JMenuItem("Save File");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String savePath = "";
                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setDialogTitle("Choose save location");
                fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooser.setSelectedFile(new File("festival.json"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("Json file", "json"));
                if(fileChooser.showSaveDialog(saveItem) == JFileChooser.APPROVE_OPTION){
                    savePath = fileChooser.getSelectedFile().toString();
                    if (!savePath .endsWith(".json"))
                        savePath += ".json";
                }

                try {
                    Festival.save(savePath, Main.festival);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //functionality for loading festivals
        loadItem = new JMenuItem("Load File");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fileToOpen = null;
                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setDialogTitle("Choose file to open");
                fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Json file", "json"));
                if(fileChooser.showOpenDialog(loadItem) == JFileChooser.APPROVE_OPTION){
                    fileToOpen = fileChooser.getSelectedFile();
                }

                try {
                    Main.festival = Festival.load(fileToOpen.toString());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        addStage = new JMenuItem("Add Stage");
        addStage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StageForm();
            }
        });

        addPerformance = new JMenuItem("Add Performance");
        addPerformance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerformanceForm();
            }
        });

        menuFile.add(saveItem);
        menuFile.add(loadItem);
        menuFile.add(newFestival);

        menuEdit.add(addStage);
        menuEdit.add(addPerformance);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);

        setJMenuBar(menuBar);
        setContentPane(mainPanel);
        setVisible(true);
        pack();
    }
}