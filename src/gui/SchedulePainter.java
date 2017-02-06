package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stijn on 6-2-2017.
 */
public class SchedulePainter extends JPanel {

    public SchedulePainter(){
        super(new FlowLayout());

        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        int posX = 0;
        int posY = 0;
        int width = getWidth();
        int height = getHeight();

        System.out.println(height / 5);

        int hSpacing = (int)Math.floor(width / 24);
        int vSpacing = (int)Math.floor(height / 5);



        hSpacing = getWidth() / 24;

        for(int i = 1; i < 5; i++){
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(posX, posY + (vSpacing * i), posX + width, posY + (vSpacing * i));
        }

        for(int i = 1; i < 24; i++){
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(posX + (hSpacing * i), posY, posX + (hSpacing * i), posY + height);
        }
    }
}
