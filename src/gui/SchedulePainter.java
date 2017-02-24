package gui;

import assets.Performance;
import assets.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Stijn on 6-2-2017.
 */
public class SchedulePainter extends JPanel {
    private int hSpacingFirst = 0;
    private ArrayList<VLine> vertLines = new ArrayList<>();
    private ArrayList<Block> blocks = new ArrayList<>();
    int infoBoxX;
    int infoBoxY;
    String performanceInfo;
    boolean drawInfoBox = false;

    public SchedulePainter(){
        super();
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (Block b : blocks) {
                    if ((e.getX() > b.block.getBounds().getX() && e.getX() < b.block.getBounds().getX() + b.block.getBounds().width) &&
                            (e.getY() > b.block.getBounds().getY() && e.getY() < b.block.getBounds().getY() + b.block.getBounds().height)) {
                        infoBoxX = e.getX();
                        infoBoxY = e.getY();
                        Performance p = b.performance;
                        performanceInfo = "Artist: " + p.getAtrist().getName()
                                + "\n" + "Stage: " +
                                p.getStage().getName() + "\n" +
                                "Start: " + p.getBegin().toString() + " End: " + p.getEnd().toString();
                        drawInfoBox = true;
                        repaint();
                        break;
                    } else {
                        drawInfoBox = false;
                        repaint();
                    }
                }
            }
        });

    }

    //private

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", 1, 15));

        int posX = 0;
        int posY = 0;
        int width = getWidth();
        int height = AgendaForm.V_SPACING * (Main.festival.getStages().size() + 1);

        hSpacingFirst = 0;
        for (Stage s : Main.festival.getStages()) {
            if (g.getFontMetrics().getStringBounds(s.getName(), null).getWidth() > hSpacingFirst - 30)
                hSpacingFirst = (int) g.getFontMetrics().getStringBounds(s.getName(), null).getWidth() + 30;
        }

        //drawing horizontal lines
        for (int i = 1; i < Main.festival.getStages().size() + 1; i++) {
            if (i == 1) g2d.setStroke(new BasicStroke(3));
            else g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(posX, posY + (AgendaForm.V_SPACING * i), posX + width, posY + (AgendaForm.V_SPACING * i));
        }

        //drawing vertical lines
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(posX + (hSpacingFirst), posY, posX + (hSpacingFirst), posY + this.getHeight());

        if (vertLines.isEmpty()) {
            createVertLines(49, 0);

            //System.out.println("filled");
        }

        g2d.setStroke(new BasicStroke(1));
        for (VLine l : vertLines) {
            if (l.linePosX > hSpacingFirst)
                g2d.drawLine((int) l.linePosX, 0, (int) l.linePosX, this.getHeight());
        }

        //drawing stage names
        posX = 15;
        posY = AgendaForm.V_SPACING;

        for (Stage s : Main.festival.getStages()) {
            g2d.drawString(s.getName(), posX, posY + 7 + AgendaForm.V_SPACING / 2);
            posY += AgendaForm.V_SPACING;
        }

        //drawing times
        g2d.setClip(new Rectangle(new Point(hSpacingFirst, 0), new Dimension((this.getWidth() - hSpacingFirst), AgendaForm.V_SPACING)));
        for (VLine l : vertLines) {
            g2d.drawString(getTime(l.index).toString(), (int) l.linePosX + 15, 7 + AgendaForm.V_SPACING / 2);
        }

        //Drawing performances
        g2d.setClip(null);

        blocks.clear();
