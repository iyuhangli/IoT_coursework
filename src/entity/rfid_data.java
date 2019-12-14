package entity;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import control.all_check;

public class rfid_data {
    public String tid;
    public String inout;

    public static ArrayList<rfid_data> rd = new ArrayList<rfid_data>();

    public rfid_data(String tid, String inout){
        this.tid=tid;
        this.inout=inout;
    }

    public  rfid_data(){
    }

    public void saveData() {
        String fname="./rfid_data.txt";
        try {
            FileWriter fw=new FileWriter(fname,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(tid+" "+inout+"\n");
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Error in I/O moudle");
            System.exit(1);
        }
    }

}
