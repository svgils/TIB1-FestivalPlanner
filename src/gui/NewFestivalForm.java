package gui;

import assets.Festival;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

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

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.festival = new Festival(Integer.parseInt(txtCap.getText()),
                                                 Integer.parseInt(txtPrice.getText()),
                                                 datePicker.getModel().getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                                 LocalTime.parse(txtStartTime.getText()),
                                                 LocalTime.parse(txtEndTime.getText()),
                                                 txtName.getText());
                    Main.mp.tabbedPane.getSelectedComponent().repaint();
                    NewFestivalForm.super.dispose();
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Veld(en) niet juist ingevoerd");
                }
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setResizable(false);
    }
}
