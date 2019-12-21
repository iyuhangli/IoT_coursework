package boundary;

import control.exception.*;
import entity.all_data;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import control.*;

public class environmental_monitoring extends JFrame {

    private JTextArea dataView4=new JTextArea();
    private JScrollPane scrollData4=new JScrollPane(dataView4);

    private JPanel COMSetPanel=new JPanel();
    private JLabel comNo=new JLabel("COM");
    private JLabel baudRate=new JLabel("Baud");

    private JComboBox comSelect=new JComboBox();
    private JComboBox baudRateSelect=new JComboBox();

    private JPanel operationPanel=new JPanel();
    private JButton openCOM=new JButton("Open");
    private JButton sendData=new JButton("Send");
    private JButton backEM=new JButton("Back");

    private JLabel te=new JLabel("Temperature: normal");
    private JLabel hu=new JLabel("Humidity: normal");
    private JLabel li=new JLabel("Light: normal");


    private JTextField dataInput=new JTextField();

    private List<String> commList = null;
    private SerialPort serialPort;
    public static ArrayList<all_data> allData = new ArrayList<all_data>();



    public environmental_monitoring() throws FileNotFoundException {
        this.setResizable(false);
        allData=all_check.get();
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon imageIcon=new ImageIcon("image/environmental_background.jpg");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
        this.setContentPane(lbBg);
        initControl();
        actionListener();
        initData();

    }

    private void initControl() {
        dataView4.setFocusable(false);
        scrollData4.setBounds(396,234,380,139);
        this.add(scrollData4);
        Font font =new Font("Times new roman",1,25);


        COMSetPanel.setBorder(BorderFactory.createTitledBorder("Set COM"));
        COMSetPanel.setBounds(396,378,140,80);
        COMSetPanel.setLayout(null);
        this.add(COMSetPanel);
        comNo.setForeground(Color.gray);
        comNo.setBounds(8,17,32,14);
        COMSetPanel.add(comNo);
        comSelect.setFocusable(false);
        comSelect.setBounds(48,20,80,16);
        COMSetPanel.add(comSelect);
        baudRate.setForeground(Color.gray);
        baudRate.setBounds(8,48,32,16);
        COMSetPanel.add(baudRate);
        baudRateSelect.setFocusable(false);
        baudRateSelect.setBounds(48,48,80,16);
        COMSetPanel.add(baudRateSelect);

        operationPanel.setBorder(BorderFactory.createTitledBorder("Operation"));
        operationPanel.setBounds(538,378,238,80);
        operationPanel.setLayout(null);
        this.add(operationPanel);
        dataInput.setBounds(10,20,218,16);
        operationPanel.add(dataInput);
        openCOM.setFocusable(false);
        openCOM.setBounds(11,48,65,16);
        operationPanel.add(openCOM);
        sendData.setFocusable(false);
        sendData.setBounds(87,48,65,16);
        operationPanel.add(sendData);
        backEM.setFocusable(false);
        backEM.setBounds(163,48,65,16);
        operationPanel.add(backEM);

        te.setBounds(80,40,300,180);
        hu.setBounds(480,40,300,180);
        li.setBounds(120,260,300,180);
        te.setFont(font);
        hu.setFont(font);
        li.setFont(font);
        this.add(te);
        this.add(hu);
        this.add(li);
    }

