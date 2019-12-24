package boundary;

import javax.naming.ldap.ControlFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.*;
import control.*;

public class bookControl extends JFrame {

    Font noteFont=new Font("Times New Roman", Font.PLAIN, 30);
    static ArrayList<book> bookData = new ArrayList<book>();
    private JLabel info=new JLabel("");
    private JLabel confirming=new JLabel();
    private JButton back=new JButton();
    private JButton yes=new JButton("Yes");
    String id="";
    int status=0;
    int number=0;
    String name="";
    int a=0;

    public bookControl(String id) throws FileNotFoundException {
        bookData=fileControl.getbookdata();
        this.setSize(1000, 625);
        this.setTitle("Welcome to the library management system!");
        this.setDefaultCloseOperation(mainPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.id=id;
        ImageIcon background=new ImageIcon("img/library.png");
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);

        info.setBounds(356,100,428,40);
        confirming.setBounds(286,260,428,40);
        info.setFont(noteFont);
        confirming.setFont(noteFont);

        yes.setFont(noteFont);
        yes.setBounds(400,380,200,60);
        yes.setContentAreaFilled(false);
        yes.setFocusPainted(false);

        ImageIcon img=new ImageIcon("img/back.png");
        back=new JButton(img);
        back.setBounds(10,10,60,60);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        this.add(back);

        for(int i=0;i<bookData.size();i++){
            if((bookData.get(i).id.equals(id))){
                status=bookData.get(i).borrowOrNot;
                name=bookData.get(i).bookName;
                a=1;
                number=i;
            }
        }

        if(a!=1){
            info.setText("Book not found, please try again!");
        }
        else{
            info.setText(name);
            if(status==1){
                confirming.setText("Do you want to borrow this book?");
            }
            else{
                confirming.setText("Do you want to return this book?");
            }
            this.add(confirming);
            this.add(yes);

        }
        this.add(info);

        action();

    }

    private void action() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new bookTag();
                dispose();
            }
        });

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date dateOfNow=new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String time=dateFormat.format(dateOfNow);
                if(status==1) {
                    bookData.get(number).borrowOrNot=0;
                    JOptionPane.showMessageDialog(null, "Borrow successfully","Successful", JOptionPane.INFORMATION_MESSAGE);
                    new bookTag();
                    dispose();
                }
                else{
                    bookData.get(number).borrowOrNot=1;
                    JOptionPane.showMessageDialog(null, "Return successfully","Successful", JOptionPane.INFORMATION_MESSAGE);
                    new bookTag();
                    dispose();
                }
                bookData.get(number).time=time;
                fileControl.savebookdata(bookData);
            }
        });
    }


}
