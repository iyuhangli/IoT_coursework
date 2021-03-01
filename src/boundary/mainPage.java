package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class mainPage extends JFrame {

    JButton book=new JButton("Books management");
    JButton info=new JButton("Show information");
    JButton light=new JButton("Light control");
    JButton fire=new JButton("Library monitor");
    Font jbf=new Font("Times New Roman", Font.PLAIN, 18);

    public mainPage(){
        this.setSize(1000, 625);
        this.setTitle("Welcome to the library management system!");
        this.setDefaultCloseOperation(mainPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        ImageIcon background=new ImageIcon("img/library.png");
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);
        book.setBounds(150,180,260,40);
        info.setBounds(570,180,260,40);
        light.setBounds(150,400,260,40);
        fire.setBounds(570,400,260,40);
        book.setFont(jbf);
        info.setFont(jbf);
        light.setFont(jbf);
        fire.setFont(jbf);
        book.setContentAreaFilled(false);
        book.setFocusPainted(false);
        info.setContentAreaFilled(false);
        info.setFocusPainted(false);
        light.setContentAreaFilled(false);
        light.setFocusPainted(false);
        fire.setContentAreaFilled(false);
        fire.setFocusPainted(false);
        this.add(book);
        this.add(info);
        this.add(light);
        this.add(fire);
        action();



    }

    private void action() {
        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new bookTag();
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new showInfo();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        light.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new light();
                dispose();
            }
        });

        fire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new fire();
                dispose();
            }
        });
    }

    public static void main (String[]args){
        new mainPage();
    }

}
