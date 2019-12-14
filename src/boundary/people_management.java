package boundary;

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

    public static ArrayList<people_data> pd = new ArrayList<people_data>();


    private JLabel PMPeopleInfo=new JLabel("People management");
    private JTextField RFIDPeopleResult=new JTextField("");
    private JTextField idtf=new JTextField("id");
    private JTextField nametf=new JTextField("name");
    private JTextField departmenttf=new JTextField("department");
    private JTextField authority=new JTextField("authority");
    private JButton startPeople=new JButton("Inventory");
    private JButton checkRFID=new JButton("Show info");
    private JButton editInfo=new JButton("Edit info");
    private JButton backPMPeople=new JButton("Back");

    public  people_management(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);

        PMPeopleInfo.setFont(font);
        PMPeopleInfo.setBounds(295,50,209,40);
        this.add(PMPeopleInfo);

        RFIDPeopleResult.setBounds(295,220,209,40);
        RFIDPeopleResult.setEditable(false);
        this.add(RFIDPeopleResult);

        idtf.setBounds(80, 310, 100, 40);
        idtf.setEditable(false);
        this.add(idtf);
        nametf.setBounds(260, 310, 100, 40);
        nametf.setEditable(false);
        this.add(nametf);
        departmenttf.setBounds(440, 310, 100, 40);
        departmenttf.setEditable(false);
        this.add(departmenttf);
        authority.setBounds(620, 310, 100, 40);
        authority.setEditable(false);
        this.add(authority);

        startPeople.setBounds(80,400,100,25);
        checkRFID.setBounds(260,400,100,25);
        editInfo.setBounds(440,400,100,25);
        backPMPeople.setBounds(620,400,100,25);
        checkRFID.setEnabled(false);
        editInfo.setEnabled(false);
        startPeople.setFocusable(true);
        checkRFID.setFocusable(true);
        editInfo.setFocusable(true);
        backPMPeople.setFocusable(true);

        this.add(startPeople);
        this.add(checkRFID);
        this.add(editInfo);
        this.add(backPMPeople);
        actionListenerPeople();
    }

    private void actionListenerPeople(){
        startPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = Linkage.getInstance().initial("COM5");// 初始化连接设备,参数：端口号
                if (i == 0) {
                    //System.out.println("connect success");
                    rfid_control.getInventoryArea();
                    rfid_control.setInventoryArea();
                    rfid_control.startInventory();
                    rfid_control.stopInventory();
                    String read_result = rfid_control.tidRead();
                    RFIDPeopleResult.setText(read_result);
                    if (!read_result.equals("")) {
                        checkRFID.setEnabled(true);
                        //to be continue
                        editInfo.setEnabled(true);
                    }
                    Linkage.getInstance().deinit();
                } else {
                    System.out.println("connect failed");
                }
            }
        });

        checkRFID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pd=new ArrayList<people_data>(all_check.getPeople());
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                int flag=0;
                for(int i=0;i<pd.size();i++){
                    if(pd.get(i).id==Integer.parseInt(RFIDPeopleResult.getText())){
                        idtf.setText(pd.get(i).id+"");
                        nametf.setText(pd.get(i).name);
                        departmenttf.setText(pd.get(i).department);
                        authority.setText(pd.get(i).authority);
                        flag=1;
                    }
                }
                if(flag==0){
                    idtf.setText("Can't found this ID");
                }
            }
        });
    }


}
