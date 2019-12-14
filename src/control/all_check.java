package control;

import entity.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class all_check {

    static ArrayList<all_data> allData = new ArrayList<all_data>();
    static ArrayList<rfid_data> rd = new ArrayList<rfid_data>();
    static ArrayList<people_data> pd = new ArrayList<people_data>();


    public static ArrayList<all_data> get() throws FileNotFoundException {
        allData.clear();
        File fr= new File("./all_data.txt");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(fr);
        while(s.hasNext())
        {
            all_data a =new all_data();
            a.dataNo=s.nextInt();
            a.dataType=s.next();
            a.value=s.nextDouble();
            a.saveTime=s.next();
            allData.add(a);
        }
        return allData;
    }

    public static ArrayList<rfid_data> getRFID() throws FileNotFoundException {
        rd.clear();
        File fr= new File("./rfid_data.txt");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(fr);
        while(s.hasNext())
        {
            rfid_data b =new rfid_data();
            b.tid=s.next();
            b.inout=s.next();
            rd.add(b);
        }
        return rd;
    }

    public static ArrayList<people_data> getPeople() throws FileNotFoundException {
        pd.clear();
        File fr= new File("./people_data.txt");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(fr);
        while(s.hasNext())
        {
            people_data a =new people_data();
            a.id=s.nextInt();
            a.name=s.next();
            a.department=s.next();
            a.authority=s.next();
            pd.add(a);
        }
        return pd;
    }

}
