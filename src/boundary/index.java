package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class index extends JFrame {

    private JLabel jla;
    private JButton jbu[];

    public index() {
        this.setSize(800, 500);
        this.setLayout(null);
        this.setResizable(false);

        ImageIcon imageIcon=new ImageIcon("image/index_background.png");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
        this.setContentPane(lbBg);


        jla =new JLabel("Welcome");
        jbu=new JButton[4];
        jbu[0]=new JButton("Environmental monitoring");
        jbu[1]=new JButton("Product management");
        jbu[2]=new JButton("People management");
        jbu[3]=new JButton("Information platform");
        jla.setFont(new java.awt.Font("Times new roman",1,40));
        jla.setBounds(310,70,160,50);
        jbu[0].setBounds(125,220,200,30);
        jbu[1].setBounds(125,320,200,30);
        jbu[2].setBounds(455,220,200,30);
        jbu[3].setBounds(455,320,200,30);
        jbu[0].setContentAreaFilled(false);
        jbu[0].setFocusPainted(false);
        jbu[1].setContentAreaFilled(false);
        jbu[1].setFocusPainted(false);
        jbu[2].setContentAreaFilled(false);
        jbu[2].setFocusPainted(false);
        jbu[3].setContentAreaFilled(false);
        jbu[3].setFocusPainted(false);

        this.add(jla);
        this.add(jbu[0]);
        this.add(jbu[1]);
        this.add(jbu[2]);
        this.add(jbu[3]);
        this.setVisible(true);
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setTitle("Intelligent warehouse management and monitoring integrated platform");

        jbu[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new environmental_monitoring();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        jbu[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new product_management();
                dispose();
            }
        });

        jbu[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new people_management();
                dispose();
            }
        });

        jbu[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
    }

    public static void main (String[] args){
        index index=new index();
    }
}
