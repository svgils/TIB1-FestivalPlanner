package gui;

import assets.Artist;
import assets.Performance;
import assets.Stage;
import org.omg.CORBA.PERSIST_STORE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.time.LocalTime;

/**
 * Created by Bobsk on 20-2-2017.
 */
public class PerformanceForm extends JFrame {

    private JButton btnAdd;
    private JTextField txtArtist;
    private JComboBox cboxStage;
    private JTextField txtStart;
    private JTextField txtEnd;
    private JPanel mainPanel;

    public PerformanceForm(){
        super("Add Performance");
        setContentPane(mainPanel);
        fillStageBox();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.festival.addPerformance(new Performance(new Artist(txtArtist.getText(), "", 1), (Stage)cboxStage.getSelectedItem(), LocalTime.parse(txtStart.getText()), LocalTime.parse(txtEnd.getText())));
            }
        });

        setVisible(true);
        pack();
    }

    private void fillStageBox(){
        for(Stage s : Main.festival.getStages()){
            cboxStage.addItem(s);
        }
    }
}