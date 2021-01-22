package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class UserDashboard extends JFrame implements ActionListener {
    
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\packages.txt";
    String hotelPath = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt";
    String user_package = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\user_package.txt";
    String user_hotel = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\user_hotel.txt";

    public static void main(String[] args) {
        
    }
    
    private String loggedInUser;
    
    public String getLoggedInUser(){
        return this.loggedInUser;
    }
    
    public void setLoggedInUser(String loggedInUser){
        this.loggedInUser = loggedInUser;
    }
    
    public UserDashboard( String id ){
        this.setLoggedInUser(id);
        initialize();     
        
        this.setTitle("User Dashboard | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(10, 10, 1367, 700);
        this.setResizable(false);
    }
    
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
    
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
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
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
        
        b4 = new JButton("Enroll Package");
        b4.setBounds(0, 145, 248, 40);
        b4.setBackground(new Color(52,58,62));
        b4.setForeground(Color.white);
        b4.addActionListener(this);
        bg.add(b4);
        
        b5 = new JButton("Packages Enrolled");
        b5.setBounds(0, 185, 248, 40);
        b5.setBackground(new Color(52,58,62));
        b5.setForeground(Color.white);
        b5.addActionListener(this);
        bg.add(b5);
        
        b6 = new JButton("Package Disenroll");
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
        
        b8 = new JButton("Enroll Hotels");
        b8.setBounds(0, 305, 248, 40);
        b8.setBackground(new Color(52,58,62));
        b8.setForeground(Color.white);
        b8.addActionListener(this);
        bg.add(b8);
        
        b9 = new JButton("Hotels Enrolled");
        b9.setBounds(0, 345, 248, 40);
        b9.setBackground(new Color(52,58,62));
        b9.setForeground(Color.white);
        b9.addActionListener(this);
        bg.add(b9);
        
        b10 = new JButton("Hotel Disenroll");
        b10.setBounds(0, 385, 248, 40);
        b10.setBackground(new Color(52,58,62));
        b10.setForeground(Color.white);
        b10.addActionListener(this);
        bg.add(b10);
        
        b12 = new JButton("Logout");
        b12.setBounds(0, 425, 248, 40);
        b12.setBackground(new Color(52,58,62));
        b12.setForeground(Color.white);
        b12.addActionListener(this);
        bg.add(b12);
        
        
    }
    
    public void enrollPackage(){
        String id = JOptionPane.showInputDialog("Package ID"); 

        if(id != null){
            if( !id.equals("") ){
                if(packageExists(id)){
                    if(!checkPackageEnrolled(id)){
                        try{
                            int newid = this.getNewId();

                            FileWriter myWriter = new FileWriter(user_package,true);
                            PrintWriter printWriter = new PrintWriter(myWriter);
                            printWriter.println(newid+" "+this.getLoggedInUser()+" "+id+"\n");
                            printWriter.close();

                            JOptionPane.showMessageDialog(null, "Successfully Enrolled! ","Confirmation", JOptionPane.PLAIN_MESSAGE);

                        }catch (IOException ep) {
                            System.out.println("ERROR 404! File-Not-Found");
                            ep.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Already Enrolled","Error", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Package","Error", JOptionPane.WARNING_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    
    public boolean packageExists(String id){
        String line;
        
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    String packageid = split[0];
                    if(id.equalsIgnoreCase(packageid)){
                        return true;
                    }
                }
                
            }
            fr.close();
            br.close();
            
            return false;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
        return false;
    }
    
    public boolean checkPackageEnrolled(String packageID){
        String line;
        
        try {
            FileReader fr = new FileReader(user_package);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    String userid = split[1];
                    String packageid = split[2];
                    if(packageID.equalsIgnoreCase(packageid) && this.getLoggedInUser().equalsIgnoreCase(userid)){
                        return true;
                    }
                }
                
            }
            fr.close();
            br.close();
            
            return false;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
	return false;
    }
    
    public int getNewId(){
        
        String line;
        int id = 1;
        
        try {
            FileReader fr = new FileReader(user_package);
            BufferedReader br = new BufferedReader(fr);
            
            if (br.readLine() == null) {
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
    
    
    
    public void disEnrolledPackage(){
        String id = JOptionPane.showInputDialog("Enrolled Id"); 
        if( id != null ){
            if( !id.equals("") ){
                String line; 
                int flag = 0;
                try {

                    File oldFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\user_package.txt");
                    File newFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\uptemp.txt");
                    if(!newFile.exists()){
                        newFile.createNewFile();
                    }

                    FileReader fr = new FileReader(user_package);
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
    
    public void enrollHotel(){
        String id = JOptionPane.showInputDialog("Hotel ID"); 

        if(id != null){
            if( !id.equals("") ){
                if(hotelExists(id)){
                    if(!checkHotelEnrolled(id)){
                        try{
                            int newid = this.getNewHotelId();

                            FileWriter myWriter = new FileWriter(user_hotel,true);
                            PrintWriter printWriter = new PrintWriter(myWriter);
                            printWriter.println(newid+" "+this.getLoggedInUser()+" "+id+"\n");
                            printWriter.close();

                            JOptionPane.showMessageDialog(null, "Successfully Enrolled! ","Confirmation", JOptionPane.PLAIN_MESSAGE);

                        }catch (IOException ep) {
                            System.out.println("ERROR 404! File-Not-Found");
                            ep.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Already Enrolled","Error", JOptionPane.WARNING_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Hotel","Error", JOptionPane.WARNING_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "Invalid Input","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean hotelExists(String id){
        String line;
        
        try {
            FileReader fr = new FileReader(hotelPath);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    String packageid = split[0];
                    if(id.equalsIgnoreCase(packageid)){
                        return true;
                    }
                }
                
            }
            fr.close();
            br.close();
            
            return false;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
        return false;
    }
    
    public boolean checkHotelEnrolled(String hotelID){
        String line;
        
        try {
            FileReader fr = new FileReader(user_hotel);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    String userid = split[1];
                    String packageid = split[2];
                    if(hotelID.equalsIgnoreCase(packageid) && this.getLoggedInUser().equalsIgnoreCase(userid)){
                        return true;
                    }
                }
                
            }
            fr.close();
            br.close();
            
            return false;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
	return false;
    }
    
    public int getNewHotelId(){
        
        String line;
        int id = 1;
        
        try {
            FileReader fr = new FileReader(user_hotel);
            BufferedReader br = new BufferedReader(fr);
            
            if (br.readLine() == null) {
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
    
    public void disEnrolledHotel(){
        String id = JOptionPane.showInputDialog("Enrolled Id"); 
        if( id != null ){
            if( !id.equals("") ){
                String line; 
                int flag = 0;
                try {
                    File oldFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\user_hotel.txt");
                    File newFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\uhtemp.txt");
                    if(!newFile.exists()){
                        newFile.createNewFile();
                    }

                    FileReader fr = new FileReader(user_hotel);
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
        }else if( e.getSource() == b12 ){
            this.dispose();
            
            Login l = new Login();
            l.setVisible(true);
        }else if( e.getSource() == b4 ){
            this.enrollPackage();  
        }else if( e.getSource() == b3 ){
            AllPackages alp = new AllPackages();
            alp.setVisible(true);
        }else if( e.getSource() == b5 ){
            UserEnrolledPackage uep = new UserEnrolledPackage(this.getLoggedInUser());
            uep.setVisible(true);
        }else if( e.getSource() == b6 ){
            this.disEnrolledPackage();
        }else if( e.getSource() == b8 ){
            this.enrollHotel();  
        }else if( e.getSource() == b7 ){
            AllHotels ap = new AllHotels();
            ap.setVisible(true);
        }else if( e.getSource() == b9 ){
            UserEnrolledHotel ueh = new UserEnrolledHotel(this.getLoggedInUser());
            ueh.setVisible(true);
        }else if( e.getSource() == b10 ){
            this.disEnrolledHotel();
        }else if( e.getSource() == b11 ){
            
        }
    }
}
