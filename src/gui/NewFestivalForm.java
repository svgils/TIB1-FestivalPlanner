package gui;

import javax.swing.*;

/**
 * Created by Stijn on 22-2-2017.
 */
public class NewFestivalForm extends JFrame{
    private JTextField txtName;
    private JTextField txtCap;
    private JButton btnCreate;
    private JTextField txtPrice;
    private DatePicker datePicker;
    private PlaceholderTextField txtStartTime;
    private PlaceholderTextField txtEndTime;
    private JPanel mainPanel;

    public NewFestivalForm(){
        super("Create New Festival");
        txtStartTime.setPlaceholder("HH:MM");
        txtEndTime.setPlaceholder("HH:MM");
        setContentPane(mainPanel);
        setVisible(true);
        pack();
    }
}
