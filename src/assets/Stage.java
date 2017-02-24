package assets;

/**
 * Created by $Bob, Bram en Michel on $6-2-2017.
 */

public class Stage  {
    private String name;
    private String location;
    private int index;

    public Stage(String name, String location, int index){
        this.name = name;
        this.location = location;
        this.index = index;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public String toString(){
        return name;
    }

    public int getIndex() {
        return this.index;
    }
}
