package Model;

import java.util.ArrayList;
import java.util.List;

public class DataDoctor {
    private List <String> crms;
    private List <String> names;

    public DataDoctor(){
        this.crms = new ArrayList<>();
        this.names = new ArrayList<>();
    }

    public void setCrms(String crm){
        this.crms.add(crm);
    }

    public void setNames(String name){
        this.names.add(name);
    }

    public List<String> getCrms(){
        return this.crms;
    }

    public List<String> getNames(){
        return this.names;
    }
}
