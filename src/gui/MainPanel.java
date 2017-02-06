package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stijn on 6-2-2017.
 */
public class MainPanel extends JFrame {

    public MainPanel(){
        super("Festival Planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(new Canvas());

        setPreferredSize(new Dimension(1280,720));

        setVisible(true);
        pack();
    }

    private class Canvas extends JPanel{

        private Canvas(){
            super();
        }

        @Override
        protected void paintComponent(Graphics g){

        }
    }
}
