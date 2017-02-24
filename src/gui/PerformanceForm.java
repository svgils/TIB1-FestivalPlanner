package gui;

import assets.Artist;
import assets.Performance;
import assets.Stage;
import org.omg.CORBA.PERSIST_STORE;
import sun.awt.WindowClosingListener;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalTime;

/**
 * Created by Bobsk on 20-2-2017.
 */
public class PerformanceForm extends JFrame {

    private JButton btnAdd;
    private JTextField txtArtist;
    private JComboBox cboxStage;
    private JPanel mainPanel;
    private PlaceholderTextField txtStartTime;
    private PlaceholderTextField txtEndTime;

    public PerformanceForm(){
        super("Add Performance");
        setContentPane(mainPanel);
        fillStageBox();

        txtStartTime.setPlaceholder("HH:MM");
        txtEndTime.setPlaceholder("HH:MM");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.festival.addPerformance(new Performance(new Artist(txtArtist.getText(), "", 1), (Stage)cboxStage.getSelectedItem(), LocalTime.parse(txtStartTime.getText()), LocalTime.parse(txtEndTime.getText())));
                    Main.mp.schedulePainter.repaint();
                    PerformanceForm.super.dispose();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Veld(en) niet juist ingevoerd");
                }
            }
        });

        setVisible(true);
        pack();
        setResizable(false);
    }

    private void fillStageBox(){
        for(Stage s : Main.festival.getStages()){
            cboxStage.addItem(s);
        }
    }
}