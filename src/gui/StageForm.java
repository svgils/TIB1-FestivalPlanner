package gui;

import javax.swing.*;

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
        setContentPane(panel1);
        setVisible(true);
        pack();
    }
}
