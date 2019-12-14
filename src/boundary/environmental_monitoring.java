package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import control.all_check;
import control.exception.*;
import entity.all_data;
import gnu.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYCrosshairState;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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

    private JTextField dataInput=new JTextField();

    private JPanel temp=new JPanel();

    public ArrayList<Double> commlist = new ArrayList<Double>();
    private SerialPort serialPort;
    XYSeries xySeriesT = new XYSeries("Temp");

    public static JFreeChart jfreechart=null;

    public environmental_monitoring() throws FileNotFoundException {

        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);

        initTemp();

        initControl();
        actionListener();
        initData();
    }


    public XYDataset createDataset(){
        XYSeriesCollection xySeriesCollection=new XYSeriesCollection();
        xySeriesCollection.addSeries(xySeriesT);
        return xySeriesCollection;
    }

    public void dataFrom(){
        int i=0;

        xySeriesT.add(i,commlist.get(i));

    }

    private void initTemp() throws FileNotFoundException {
        commlist= all_data.getDataValue("te");
        temp.setBounds(8,8,384,234);
        temp.setVisible(true);
        dataFrom();
        jfreechart = ChartFactory.createXYLineChart(
                null, null, null, createDataset(),
                PlotOrientation.VERTICAL, false, true, false);

        StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("Times New Roman", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("Times New Roman", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("Times New Roman", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);
        jfreechart.setBorderPaint(new Color(0,204,205));
        jfreechart.setBorderVisible(true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setLowerBound(0);
        numberaxis.setUpperBound(100);
        numberaxis.setTickUnit(new NumberTickUnit(100d));
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setLowerMargin(0); // 数据轴下（左）边距 ­
        numberaxis.setMinorTickMarksVisible(false);// 标记线是否显示
        numberaxis.setTickMarkInsideLength(0);// 外刻度线向内长度
        numberaxis.setTickMarkOutsideLength(0);
        // X轴的设计
        NumberAxis x = (NumberAxis) xyplot.getDomainAxis();
        x.setAutoRange(true);// 自动设置数据轴数据范围
        // 自己设置横坐标的值
        x.setAutoTickUnitSelection(false);
        x.setTickUnit(new NumberTickUnit(60d));
        // 设置最大的显示值和最小的显示值
        x.setLowerBound(0);
        x.setUpperBound(60);
        // 数据轴的数据标签：只显示整数标签
        x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        x.setAxisLineVisible(true);// X轴竖线是否显示
        x.setTickMarksVisible(false);// 标记线是否显示
        org.jfree.chart.ui.RectangleInsets offset = new RectangleInsets(0, 0, 0, 0);
        xyplot.setAxisOffset(offset);// 坐标轴到数据区的间距
        xyplot.setBackgroundAlpha(0.0f);// 去掉柱状图的背景色
        xyplot.setOutlinePaint(null);// 去掉边框
        // ChartPanel chartPanel = new ChartPanel(jfreechart);
        // chartPanel.restoreAutoDomainBounds();//重置X轴
        ChartPanel chartPanel = new ChartPanel(jfreechart, true);
        temp.add(chartPanel);
        chartPanel.setVisible(true);

    }


    private void initControl() {
        dataView4.setFocusable(false);
        scrollData4.setBounds(396,234,380,139);
        this.add(scrollData4);

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
    }

    private void actionListener() {
        openCOM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Open serial port".equals(openCOM.getText())&&serialPort==null){
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
                                if ((a.charAt(44))>65){//if bigger than A***, red light
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(red));
                                } else {
                                    control.serial_port_manager.sendToPort(serialPort, control.byte_utils.hexStr2Byte(green));
                                }
                            }
                        }
                    } catch(Exception e){
                        JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                        new index();
                        dispose();
                    }
            }
        }
    }
}
