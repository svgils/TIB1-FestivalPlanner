import gui.AgendaForm;

import javax.swing.*;

/**
 * Created by Stijn on 6-2-2017.
 */
public class Main {

    public static AgendaForm mp;

    public static void main(String[] args){
        mp = new AgendaForm();
    }

    public JFrame getMainPanel(){
        return mp;
    }
}
