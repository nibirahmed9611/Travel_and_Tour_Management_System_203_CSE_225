package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;



public class Dashboard extends JFrame implements ActionListener {
    
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\packages.txt";
    String hotelPath = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt";

    public static void main(String[] args) {
        
    }
    
    private String loggedInUser;
    
    public String getLoggedInUser(){
        return this.loggedInUser;
    }
    
    public void setLoggedInUser(String loggedInUser){
        this.loggedInUser = loggedInUser;
    }
    
    public Dashboard( String id ){
        this.setLoggedInUser(id);
        initialize();     
        
        this.setTitle("Admin Dashboard | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(10, 10, 1367, 700);
        this.setResizable(false);
    }
    
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14;
    
    public void initialize(){
        
        JLabel bg;
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 1367, 700);
        
        ImageIcon bgi = new ImageIcon(this.getClass().getResource("images/dashboard-bg.jpg"));

        bg = new JLabel("",bgi,JLabel.LEFT);
        bg.setVerticalAlignment(JLabel.TOP);
        bg.setBounds(0, 0, 1367, 700);
        
        this.add(p1);
        p1.add(bg);
        
        
        b1 = new JButton("");
        b1.setBounds(1249, 15, 82, 32);
        b1.setOpaque(false); // make it transparent
        b1.setContentAreaFilled(false); //content area is also made transparent
        b1.setBackground(null);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        bg.add(b1);
        
        b2 = new JButton("My Profile");
        b2.setBounds(0, 65, 248, 40);
        b2.setBackground(new Color(52,58,62));
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        bg.add(b2);
        
        b3 = new JButton("View Packages");
        b3.setBounds(0, 105, 248, 40);
        b3.setBackground(new Color(52,58,62));
        b3.setForeground(Color.white);
        b3.addActionListener(this);
        bg.add(b3);
        
        b4 = new JButton("Add Package");
        b4.setBounds(0, 145, 248, 40);
        b4.setBackground(new Color(52,58,62));
        b4.setForeground(Color.white);
        b4.addActionListener(this);
        bg.add(b4);
        
        b5 = new JButton("Edit Packages");
        b5.setBounds(0, 185, 248, 40);
        b5.setBackground(new Color(52,58,62));
        b5.setForeground(Color.white);
        b5.addActionListener(this);
        bg.add(b5);
        
        b6 = new JButton("Delete Package");
        b6.setBounds(0, 225, 248, 40);
        b6.setBackground(new Color(52,58,62));
        b6.setForeground(Color.white);
        b6.addActionListener(this);
        bg.add(b6);
        
        b7 = new JButton("View Hotels");
        b7.setBounds(0, 265, 248, 40);
        b7.setBackground(new Color(52,58,62));
        b7.setForeground(Color.white);
        b7.addActionListener(this);
        bg.add(b7);
        
        b8 = new JButton("Add Hotels");
        b8.setBounds(0, 305, 248, 40);
        b8.setBackground(new Color(52,58,62));
        b8.setForeground(Color.white);
        b8.addActionListener(this);
        bg.add(b8);
        
        b9 = new JButton("Edit Hotels");
        b9.setBounds(0, 345, 248, 40);
        b9.setBackground(new Color(52,58,62));
        b9.setForeground(Color.white);
        b9.addActionListener(this);
        bg.add(b9);
        
        b10 = new JButton("Delete Hotels");
        b10.setBounds(0, 385, 248, 40);
        b10.setBackground(new Color(52,58,62));
        b10.setForeground(Color.white);
        b10.addActionListener(this);
        bg.add(b10);
        
        b11 = new JButton("Package Enrolled");
        b11.setBounds(0, 425, 248, 40);
        b11.setBackground(new Color(52,58,62));
        b11.setForeground(Color.white);
        b11.addActionListener(this);
        bg.add(b11);
        
        b12 = new JButton("Hotels Enrolled");
        b12.setBounds(0, 465, 248, 40);
        b12.setBackground(new Color(52,58,62));
        b12.setForeground(Color.white);
        b12.addActionListener(this);
        bg.add(b12);
        
//        b13 = new JButton("About");
//
//        b13.setBackground(new Color(52,58,62));
//        b13.setForeground(Color.white);
//        b13.addActionListener(this);
//        bg.add(b13);
        
        b14 = new JButton("Logout");
        b14.setBounds(0, 505, 248, 40);
        b14.setBackground(new Color(52,58,62));
        b14.setForeground(Color.white);
        b14.addActionListener(this);
        bg.add(b14);
        
    }
    
