package gui;

import assets.Stage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stijn on 6-2-2017.
 */
public class SchedulePainter extends JPanel {

    public SchedulePainter(){
        super();
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

        //System.out.println(height / 5);

        int hSpacing = (int)Math.floor(width / 6);
//        if(AgendaForm.stages.size() > 0){
//            vSpacing = (int)Math.floor(height / AgendaForm.stages.size());}
//        else vSpacing = (int)Math.floor(height);

        for(int i = 1; i < 5; i++){
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(posX, posY + (AgendaForm.V_SPACING * i), posX + width, posY + (AgendaForm.V_SPACING * i));
        }

        for(int i = 1; i < 24; i++){
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(posX + (AgendaForm.H_SPACING * i), posY, posX + (AgendaForm.H_SPACING * i), posY + height);
        }
    }
}
