package gui;

import assets.Festival;
import assets.Performance;
import assets.Stage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Created by Stijn on 6-2-2017.
 */
public class SchedulePainter extends JPanel {
    int hSpaceingFirst = 0;

    public SchedulePainter(){
        super();
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", 1, 15));

        int startpos = AgendaForm.scrollBar2Pos;
        LocalTime time = LocalTime.parse("00:00");

        int posX = 0;
        int posY = 0;
        int width = getWidth();
        int height = AgendaForm.V_SPACING * (Main.festival.stages.size() + 1);

        for(Stage s : Main.festival.stages){
            if(g.getFontMetrics().getStringBounds(s.getName(), null).getWidth() > hSpaceingFirst - 30)
                hSpaceingFirst = (int)g.getFontMetrics().getStringBounds(s.getName(), null).getWidth() + 30;
        }

        //drawing horizontal lines
        for(int i = 1; i < Main.festival.stages.size() + 1; i++){
            if(i == 1) g2d.setStroke(new BasicStroke(3));
            else g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(posX, posY + (AgendaForm.V_SPACING * i), posX + width, posY + (AgendaForm.V_SPACING * i));
        }

        //drawing vertical lines
        for(int i = 1; i < (this.getWidth() - hSpaceingFirst) / 50 + 0.5; i++){
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

        //drawing stage names
        posX = 15;
        posY = AgendaForm.V_SPACING;

        for(Stage s: Main.festival.stages){
            g2d.drawString(s.getName(), posX, posY + 7 + AgendaForm.V_SPACING/2);
            posY += AgendaForm.V_SPACING;
        }

        posX = hSpaceingFirst + 15;
        posY = 0;
        //drawing times
        for(int i = 0; i < (this.getWidth() - hSpaceingFirst) / 50 + 0.5; i++){
             g2d.drawString(time.toString(), posX, posY + 7 + AgendaForm.V_SPACING/2);
             time = getNextTime(time);
             posX += AgendaForm.H_SPACING;
        }
    }

    private LocalTime getNextTime(LocalTime t){
        return t.plusMinutes(30);
    }
}
