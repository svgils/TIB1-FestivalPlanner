package assets;

/**
 * Created by Bobsk on 6-2-2017.
 */
public class Visitor {
    private String name;
    private String sex;
    private String dateOfBirth;

    public Visitor(String name, String sex, String dateOfBirth){
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName(){
        return name;
    }

    public String getSex(){
        return sex;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

}
