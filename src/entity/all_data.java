package entity;

import control.all_check;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class all_data {


    public int dataNo;
    public String tem;
    public String hum;
    public String lig;
    public String saveTime;
    public static ArrayList<all_data> allData = new ArrayList<all_data>();


    public all_data(String tem, String hum,String lig) throws FileNotFoundException {
        allData=all_check.get();
        this.tem=tem;
        this.hum=hum;
        this.lig=lig;
        this.dataNo=allData.get((allData.size())-1).dataNo+1;
    }

    public all_data(){

    }

    public void saveData() {
        Date dateOfNow=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        saveTime=dateFormat.format(dateOfNow);

        String fname="./all_data.txt";
        try {
            FileWriter fw=new FileWriter(fname,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(tem+" "+hum+" "+lig+" "+ saveTime+"\n");
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Error in I/O moudle");
            System.exit(1);
        }
    }

}