    private void actionListener() {
        openCOM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Open".equals(openCOM.getText())&&serialPort==null){
                    openSerialPort(e);
                }
                else{
                    closeSerialPort(e);
                }
            }
        });
        sendData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData(e);
            }
        });
        backEM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new index();
                dispose();
            }
        });

    }

    private void initData() {
        commList = control.serial_port_manager.findPort();
        // 检查是否有可用串口，有则加入选项中
        if (commList == null || commList.size() < 1) {
            System.out.println("No serial ports can be found.");
        } else {
            for (String s : commList) {
                comSelect.addItem(s);
            }
            baudRateSelect.addItem("9600");
            baudRateSelect.addItem("19200");
            baudRateSelect.addItem("38400");
            baudRateSelect.addItem("57600");
            baudRateSelect.addItem("115200");
        }
    }

    private void openSerialPort(ActionEvent evt){
        String comStr=(String) comSelect.getSelectedItem();
        int baudRate =9600;
        baudRate=Integer.parseInt((String) baudRateSelect.getSelectedItem());
        if(comStr==null||comStr.equals("")){
            JOptionPane.showMessageDialog(null, "No valid serial ports!","Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                serialPort=control.serial_port_manager.openPort(comStr,baudRate);
                if(serialPort!=null){
                    dataView4.setText("Serial port is opened!"+"\r\n");
                    openCOM.setText("Close");
                }
            } catch (PortInUse portInUse) {
                portInUse.printStackTrace();
            } catch (NoSuchPort noSuchPort) {
                noSuchPort.printStackTrace();
            } catch (NotASerialPort notASerialPort) {
                notASerialPort.printStackTrace();
            } catch (SerialPortParameterFailure serialPortParameterFailure) {
                serialPortParameterFailure.printStackTrace();
                JOptionPane.showMessageDialog(null, "Serial port is occupied!","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        try{
            control.serial_port_manager.addListener(serialPort,new serial_listener());
        } catch (TooManyListeners e){
            e.printStackTrace();
        }
    }

    private void closeSerialPort(ActionEvent evt){
    }

    private void sendData(java.awt.event.ActionEvent evt) {
        String data = dataInput.getText().toString();
        try {
            control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(data));
        } catch (SendDataToSerialPortFailure e) {
            e.printStackTrace();
        } catch (SerialPortOutputStreamCloseFailure e) {
            e.printStackTrace();
        }
    }

    public class serial_listener implements SerialPortEventListener {
        public void serialEvent(SerialPortEvent serialPortEvent){
            int wen=1,shi=1,guang=1;
            String we,sh,gu;
            switch(serialPortEvent.getEventType()){
                case SerialPortEvent.BI://Communication interruption
                    JOptionPane.showMessageDialog(null, "Communication interruption","Communication interruption", JOptionPane.ERROR_MESSAGE);
                    break;
                case SerialPortEvent.OE: //Overflow error
                case SerialPortEvent.FE: //Frame error
                case SerialPortEvent.PE: //Parity error
                case SerialPortEvent.CD: //Carrier detection
                case SerialPortEvent.CTS: //Clear data to be sent
                case SerialPortEvent.DSR: //Ready to send data
                case SerialPortEvent.RI: //Ringing indication
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: //Output buffer cleared
                    break;
                case SerialPortEvent.DATA_AVAILABLE: //Data available
                    byte[] data=null;
                    try{
                        if(serialPort==null){
                            JOptionPane.showMessageDialog(null, "Serial port object is empty! Monitoring failed","Monitoring failed", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            data = control.serial_port_manager.readFromPort(serialPort);
                            dataView4.append(control.byte_utils.byteArrayToHexString2(data) + "\r\n");
                            String a="";
                            String red="FFFFB6260010FEFE";
                            String green="FFFFB6260001FEFE";
                            a=control.byte_utils.byteArrayToHexString2(data);
                            //System.out.println(a+"  "+a.charAt(7));
                            if((a.charAt(7))=='3') {
                                we=a.substring(40,44);
                                sh=a.substring(36,40);
                                gu=a.substring(44,48);
                                all_data ad=new all_data(we,sh,gu);
                                ad.saveData();
                                allData.add(ad);
                                if ((a.charAt(44))>65){//if bigger than A***, red light
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(red));
                                    guang=0;
                                    li.setText("Light: warning");
                                    li.setForeground(Color.red);
                                } else {
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(green));
                                    guang=1;
                                    hu.setText("Light: normal");
                                    hu.setForeground(Color.black);
                                }
                                if ((a.charAt(40))>65){//if bigger than A***, red light
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(red));
                                    shi=0;
                                    hu.setText("Humidity: warning");
                                    hu.setForeground(Color.red);
                                } else {
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(green));
                                    shi=1;
                                    hu.setText("Humidity: normal");
                                    hu.setForeground(Color.black);
                                }
                                if ((a.charAt(36))>65){//if bigger than A***, red light
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(red));
                                    wen=0;
                                    te.setText("Temperature: warning");
                                    te.setForeground(Color.red);
                                } else {
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(green));
                                    wen=1;
                                    te.setText("Temperature: normal");
                                    te.setForeground(Color.black);
                                }

                            }
                        }
                    } catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                        new index();
                        dispose();
                    }
            }
            all_check.setAllData(allData);
        }
    }
}
