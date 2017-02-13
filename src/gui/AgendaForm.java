package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;

/**
 * Created by Stijn on 6-2-2017.
 */
public class AgendaForm extends JFrame{
    private JPanel mainPanel;

    public AgendaForm(){
        super("Agenda");

        setPreferredSize(new Dimension( 1000, 350));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(mainPanel);

        setVisible(true);
        pack();
    }
}
