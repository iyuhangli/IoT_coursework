package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import control.*;

public class beta_version extends JFrame{

    JButton backBV;
    JButton jb1,jb2;
    JLabel jl;

    public beta_version(){
        this.setResizable(false);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Beta funciton");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);

        ImageIcon imageIcon=new ImageIcon("image/beta_version_background.jpg");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
                this.setContentPane(lbBg);

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        Font font3 = new Font("Times New Roman", Font.PLAIN, 15);
        backBV=new JButton("Back");
        backBV.setBounds(620,380,100,25);
        backBV.setFont(font3);
        backBV.setContentAreaFilled(false);
        backBV.setFocusPainted(false);
        this.add(backBV);
        ImageIcon locateIcon=new ImageIcon("image/locate.png");
        ImageIcon typeIcon=new ImageIcon("image/type.png");

        jl=new JLabel("Being implemented···");
        jl.setFont(font);
        jl.setBounds(410,60,300,60);
        jb1=new JButton(locateIcon);
        jb1.setBounds(40,40,100,100);
        jb1.setFocusPainted(false);
        jb1.setContentAreaFilled(false);
        jb2=new JButton(typeIcon);
        jb2.setBounds(180,40,100,100);
        jb2.setFocusPainted(false);
        jb2.setContentAreaFilled(false);
        this.add(jb1);
        this.add(jb2);
        this.add(jl);
        actionListenerBV();
    }

    private void actionListenerBV(){
        backBV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new index();
                dispose();
            }
        });

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("useful_doc/Locate.pdf"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("useful_doc/Material.pdf"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
