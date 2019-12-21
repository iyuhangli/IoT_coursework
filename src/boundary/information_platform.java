package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import control.all_check;
import entity.*;

public class information_platform extends JFrame {

    private JLabel IPInfo=new JLabel("Information platform");
    private JTable jtable=new JTable();
    private JButton backIP=new JButton("Return");
    private DefaultTableModel tableModel;
    ArrayList<all_data> emdata=new ArrayList<all_data>();
    ArrayList<people_data> pdata=new ArrayList<people_data>();
    ArrayList<rfid_data> rdata=new ArrayList<rfid_data>();

    public information_platform(int a){
        this.setResizable(false);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Information platform");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        Font font2 = new Font("Times New Roman", Font.PLAIN, 20);
        Font font3 = new Font("Times New Roman", Font.PLAIN, 15);
        backIP.setContentAreaFilled(false);
        backIP.setFocusPainted(false);

        ImageIcon imageIcon=new ImageIcon("image/information_background.png");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
        this.setContentPane(lbBg);

        IPInfo.setFont(font);
        IPInfo.setBounds(288,50,230,40);
        this.add(IPInfo);
        tableModel = (DefaultTableModel) jtable.getModel();
        if(a==0){
            emShow();
        }
        else if(a==1){
            pdShow();
        }
        else if(a==2){
            rdShow();
        }
        else{
            JOptionPane.showMessageDialog(null, "Error","Error", JOptionPane.ERROR_MESSAGE);
        }
        this.add(jtable);
        backIP.setBounds(650,395,100,25);
        backIP.setFont(font3);
        this.add(backIP);
        actionListener();
    }

    public information_platform() {

    }

    private void emShow() {
        try {
            emdata  = all_check.get();
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        Object[] o={"Data No.","Temperature data","Humidity data","Light data", "Save time"};
        tableModel.addColumn("Data No.");
        tableModel.addColumn("Temperature data");
        tableModel.addColumn("Humidity data");
        tableModel.addColumn("Light data");
        tableModel.addColumn("Save time");
        tableModel.addRow(o);
        for(int j=0;j<emdata.size();j++) {
            Object[] t={emdata.get(j).dataNo,emdata.get(j).tem,emdata.get(j).hum,emdata.get(j).lig,emdata.get(j).saveTime};
            tableModel.addRow(t);
        }
        jtable.setBounds(150,120,480,300);
    }

    private void pdShow() {
        try {
            pdata  = all_check.getPeople();
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        Object[] o={"Data No.","Name","Dept", "Authority","Able or not"};
        tableModel.addColumn("Data No.");
        tableModel.addColumn("Name");
        tableModel.addColumn("Dept");
        tableModel.addColumn("Authority");
        tableModel.addColumn("Open or not");
        tableModel.addRow(o);
        for(int j=0;j<pdata.size();j++) {
            Object[] t={pdata.get(j).id,pdata.get(j).name,pdata.get(j).department,pdata.get(j).authority,pdata.get(j).aval};
            tableModel.addRow(t);
        }
        jtable.setBounds(150,120,480,300);
        backIP.setText("Return");
    }

    private void rdShow(){
        try {
            rdata  = all_check.getRFID();
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        Object[] o={"Tid","Operation type","True or false", "Save time"};
        tableModel.addColumn("Tid");
        tableModel.addColumn("Operation type");
        tableModel.addColumn("True or false");
        tableModel.addColumn("Save time");
        tableModel.addRow(o);
        for(int j=0;j<rdata.size();j++) {
            Object[] t={rdata.get(j).tid,rdata.get(j).inout,rdata.get(j).status,rdata.get(j).savetime};
            tableModel.addRow(t);
        }
        jtable.setBounds(150,120,480,300);
        backIP.setText("Return");
    }

    private void actionListener() {
        backIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new select_type();
            }
        });
    }
}
