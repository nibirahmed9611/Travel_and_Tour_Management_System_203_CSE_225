package tms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class AddHotel extends JFrame implements ActionListener {
    
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt";

    AddHotel(){
        initialize();     
        
        this.setTitle("Add Hotel | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(400, 150, 400, 400);
        this.setResizable(false);
    }
    
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton b1,b2;
    
    public void initialize(){
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 400, 400);
        this.add(p1);
        
        JLabel h1 = new JLabel("Add Hotel");
        h1.setForeground(Color.black);
        h1.setFont(new Font("Tahoma",Font.BOLD,26));
        h1.setBounds(85, 15, 200, 50);
        h1.setHorizontalAlignment(JLabel.CENTER);
        p1.add(h1);
        
        JLabel t1 = new JLabel("Name of Hotel: ");
        t1.setForeground(Color.black);
        t1.setFont(new Font("Tahoma",Font.BOLD,14));
        t1.setBounds(20, 80, 150, 50);
        p1.add(t1);
        
        tf1 = new JTextField();
        tf1.setBounds(160,95,170,25);
        tf1.setToolTipText("Name of Hotel");
        p1.add(tf1);
        
        JLabel t2 = new JLabel("No of rooms: ");
        t2.setForeground(Color.black);
        t2.setFont(new Font("Tahoma",Font.BOLD,14));
        t2.setBounds(20, 120, 150, 50);
        p1.add(t2);
        
        tf2 = new JTextField();
        tf2.setBounds(160,135,170,25);
        tf2.setToolTipText("No of rooms");
        p1.add(tf2);
        
        JLabel t3 = new JLabel("Date: ");
        t3.setForeground(Color.black);
        t3.setFont(new Font("Tahoma",Font.BOLD,14));
        t3.setBounds(20, 160, 150, 50);
        p1.add(t3);
        
        tf3 = new JTextField();
        tf3.setBounds(160,175,170,25);
        tf3.setToolTipText("Date");
        p1.add(tf3);
        
        JLabel t4 = new JLabel("Budget: ");
        t4.setForeground(Color.black);
        t4.setFont(new Font("Tahoma",Font.BOLD,14));
        t4.setBounds(20, 200, 150, 50);
        p1.add(t4);
        
        tf4 = new JTextField();
        tf4.setBounds(160,215,170,25);
        tf4.setToolTipText("Budget");
        p1.add(tf4);
        
        b1 = new JButton("Cancel");
        b1.setBounds(20, 280, 100, 25);
        b1.setBackground( new Color(234,29,98) );
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        p1.add(b1);
        
        b2 = new JButton("Add");
        b2.setBounds(228, 280, 100, 25);
        b2.setBackground( new Color(81,181,110) );
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        p1.add(b2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            this.dispose();
        }else if(e.getSource() == b2){
            try{
                String name = !tf1.getText().equals("") ? tf1.getText().replace(" ", "+") : "null" ;
                String rooms = !tf2.getText().equals("") ? tf2.getText().replace(" ", "+") : "null";
                String date = !tf3.getText().equals("") ? tf3.getText().replace(" ", "+") : "null";
                String budget = !tf4.getText().equals("") ? tf4.getText().trim().replace(" ", "+") : "null";

                int id = this.getNewId();
                
                FileWriter myWriter = new FileWriter(path,true);
                PrintWriter printWriter = new PrintWriter(myWriter);
                printWriter.println(id+" "+name+" "+rooms+" "+date+" "+budget+"\n");
                printWriter.close();

                JOptionPane.showMessageDialog(null, "Successfully Added! ","Confirmation", JOptionPane.PLAIN_MESSAGE);

                this.dispose();
            }
            catch (IOException ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
            }
        }
    }
    
    public int getNewId(){
        
        String line;
        int id = 1;
        
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            if (br.readLine() == null) {
                fr.close();
                br.close();
                return 1;
            }

            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    id = Integer.parseInt(split[0]);
                }
            }
            fr.close();
            br.close();
            return (id + 1);
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
        
        return 1;
    }
}
