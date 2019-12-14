package entity;

import control.all_check;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class all_data {


    public int dataNo;
    public String dataType;
    public double value;
    public String saveTime;
    public static ArrayList<all_data> allData = new ArrayList<all_data>();
    public static ArrayList<Double> data_value = new ArrayList<Double>();
    public ArrayList<String> save_time = new ArrayList<String>();



    public all_data(String type, double value){
        this.dataType=type;
        this.value=value;
        this.dataNo=allData.get((allData.size()-1)).dataNo+1;
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
            bw.write(dataType+" "+value+" "+ saveTime+"\n");
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Error in I/O moudle");
            System.exit(1);
        }
    }

    public static ArrayList<all_data> getAllValue(String type) throws FileNotFoundException {
        allData= all_check.get();
        for(int i=0;i<allData.size();i++){
            if(allData.get(i).dataType.equals(type)){
                allData.add(allData.get(i));
            }
        }
        return allData;
    }

    public static ArrayList<Double> getDataValue(String type) throws FileNotFoundException {
        allData= all_check.get();
        for(int i=0;i<allData.size();i++){
            if(allData.get(i).dataType.equals(type)){
                data_value.add(allData.get(i).value);
            }
        }
        return data_value;
    }

    public ArrayList<String> getSaveTime(String type) throws FileNotFoundException {
        for(int i=0;i<allData.size();i++){
            if(allData.get(i).dataType.equals(type)){
                save_time.add(allData.get(i).saveTime);
            }
        }
        return save_time;
    }
}
