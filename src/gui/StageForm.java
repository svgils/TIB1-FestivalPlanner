package gui;

import assets.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bobsk on 20-2-2017.
 */
public class StageForm extends JFrame{
    private JPanel panel1;
    private JTextField txtName;
    private JTextField txtLocationX;
    private JTextField txtLocationY;
    private JButton btnAdd;

    public StageForm(){
        super("Add Stage");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.festival.addStage(new Stage(txtName.getText(), txtLocationX + "," + txtLocationY, Main.festival.getStages().size() + 1));
                    Main.mp.schedulePainter.repaint();
                    StageForm.super.dispose();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Veld(en) niet juist ingevoerd");
                }
            }
        });

        setContentPane(panel1);
        setVisible(true);
        pack();
        setResizable(false);
    }
}
