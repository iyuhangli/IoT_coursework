package boundary;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class select_type extends JFrame{

    private JLabel STInfo=new JLabel("Please select a type");
    private JButton emdata=new JButton("Environmental data");
    private JButton pedata=new JButton("People data");
    private JButton prdata=new JButton("Product data");
    private JButton backST=new JButton("Back");


    public select_type(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Information platform");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        Font font2 = new Font("Times New Roman", Font.PLAIN, 20);
        Font font3 = new Font("Times New Roman", Font.PLAIN, 15);

        ImageIcon imageIcon=new ImageIcon("image/index_background.png");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
        this.setContentPane(lbBg);

        STInfo.setFont(font);
        STInfo.setBounds(291,50,217,40);
        this.add(STInfo);

        emdata.setBounds(296,160,177,40);
        emdata.setFont(font3);
        emdata.setContentAreaFilled(false);
        emdata.setFocusPainted(false);
        this.add(emdata);
        pedata.setBounds(296,250,177,40);
        pedata.setFont(font3);
        pedata.setContentAreaFilled(false);
        emdata.setFocusPainted(false);
        this.add(pedata);
        prdata.setBounds(296,340,177,40);
        prdata.setFont(font3);
        prdata.setContentAreaFilled(false);
        emdata.setFocusPainted(false);
        this.add(prdata);
        backST.setBounds(620,380,100,25);
        backST.setFont(font3);
        backST.setContentAreaFilled(false);
        backST.setFocusPainted(false);
        this.add(backST);
        actionListenerST();
    }

    private void actionListenerST(){
        emdata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new information_platform(0);
                dispose();
            }
        });

        pedata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new information_platform(1);
                dispose();
            }
        });

        prdata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new information_platform(2);
                dispose();
            }
        });

        backST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new index();
                dispose();
            }
        });
    }


}
