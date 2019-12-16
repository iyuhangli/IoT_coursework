package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.uhf.demo.UhfDemo;
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
    public ArrayList<rfid_data> rdCheck = new ArrayList<rfid_data>();





    public product_management(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);

        ImageIcon imageIcon=new ImageIcon("image/index_background.png");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
        this.setContentPane(lbBg);

        PMInfo.setFont(font);
        PMInfo.setBounds(291,50,217,40);
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
              String abc= UhfDemo.abc();
              RFIDResult.setText(abc);
              if(!abc.equals("")) {
                  putIn.setEnabled(true);
                  putOut.setEnabled(true);
              }
              else{
                  RFIDResult.setText("No tag here!");
              }
            }
        });

        putIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=0;
                try {
                    rdCheck=all_check.getRFID();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                for(int temp=0;temp<rdCheck.size();temp++){
                    if(rdCheck.get(temp).tid.equals(RFIDResult.getText())&&rdCheck.get(temp).status.equals("true")&&rdCheck.get(temp).inout.equals("in")) {
                        flag = 1;
                    }
                }
                if(flag==0) {
                    rfid_data idata = new rfid_data(RFIDResult.getText(), "in");
                    idata.saveData();
                    JOptionPane.showMessageDialog(null, "Good's operation OK.","OK", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                    JOptionPane.showMessageDialog(null, "Good is already in storage.","Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        putOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag2=0;
                try {
                    rdCheck=all_check.getRFID();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                for(int temp=0;temp<rdCheck.size();temp++){
                    if(rdCheck.get(temp).tid.equals(RFIDResult.getText())&&rdCheck.get(temp).status.equals("true")&&rdCheck.get(temp).inout.equals("in")) {
                        rdCheck.get(temp).status="false";
                        all_check.setRFID(rdCheck);
                        flag2 = 1;
                    }
                }
                if(flag2==1) {
                    rfid_data idata = new rfid_data(RFIDResult.getText(), "out");
                    idata.saveData();
                    JOptionPane.showMessageDialog(null, "Good's operation OK.","OK", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Good is not in storage.","Warning", JOptionPane.WARNING_MESSAGE);
                }
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
