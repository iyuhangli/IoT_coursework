package boundary;

import entity.book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import control.*;

public class showInfo extends JFrame {

    JButton back=new JButton();
    private DefaultTableModel table;
    private JTable tablee=new JTable();

    ArrayList<book> temp=new ArrayList<book>();
    public showInfo() throws FileNotFoundException {
        this.setSize(1000, 625);
        this.setTitle("Infomation");
        this.setDefaultCloseOperation(mainPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        ImageIcon background=new ImageIcon("img/library.png");
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);
        ImageIcon img=new ImageIcon("img/back.png");
        back=new JButton(img);
        back.setBounds(10,10,60,60);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        this.add(back);

        temp=fileControl.getbookdata();

        table = (DefaultTableModel) tablee.getModel();

        Object[] object={"Book name","Rest of book","Operation time"};
        table.addColumn("Book name");
        table.addColumn("Rest of book");
        table.addColumn("Operation time");
        table.addRow(object);
        for(int i=0;i<temp.size();i++) {
            Object[] tables={temp.get(i).bookName,temp.get(i).borrowOrNot,temp.get(i).time};
            table.addRow(tables);
        }
        tablee.setBounds(250,120,480,300);
        Font a=new Font("Times New Roman", 0, 18);
        tablee.setFont(a);
        this.add(tablee);

        action();

    }

    private void action() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainPage();
                dispose();
            }
        });
    }
}
