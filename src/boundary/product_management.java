package boundary;

import javax.swing.*;
import java.awt.*;

public class product_management extends JFrame{
    private JLabel RFID=new JLabel();
    private JLabel PMInof=new JLabel("Product management");

    public product_management(){
        this.setSize(800, 500);
        this.setLayout(null);
        this.setTitle("Environmental monitoring");
        this.setDefaultCloseOperation(index.EXIT_ON_CLOSE);
        this.setVisible(true);
        Font font = new Font("Times New Roman", Font.PLAIN, 25);

        PMInof.setFont(font);
        PMInof.setBounds(295,50,209,40);
        this.add(PMInof);
    }

}
