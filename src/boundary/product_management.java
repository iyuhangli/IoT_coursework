package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.uhf.linkage.Linkage;
import entity.rfid_data;
import control.*;

public class product_management extends JFrame{
    private JLabel PMInfo=new JLabel("Product management");
    private JTextField RFIDResult=new JTextField("");
    private JButton start=new JButton("Inventory");
    private JButton putIn=new JButton("Put in");
    private JButton putOut=new JButton("Put out");
    private JButton backPM=new JButton("Back");


    public product_management(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);

        PMInfo.setFont(font);
        PMInfo.setBounds(295,50,209,40);
        this.add(PMInfo);

        RFIDResult.setBounds(295,200,209,40);
        RFIDResult.setEditable(false);
        this.add(RFIDResult);

        start.setBounds(80,400,100,25);
        putIn.setBounds(260,400,100,25);
        putOut.setBounds(440,400,100,25);
        backPM.setBounds(620,400,100,25);
        putIn.setEnabled(false);
        putOut.setEnabled(false);
        start.setFocusable(true);
        putIn.setFocusable(true);
        putOut.setFocusable(true);
        backPM.setFocusable(true);


        this.add(start);
        this.add(putIn);
        this.add(putOut);
        this.add(backPM);
        actionListener();
    }

    private void actionListener() {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              // String abc=uhf_demo.abc();
               //System.out.println(abc);
            }
        });

        putIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rfid_data idata=new rfid_data(RFIDResult.getText(),"in");
                idata.saveData();
            }
        });

        putOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rfid_data odata=new rfid_data(RFIDResult.getText(), "out");
                odata.saveData();
            }
        });

        backPM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new index();
                dispose();
            }
        });

    }
}
