package control;

import entity.all_data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class all_check {

    static ArrayList<all_data> allData = new ArrayList<all_data>();

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

}
