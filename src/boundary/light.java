package boundary;
import control.*;

import control.exception.*;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class light extends JFrame {

    ImageIcon bulbd = new ImageIcon("img/dark.png");
    ImageIcon bulbl = new ImageIcon("img/light.png");
    JButton bulb = new JButton(bulbl);
    JButton auto = new JButton("Auto-control");
    JButton human = new JButton("Manual control");
    JButton back = new JButton();
    JButton control = new JButton("");
    private SerialPort serialPort;
    JLabel info = new JLabel();
    Font button = new Font("Times New Roman", Font.PLAIN, 20);
    int temp=0;

    public light() {
        this.setSize(1000, 625);
        this.setTitle("Light control");
        this.setDefaultCloseOperation(mainPage.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        ImageIcon background=new ImageIcon("img/library.png");
        JLabel lbBg = new JLabel(background);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        this.setContentPane(lbBg);

        bulb.setBounds(414, 50, 173, 200);
        this.add(bulb);
        info.setFont(button);
        info.setBounds(420, 280, 200, 40);
        this.add(info);
        auto.setFont(button);
        auto.setBounds(260, 360, 160, 40);
        this.add(auto);
        human.setFont(button);
        human.setBounds(575, 360, 160, 40);
        this.add(human);
        ImageIcon img = new ImageIcon("img/back.png");
        back = new JButton(img);
        back.setBounds(10, 10, 60, 60);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        this.add(back);
        auto.setContentAreaFilled(false);
        auto.setFocusPainted(false);
        human.setContentAreaFilled(false);
        human.setFocusPainted(false);
        action();

    }

    private void humanControl() {
        if(temp==0){
            human.setText("Off");
        }
        else{
            human.setText("On");
        }
    }

    private void lightControl() {
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
            serial_port_manager.addListener(serialPort,new serialListener());
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
                                if ((a.charAt(44))>65){//if bigger than A***, red light
                                    serial_port_manager.sendToPort(serialPort, byte_utils.hexStr2Byte(red));
                                    info.setText("Current statu: Strong light");
                                    bulb.setIcon(bulbd);
                                } else {
                                    serial_port_manager.sendToPort(serialPort,byte_utils.hexStr2Byte(green));
                                    info.setText("Current statu: Dark");
                                    bulb.setIcon(bulbl);
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

        auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                human.setText("Manual control");
                lightControl();
            }
        });

        human.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(human.getText().equals("Manual control")) {
                    humanControl();
                }
                else if(human.getText().equals("On")){
                    bulb.setIcon(bulbl);
                    human.setText("Off");
                }
                else{
                    bulb.setIcon(bulbd);
                    human.setText("On");
                }
            }
        });
    }
}
