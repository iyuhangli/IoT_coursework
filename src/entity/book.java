package entity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class book {

    public String id;
    public String bookName;
    public int borrowOrNot;
    public String time;

    public book(String id, String bookName, int borrowOrNot, String time){
        this.id=id;
        this.bookName=bookName;
        this.borrowOrNot=borrowOrNot;
        this.time=time;
    }

    public book(){
    }

    public void saveBook(){
        Date dateOfNow=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        time=dateFormat.format(dateOfNow);
        String fname="./book.txt";
        try {
            FileWriter fw=new FileWriter(fname,true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(id+" "+bookName+" "+borrowOrNot+" "+time+"\n");
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("Error in I/O moudle");
            System.exit(1);
        }
    }
}
