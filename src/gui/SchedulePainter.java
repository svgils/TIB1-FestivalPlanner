package gui;

import assets.Festival;
import assets.Performance;
import assets.Stage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Stijn on 6-2-2017.
 */
public class SchedulePainter extends JPanel {
    int hSpaceingFirst = 0;
    ArrayList<VLine> vertLines = new ArrayList<>();

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
//        for(int i = 1; i < (this.getWidth() - hSpaceingFirst) / 50 + 0.5; i++){
//            if(i == 1) {
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(posX + (hSpaceingFirst), posY, posX + (hSpaceingFirst), posY + height);
//                posX = hSpaceingFirst;
//            }
//            else {
//                g2d.setStroke(new BasicStroke(1));
//                g2d.drawLine(posX + (AgendaForm.H_SPACING * (i - 1)), posY, posX + (AgendaForm.H_SPACING * (i - 1)), posY + height);
//            }
//        }


        if(vertLines.isEmpty()){
            createVertLines(49, 0);
        System.out.println("filled");}

        g2d.setStroke(new BasicStroke(1));
        for(VLine l : vertLines){
            //l.linePosX = l.linePosX + 100 * (AgendaForm.scrollBar2Pos/90);
            //System.out.println(l.linePosX - ((AgendaForm.scrollBar2Pos/90) * (this.getWidth() - hSpaceingFirst)));
            //System.out.println(l.posX);
            if(l.linePosX > hSpaceingFirst)
                g2d.drawLine((int)l.linePosX,0,(int)l.linePosX,this.getHeight());
        }

        //drawing stage names
        posX = 15;
        posY = AgendaForm.V_SPACING;

        for(Stage s: Main.festival.stages){
            g2d.drawString(s.getName(), posX, posY + 7 + AgendaForm.V_SPACING/2);
            posY += AgendaForm.V_SPACING;
        }

        //drawing times
        g2d.setClip(new Rectangle(new Point(hSpaceingFirst, 0), new Dimension((this.getWidth() - hSpaceingFirst), AgendaForm.V_SPACING)));
        for(VLine l : vertLines){
             g2d.drawString(getTime(l.index).toString(), (int)l.linePosX + 15, 7 + AgendaForm.V_SPACING/2);
        }
    }

    public void createVertLines(int ammount, double offset){
        vertLines.clear();
        for(int i=0; i < ammount; i++){
            vertLines.add(new VLine((double)(hSpaceingFirst + i * AgendaForm.H_SPACING) - (offset * (double)this.getWidth()), i));
        }
    }

    private LocalTime getTime(int index){
        return LocalTime.parse("00:00").plusMinutes(30*index);
    }

    class VLine{
        double linePosX;
        int index;
        VLine(double nposX, int index){
            this.linePosX = nposX;
            this.index = index;
        }

        public void setPosX(double posX) {
            this.linePosX = posX;
        }
    }
}
