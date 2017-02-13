package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stijn on 6-2-2017.
 */
public class AgendaForm extends JFrame{
    private JPanel mainPanel;
    private DatePicker datePicker;

    public AgendaForm(){
        super("Agenda");

        setPreferredSize(new Dimension( 1280, 720));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(mainPanel);

        setVisible(true);
        pack();
    }
}
