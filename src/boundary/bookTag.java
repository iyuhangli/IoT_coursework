package boundary;

import com.uhf.demo.UhfDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class bookTag extends JFrame {

    Font noteFont=new Font("Times New Roman", Font.PLAIN, 30);
    JLabel note=new JLabel("Please place the book, and click OK");
    JButton ok=new JButton("OK");
    JButton back;
    String id="";


    public bookTag(){
        this.setSize(1000, 625);
        this.setTitle("Welcome to the library management system!");
        this.setDefaultCloseOperation(mainPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        ImageIcon background=new ImageIcon("img/library.png");
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);

        note.setBounds(286,160,428,40);
        note.setFont(noteFont);
        this.add(note);
        ok.setFont(noteFont);
        ok.setBounds(400,350,200,60);
        ok.setContentAreaFilled(false);
        ok.setFocusPainted(false);
        ok.setContentAreaFilled(false);
        ok.setFocusPainted(false);
        this.add(ok);
        ImageIcon img=new ImageIcon("img/back.png");
        back=new JButton(img);
        back.setBounds(10,10,60,60);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        this.add(back);
        action();


    }

    private void action() {
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id= UhfDemo.getID();
                try {
                    //new bookControl(id);
                    new bookControl("12345678");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainPage();
                dispose();
            }
        });
    }
}
