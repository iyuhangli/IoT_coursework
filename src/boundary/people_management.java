package boundary;

import com.uhf.demo.UhfDemo;
import com.uhf.linkage.Linkage;
import control.all_check;
import control.rfid_control;
import entity.people_data;
import entity.rfid_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class people_management extends JFrame {

    public ArrayList<people_data> pd = new ArrayList<people_data>();


    private JLabel PMPeopleInfo=new JLabel("People management");
    private JTextField RFIDPeopleResult=new JTextField("");
    private JTextField idtf=new JTextField("");
    private JTextField nametf=new JTextField("");
    private JTextField departmenttf=new JTextField("");
    private JTextField authority=new JTextField("");
    private JTextField avaltf=new JTextField("");
    private JButton startPeople=new JButton("Inventory");
    private JButton enableRFID=new JButton("En/disable");
    private JButton editInfo=new JButton("New/Edit");
    private JButton backPMPeople=new JButton("Back");
    private JLabel idjl=new JLabel("ID");
    private JLabel namejl=new JLabel("Name");
    private JLabel deptjl=new JLabel("Dept");
    private JLabel authjl=new JLabel("Authority");
    private JLabel avaljl=new JLabel("Statu");

    public  people_management(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        Font font2 = new Font("Times New Roman", Font.PLAIN, 15);
        Font font3 = new Font("Times New Roman", Font.PLAIN, 15);




        ImageIcon imageIcon=new ImageIcon("image/people_background.png");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);


        PMPeopleInfo.setFont(font);
        PMPeopleInfo.setBounds(295,50,209,40);
        this.add(PMPeopleInfo);

        RFIDPeopleResult.setBounds(295,220,209,40);
        RFIDPeopleResult.setEditable(false);
        //this.add(RFIDPeopleResult);

        idjl.setBounds(200, 170, 40,40);
        idjl.setFont(font2);
        idtf.setBounds(240, 170, 100, 40);
        idtf.setEditable(false);
        idtf.setFont(font2);
        this.add(idjl);
        this.add(idtf);

        namejl.setBounds(85,280,50,40);
        namejl.setFont(font2);
        nametf.setBounds(135, 280, 100, 40);
        nametf.setEditable(false);
        nametf.setFont(font2);
        this.add(namejl);
        this.add(nametf);
        deptjl.setBounds(300,280,40,40);
        deptjl.setFont(font2);
        departmenttf.setBounds(340, 280, 100, 40);
        departmenttf.setEditable(false);
        departmenttf.setFont(font2);
        this.add(deptjl);
        this.add(departmenttf);
        authjl.setFont(font2);
        authjl.setBounds(505,280,70,40);
        authority.setBounds(575, 280, 100, 40);
        authority.setEditable(false);
        authority.setFont(font2);
        this.add(authjl);
        this.add(authority);

        avaljl.setBounds(420,170,60,40);
        avaljl.setFont(font2);
        avaltf.setBounds(480, 170, 100, 40);
        avaltf.setFont(font2);
        avaltf.setEditable(false);
        this.add(avaljl);
        this.add(avaltf);

        startPeople.setBounds(80,380,100,25);
        enableRFID.setBounds(260,380,100,25);
        editInfo.setBounds(440,380,100,25);
        backPMPeople.setBounds(620,380,100,25);
        enableRFID.setEnabled(false);
        editInfo.setEnabled(false);
        startPeople.setFocusable(true);
        enableRFID.setFocusable(true);
        editInfo.setFocusable(true);
        backPMPeople.setFocusable(true);
        startPeople.setContentAreaFilled(false);
        enableRFID.setContentAreaFilled(false);
        editInfo.setContentAreaFilled(false);
        backPMPeople.setContentAreaFilled(false);
        startPeople.setFont(font3);
        enableRFID.setFont(font3);
        editInfo.setFont(font3);
        backPMPeople.setFont(font3);


        this.add(startPeople);
        this.add(enableRFID);
        this.add(editInfo);
        this.add(backPMPeople);
        actionListenerPeople();
    }

    private void actionListenerPeople(){
        startPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=-1;
                String peopleResult = UhfDemo.abc();
                //String peopleResult="00000000";
                RFIDPeopleResult.setText(peopleResult);
                if(!RFIDPeopleResult.equals("")){
                    try {
                        pd=all_check.getPeople();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    for(int temp=0;temp<pd.size();temp++){
                        if(pd.get(temp).rfidID.equals(peopleResult)){
                            flag=temp;
                        }
                    }
                    if(flag!=-1){
                        idtf.setText(pd.get(flag).id+"");
                        nametf.setText(pd.get(flag).name);
                        departmenttf.setText(pd.get(flag).department);
                        authority.setText(pd.get(flag).authority);
                        avaltf.setText(pd.get(flag).aval);
                        editInfo.setText("Edit");
                        enableRFID.setEnabled(true);
                        if(pd.get(flag).aval.equals("true")){
                            enableRFID.setText("Disable");
                        }
                        else{
                            enableRFID.setText("Enable");
                        }
                    }
                    else{
                        RFIDPeopleResult.setText(peopleResult);
                        editInfo.setText("Create");
                    }
                    editInfo.setEnabled(true);
                }
            }
        });

        enableRFID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag=-1;
                for(int temp=0;temp<pd.size();temp++){
                    if(pd.get(temp).rfidID.equals(RFIDPeopleResult.getText())){
                        flag=temp;
                    }
                }
                if(enableRFID.getText().equals("Enable")){
                    pd.get(flag).aval="true";
                    enableRFID.setText("Disable");
                    avaltf.setText("true");
                }
                else{
                    pd.get(flag).aval="false";
                    enableRFID.setText("Enable");
                    avaltf.setText("false");
                }
                all_check.setPeople(pd);
                JOptionPane.showMessageDialog(null, "Operation OK","OK", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        editInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(editInfo.getText().equals("Confirm")) {
                    int flag = -1;
                    for (int temp = 0; temp < pd.size(); temp++) {
                        if (pd.get(temp).rfidID.equals(RFIDPeopleResult.getText())) {
                            flag = temp;
                        }
                    }
                    pd.get(flag).name=nametf.getText();
                    pd.get(flag).department=departmenttf.getText();
                    pd.get(flag).authority=authority.getText();
                    all_check.setPeople(pd);
                    nametf.setEditable(false);
                    departmenttf.setEditable(false);
                    authority.setEditable(false);
                    editInfo.setText("Edit");
                    JOptionPane.showMessageDialog(null, "Edit OK","OK", JOptionPane.INFORMATION_MESSAGE);

                }
                else if(editInfo.getText().equals("Edit")){
                    nametf.setEditable(true);
                    departmenttf.setEditable(true);
                    authority.setEditable(true);
                    editInfo.setText("Confirm");
                }
                else if(editInfo.getText().equals("Create")){
                    nametf.setEditable(true);
                    departmenttf.setEditable(true);
                    authority.setEditable(true);
                    editInfo.setText("Add to database");
                }
                else{
                    people_data p= null;
                    try {
                        p = new people_data(nametf.getText(),RFIDPeopleResult.getText(),departmenttf.getText(),authority.getText());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    pd.add(p);
                    all_check.setPeople(pd);
                    JOptionPane.showMessageDialog(null, "Create OK.","OK", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        backPMPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new index();
                dispose();
            }
        });

    }
}