    public void editPackage(){
        String id = JOptionPane.showInputDialog("Package ID"); 
        
        if( id != null ){
            if( !id.equals("") ){
                String line;
                int flag = 0;

                try {
                    FileReader fr = new FileReader(path);
                    BufferedReader br = new BufferedReader(fr);

                    while ((line = br.readLine()) != null){
                        if(line.contains(" ")) {
                            String[] split = line.split(" ");
                            if(line.split(" ")[0].equals(id)){
                                flag++;
                            }
                        }
                    }

                    fr.close();
                    br.close();

                    if( flag == 0 ){
                        JOptionPane.showMessageDialog(null, "No data found","Error", JOptionPane.ERROR_MESSAGE);   
                        return;
                    }else{
                        EditPackage ep = new EditPackage(id);
                        ep.setVisible(true);
                    }
                }
                catch (Exception ep) {
                    System.out.println("ERROR 404! File-Not-Found");
                    ep.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    
    public void deletePackage(){
     
        String id = JOptionPane.showInputDialog("Package ID"); 
        if( id != null ){
            if( !id.equals("") ){
                String line; 
                int flag = 0;
                try {

                    File oldFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\packages.txt");
                    File newFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\temp.txt");
                    if(!newFile.exists()){
                        newFile.createNewFile();
                    }

                    FileReader fr = new FileReader(path);
                    BufferedReader br = new BufferedReader(fr);

                    while ((line = br.readLine()) != null){
                        if(line.contains(" ")) {
                            String[] split = line.split(" ");
                            if(!id.equalsIgnoreCase(split[0])){
                                FileWriter myWriter = new FileWriter(newFile.getAbsolutePath(),true);
                                PrintWriter printWriter = new PrintWriter(myWriter);
                                printWriter.println(line);
                                printWriter.close();
                            }else{
                                flag++;
                            }
                        }
                    }

                    fr.close();
                    br.close();

                    oldFile.delete();
                    newFile.renameTo(oldFile);

                    if(flag == 0){
                        JOptionPane.showMessageDialog(null, "No Data Found","Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Successfully Deleted","Success", JOptionPane.PLAIN_MESSAGE);
                    }

                }
                catch (Exception ep) {
                    System.out.println("ERROR 404! File-Not-Found");
                    ep.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        
        
    }
    
    public void editHotel(){
        String id = JOptionPane.showInputDialog("Package ID"); 
        if(id != null){
            if( !id.equals("") ){
                String line;
                int flag = 0;

                try {
                    FileReader fr = new FileReader(hotelPath);
                    BufferedReader br = new BufferedReader(fr);

                    while ((line = br.readLine()) != null){
                        if(line.contains(" ")) {
                            String[] split = line.split(" ");
                            if(line.split(" ")[0].equals(id)){
                                flag++;
                            }
                        }
                    }
                    fr.close();
                    br.close();

                    if( flag == 0 ){
                        JOptionPane.showMessageDialog(null, "No data found","Error", JOptionPane.ERROR_MESSAGE);   
                        return;
                    }else{
                        EditHotel ep = new EditHotel(id);
                        ep.setVisible(true);
                    }
                }
                catch (Exception ep) {
                    System.out.println("ERROR 404! File-Not-Found");
                    ep.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    
    public void deleteHotel(){
     
        String id = JOptionPane.showInputDialog("Package ID"); 
        if(id != null){
            if( !id.equals("") ){
                String line; 
                int flag = 0;
                try {

                    File oldFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt");
                    File newFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\temp.txt");
                    if(!newFile.exists()){
                        newFile.createNewFile();
                    }

                    FileReader fr = new FileReader(hotelPath);
                    BufferedReader br = new BufferedReader(fr);

                    while ((line = br.readLine()) != null){
                        if(line.contains(" ")) {
                            String[] split = line.split(" ");
                            if(!id.equalsIgnoreCase(split[0])){
                                FileWriter myWriter = new FileWriter(newFile.getAbsolutePath(),true);
                                PrintWriter printWriter = new PrintWriter(myWriter);
                                printWriter.println(line);
                                printWriter.close();
                            }else{
                                flag++;
                            }
                        }
                    }

                    fr.close();
                    br.close();

                    oldFile.delete();
                    newFile.renameTo(oldFile);

                    if(flag == 0){
                        JOptionPane.showMessageDialog(null, "No Data Found","Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Successfully Deleted","Success", JOptionPane.PLAIN_MESSAGE);
                    }

                }
                catch (Exception ep) {
                    System.out.println("ERROR 404! File-Not-Found");
                    ep.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == b1 ){
            this.dispose();
            Login l = new Login();
            l.setVisible(true);
            
        }else if( e.getSource() == b2 ){
            MyProfile mp = new MyProfile(this.getLoggedInUser());
            mp.setVisible(true);
            
        }else if( e.getSource() == b14 ){
            this.dispose();
            Login l = new Login();
            l.setVisible(true);
            
        }else if( e.getSource() == b4 ){
            AddPackage ap = new AddPackage();
            ap.setVisible(true);
            
        }else if( e.getSource() == b3 ){
            AllPackages alp = new AllPackages();
            alp.setVisible(true);
            
        }else if( e.getSource() == b5 ){
            this.editPackage();
        }else if( e.getSource() == b6 ){
            this.deletePackage();
        }else if( e.getSource() == b8 ){
            AddHotel ap = new AddHotel();
            ap.setVisible(true);
        }else if( e.getSource() == b7 ){
            AllHotels ap = new AllHotels();
            ap.setVisible(true);
        }else if( e.getSource() == b9 ){
            this.editHotel();
        }else if( e.getSource() == b10 ){
            this.deleteHotel();
        }else if( e.getSource() == b11 ){
            UserEnrolledPackage uep = new UserEnrolledPackage(this.getLoggedInUser());
            uep.setVisible(true);
        }else if( e.getSource() == b12 ){
            UserEnrolledHotel ueh = new UserEnrolledHotel(this.getLoggedInUser());
            ueh.setVisible(true);
        }
    }
}
