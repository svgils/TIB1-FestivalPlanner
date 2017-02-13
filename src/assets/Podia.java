package assets;

import java.util.ArrayList;

/**
 * Created by Bobsk on 6-2-2017.
 */
public class Podia {
    private String name;
    private String location;
    private ArrayList<Act> acts;

    public Podia(String name, String location)
    {
        this.name = name;
        this.location = location;
        this.acts = new ArrayList<>();
    }

    public void addAct(Act act)
    {
        this.acts.add(act);
    }

    public void removeAct(Act act)
    {

    }
}
