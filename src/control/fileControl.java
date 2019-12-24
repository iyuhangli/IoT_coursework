package control;

import entity.book;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class fileControl {

    static ArrayList<book> bookData=new ArrayList<book>();

    public fileControl(){
    }

    public static ArrayList<book> getbookdata() throws FileNotFoundException {
        bookData.clear();
        File fr= new File("./bookData.txt");
        @SuppressWarnings("resource")
        Scanner s = new Scanner(fr);
        while(s.hasNext())
        {
            book a =new book();
            a.id=s.next();
            a.bookName=s.next();
            a.borrowOrNot=s.nextInt();
            a.time=s.next();
            bookData.add(a);
        }
        return bookData;
    }

    public static void savebookdata(ArrayList<book> bookData) {
        try {
            FileWriter fileWriter= new FileWriter("./bookData.txt");
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            for(int j=0;j<bookData.size();j++) {
                bufferedWriter.write(bookData.get(j).id+" "+bookData.get(j).bookName +" "
                        +bookData.get(j).borrowOrNot+" "+bookData.get(j).time+"\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error at I/O module");
            System.exit(1);
        }
    }

}
