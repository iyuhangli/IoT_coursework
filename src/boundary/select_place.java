package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class select_place extends JFrame {

    private JLabel SPInfo=new JLabel("Please select a place");
    private JButton backSP=new JButton("Back");
    private JButton show=new JButton("Show");
    private JComboBox placeSelect=new JComboBox();
    private List<String> placeList = null;

    public select_place(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Information platform");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);
        Font font2 = new Font("Times New Roman", Font.PLAIN, 20);
        Font font3 = new Font("Times New Roman", Font.PLAIN, 15);

        ImageIcon imageIcon=new ImageIcon("image/select_environmental_background.jpg");
        JLabel lbBg = new JLabel(imageIcon);
        lbBg.setBounds(0, 0, this.getSize().width, this.getSize().height);
        //this.getContentPane().add(lbBg);
        this.setContentPane(lbBg);

        SPInfo.setFont(font);
        SPInfo.setBounds(291,50,217,40);
        this.add(SPInfo);

        backSP.setBounds(620,380,100,25);
        backSP.setFont(font3);
        backSP.setContentAreaFilled(false);
        backSP.setFocusPainted(false);
        this.add(backSP);
        show.setBounds(420,380,100,25);
        show.setFont(font3);
        show.setContentAreaFilled(false);
        show.setFocusPainted(false);
        this.add(show);
        actionListenerSP();

        placeSelect.addItem("Warehouse 1");
        placeSelect.addItem("Warehouse 2");
        placeSelect.setFocusable(false);
        placeSelect.setBounds(300,220,200,40);
        this.add(placeSelect);

    }

    private void actionListenerSP() {
        backSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new index();
                dispose();
            }
        });

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select = (String) placeSelect.getSelectedItem();
                if (!select.equals("")) {
                    try {
                        new environmental_monitoring();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                }
            }
        });
    }

}