//        for (Performance p : Main.festival.getPerformances()) {
//
//            g2d.setClip(hSpacingFirst + 2, 0, this.getWidth() - hSpacingFirst, this.getHeight());
//            int x = (int) getLineIndexFromTime(p.getBegin());
//            int length = (int) getLineIndexFromTime(p.getEnd()) - (int) getLineIndexFromTime(p.getBegin());
//            blocks.add(new Block(new RoundRectangle2D.Double(x,
//                                                            AgendaForm.V_SPACING * (Main.festival.getStages().indexOf(p.getStage())) + 5 + AgendaForm.V_SPACING,
//                                                            length, AgendaForm.V_SPACING - 10, 10, 10), p));
//            g2d.setColor(new Color(130, 175, 255));
//            System.out.println(Main.festival.getStages().size());
//            g2d.fillRoundRect(x, AgendaForm.V_SPACING * (Main.festival.getStages().indexOf(p.getStage())) + 5 + AgendaForm.V_SPACING, length, AgendaForm.V_SPACING - 10, 10, 10);
//            g2d.setColor(Color.black);
//            g2d.drawString(p.getAtrist().getName(), x + 5, AgendaForm.V_SPACING * (Main.festival.getStages().indexOf(p.getStage())) + 30 + AgendaForm.V_SPACING);
//            //g2d.fillRoundRect(x, AgendaForm.V_SPACING * (Main.festival.getStages().indexOf(p.getStage())) + 5, length, AgendaForm.V_SPACING - 10, 10, 10);
//
//        }
        for (Performance p : Main.festival.getPerformances()) {
            g2d.setClip(hSpacingFirst + 2, 0, this.getWidth() - hSpacingFirst, this.getHeight());
            int x = (int) getLineIndexFromTime(p.getBegin());
            int length = (int) getLineIndexFromTime(p.getEnd()) - (int) getLineIndexFromTime(p.getBegin());
            blocks.add(new Block(new RoundRectangle2D.Double(x,
                    AgendaForm.V_SPACING * p.getStage().getIndex() + 5 + AgendaForm.V_SPACING,
                    length, AgendaForm.V_SPACING - 10, 10, 10), p));
            g2d.setColor(new Color(130, 175, 255));
            g2d.fillRoundRect(x, AgendaForm.V_SPACING * (p.getStage().getIndex()) + 5 + AgendaForm.V_SPACING, length, AgendaForm.V_SPACING - 10, 10, 10);
            g2d.setColor(Color.black);
            g2d.drawString(p.getAtrist().getName(), x + 5, AgendaForm.V_SPACING * p.getStage().getIndex() + 30 + AgendaForm.V_SPACING);
            //g2d.fillRoundRect(x, AgendaForm.V_SPACING * (Main.festival.getStages().indexOf(p.getStage())) + 5, length, AgendaForm.V_SPACING - 10, 10, 10);
        }



            //Drawing popup info
            g2d.setClip(null);
            if (drawInfoBox) {
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                String lines[] = performanceInfo.split("\\r?\\n");
                int boxWidth = 0;
                for (String s : lines) {
                    if (g.getFontMetrics().stringWidth(s) > boxWidth) {
                        boxWidth = g.getFontMetrics().stringWidth(s);
                    }
                }
                g.setColor(new Color(198, 206, 211));
                g2d.fillRoundRect(infoBoxX, infoBoxY, boxWidth + 30, metrics.getHeight() * countLines(performanceInfo) + 30, 10, 10);
                g.setColor(Color.black);
                drawString(g, performanceInfo, infoBoxX + 15, infoBoxY + 10);
            }
        }


    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    void createVertLines(int amount, double offset){
        vertLines.clear();
        for(int i=0; i < amount; i++){
            vertLines.add(new VLine((double)(hSpacingFirst + i * AgendaForm.H_SPACING) - (offset * (AgendaForm.H_SPACING * amount - getWidth() + hSpacingFirst)), i));
        }
    }



    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }



    private LocalTime getTime(int index){
        return LocalTime.parse("00:00").plusMinutes(30*index);
    }

    private double getLineIndexFromTime(LocalTime t){
        //System.out.println(t.toString());
        LocalDateTime tm = LocalDate.parse("0001-01-01", DateTimeFormatter.ISO_DATE).atTime(t);
        int index = tm.getHour() * 2;

        double modMin;

        modMin = tm.getMinute();
        return vertLines.get(index).linePosX + ((double)AgendaForm.H_SPACING * (modMin / 30.0));
    }

    class Block{
        public Shape block;
        public Performance performance;

        public Block(Shape block, Performance performance){ this.block = block; this.performance = performance;}
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
