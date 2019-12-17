package entity;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import control.all_check;

public class rfid_data {
    public String tid;
    public String inout;
    public String status;
    public String savetime;

    public static ArrayList<rfid_data> rd = new ArrayList<rfid_data>();

    public rfid_data(String tid, String inout){
        this.tid=tid;
        this.inout=inout;
        this.status="true";
    }

    public rfid_data(String tid, String inout, String status){
        this.tid=tid;
        this.inout=inout;
        this.status=status;
    }

    public  rfid_data(){
    }

    public void saveData() {
        Date dateOfNow=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        savetime=dateFormat.format(dateOfNow);
        String fname="./rfid_data.txt";
        try {
            FileWriter fw=new FileWriter(fname,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(tid+" "+inout+" "+status+" "+savetime+"\n");
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Error in I/O moudle");
            System.exit(1);
        }
    }

}
