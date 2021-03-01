package boundary;

import control.byte_utils;
import control.exception.*;
import control.serial_port_manager;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fire extends JFrame {

    JLabel info=new JLabel("Current temperature statu: Normal");
    JButton back =new JButton();
    private SerialPort serialPort;
    Font a=new Font("Times New Roman", Font.PLAIN, 25);

    public fire(){
        this.setSize(1000, 625);
        this.setTitle("Library monitor");
        this.setDefaultCloseOperation(mainPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        ImageIcon background=new ImageIcon("img/library.png");
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);
        info.setFont(a);
        info.setBounds(300,160,428,40);
        this.add(info);
        ImageIcon img=new ImageIcon("img/back.png");
        back=new JButton(img);
        back.setBounds(10,10,60,60);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        this.add(back);
        action();
        fireControl();


    }

    private void fireControl() {
        int baudRate = 115200;
        String comStr = serial_port_manager.findPort().get(1);
        try {
            serialPort = serial_port_manager.openPort(comStr, baudRate);
        } catch (PortInUse portInUse) {
            portInUse.printStackTrace();
        } catch (NoSuchPort noSuchPort) {
            noSuchPort.printStackTrace();
        } catch (NotASerialPort notASerialPort) {
            notASerialPort.printStackTrace();
        } catch (SerialPortParameterFailure serialPortParameterFailure) {
            serialPortParameterFailure.printStackTrace();
            JOptionPane.showMessageDialog(null, "Serial port is occupied!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        try{
            serial_port_manager.addListener(serialPort,new fire.serialListener());
        } catch (TooManyListeners e){
            e.printStackTrace();
        }

    }

    public class serialListener implements SerialPortEventListener {
        public void serialEvent(SerialPortEvent serialPortEvent){
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
                            data = serial_port_manager.readFromPort(serialPort);
                            String a="";
                            String red="FFFFB6260010FEFE";
                            String green="FFFFB6260001FEFE";
                            a= byte_utils.byteArrayToHexString2(data);
                            //System.out.println(a+"  "+a.charAt(7));
                            if((a.charAt(7))=='3') {
                                if ((a.charAt(40))>65){//if bigger than A***, red light
                                    serial_port_manager.sendToPort(serialPort, byte_utils.hexStr2Byte(red));
                                    info.setText("Current temperature statu: Warning");
                                } else {
                                    serial_port_manager.sendToPort(serialPort,byte_utils.hexStr2Byte(green));
                                    info.setText("Current temperature statu: Normal");
                                }
                            }
                        }
                    } catch(Exception e){
                    }
            }
        }
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
