package entity;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import control.all_check;

public class people_data {
    public int id;
    public String rfidID;
    public String name;
    public String department;
    public String authority;
    public String aval;

    public static ArrayList<people_data> pd = new ArrayList<people_data>();

    public people_data(String name, String ID, String department, String authority) throws FileNotFoundException {
        pd=all_check.getPeople();
        this.id=pd.get(pd.size()-1).id+1;
        this.rfidID=ID;
        this.name=name;
        this.department=department;
        this.authority=authority;
        this.aval="true";
    }

    public  people_data(){
    }

    public void saveData() {
        String fname="./people_data.txt";
        try {
            FileWriter fw=new FileWriter(fname,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(id+" "+rfidID+" "+name+" "+department+" "+authority+" "+aval+"\n");
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Error in I/O moudle");
            System.exit(1);
        }
    }

}
