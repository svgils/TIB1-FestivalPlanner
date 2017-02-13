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
        g2d.setFont(new Font("Arial", 1, 15));


        int posX = 0;
        int posY = 0;
        int width = getWidth();
        int height = getHeight();

        int hSpaceingFirst = 0;
        for(Stage s : AgendaForm.stages){
            if(g.getFontMetrics().getStringBounds(s.getName(), null).getWidth() > hSpaceingFirst)
                hSpaceingFirst = (int)g.getFontMetrics().getStringBounds(s.getName(), null).getWidth() + 30;
            System.out.println(hSpaceingFirst);
        }

        //System.out.println(height / 5);

        int hSpacing = (int)Math.floor(width / 6);
//        if(AgendaForm.stages.size() > 0){
//            vSpacing = (int)Math.floor(height / AgendaForm.stages.size());}
//        else vSpacing = (int)Math.floor(height);

        //drawing horizontal lines
        for(int i = 1; i < AgendaForm.stages.size() + 1; i++){
            if(i == 1) g2d.setStroke(new BasicStroke(3));
            else g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(posX, posY + (AgendaForm.V_SPACING * i), posX + width, posY + (AgendaForm.V_SPACING * i));
        }

        //drawing vertical lines
        for(int i = 1; i < 24; i++){
            if(i == 1) {
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(posX + (hSpaceingFirst * i), posY, posX + (hSpaceingFirst * i), posY + height);
                posX = hSpaceingFirst;
            }
            else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(posX + (AgendaForm.H_SPACING * (i - 1)), posY, posX + (AgendaForm.H_SPACING * (i - 1)), posY + height);
            }
        }
        posX = 15;
        posY = AgendaForm.V_SPACING;

        for(Stage s: AgendaForm.stages){
            g2d.drawString(s.getName(), posX, posY + 7 + AgendaForm.V_SPACING/2);
            posY += AgendaForm.V_SPACING;
        }
    }
}
