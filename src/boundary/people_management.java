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
    private JTextField idtf=new JTextField("id");
    private JTextField nametf=new JTextField("name");
    private JTextField departmenttf=new JTextField("department");
    private JTextField authority=new JTextField("authority");
    private JTextField avaltf=new JTextField("available");
    private JButton startPeople=new JButton("Inventory");
    private JButton enableRFID=new JButton("Enable/disable");
    private JButton editInfo=new JButton("Create/Edit");
    private JButton backPMPeople=new JButton("Back");

    public  people_management(){
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


        PMPeopleInfo.setFont(font);
        PMPeopleInfo.setBounds(295,50,209,40);
        this.add(PMPeopleInfo);

        RFIDPeopleResult.setBounds(295,220,209,40);
        RFIDPeopleResult.setEditable(false);
        this.add(RFIDPeopleResult);

        idtf.setBounds(50, 310, 100, 40);
        idtf.setEditable(false);
        this.add(idtf);
        nametf.setBounds(200, 310, 100, 40);
        nametf.setEditable(false);
        this.add(nametf);
        departmenttf.setBounds(350, 310, 100, 40);
        departmenttf.setEditable(false);
        this.add(departmenttf);
        authority.setBounds(500, 310, 100, 40);
        authority.setEditable(false);
        this.add(authority);
        avaltf.setBounds(650, 310, 100, 40);
        avaltf.setEditable(false);
        this.add(avaltf);

        startPeople.setBounds(80,400,100,25);
        enableRFID.setBounds(260,400,100,25);
        editInfo.setBounds(440,400,100,25);
        backPMPeople.setBounds(620,400,100,25);
        enableRFID.setEnabled(false);
        editInfo.setEnabled(false);
        startPeople.setFocusable(true);
        enableRFID.setFocusable(true);
        editInfo.setFocusable(true);
        backPMPeople.setFocusable(true);

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
                //String peopleResult = UhfDemo.abc();
                String peopleResult="00000000";
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
                        editInfo.setText("Edit Info");
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
                    editInfo.setText("Edit Info");
                    JOptionPane.showMessageDialog(null, "Edit OK","OK", JOptionPane.INFORMATION_MESSAGE);

                }
                else if(editInfo.getText().equals("Edit Info")){
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
