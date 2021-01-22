package tms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


/**
 *
 * @author Nibir
 */
public class UserEnrolledPackage extends JFrame implements ActionListener{
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\packages.txt";
    String hotelPath = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt";
    String user_package = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\user_package.txt";
    String user_hotel = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\user_hotel.txt";
    String userpath = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\users.txt";
    
    String userId;

    UserEnrolledPackage(String userId){
        this.userId = userId;
//        this.getAllPackages();
        initialize();     
        
        this.setTitle("User Enrolled Package | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(400, 150, 900, 600);
        this.setResizable(false);
    }
    JButton b1;
    public void initialize(){
    
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 900, 600);
        this.add(p1);
        
        JLabel h1 = new JLabel("All Packages Enrolled");
        h1.setForeground(Color.black);
        h1.setFont(new Font("Tahoma",Font.BOLD,26));
        h1.setBounds(10, 15, 300, 50);
        h1.setHorizontalAlignment(JLabel.LEFT);
        p1.add(h1);
        
        b1 = new JButton("Close");
        b1.setBounds(765, 30, 100, 25);
        b1.setBackground( new Color(234,29,98) );
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        p1.add(b1);
        
        String data[][] = getAllPackages();    
        String column[] = {"ID","USER NAME","PACKAGE NAME",};
        
        JTable jt = new JTable(data,column);    
        jt.setBounds(30,80,200,500);      
        jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
        jt.setFillsViewportHeight(true);
        jt.setRowHeight(30);
        
        JScrollPane sp = new JScrollPane(jt); 
        sp.setBounds(10,80,865,500);
                
        p1.add(sp);
         
    }
    
    public String[][] getAllPackages(){
        
        String line;
        
        try {
            FileReader fr = new FileReader(user_package);
            BufferedReader br = new BufferedReader(fr);
            
            int linecount = 0;
            
            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
                    String[] split  = line.split(" ");
                    String idFromLine = split[1];
                    if( this.userId.equalsIgnoreCase("-1") ){
                        linecount++;
                    }else{
                        if( this.userId.equalsIgnoreCase(idFromLine) ){
                            linecount++;
                        }
                    }
                    
                }
            }
            
            
            fr.close();
            br.close();
                        
            fr = new FileReader(user_package);
            br = new BufferedReader(fr);
            
            String[][] data = new String[linecount][3];
            int i = 0;
            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
                    
                    String[] split  = line.split(" ");
                    if(this.userId.equalsIgnoreCase("-1")){
                        String id       = split[0];
                        String name     = this.getUser(split[1]).replace("+", " ");
                        String place    = this.getPackage(split[2]).replace("+", " ");

                        data[i][0] = id;
                        data[i][1] = name;
                        data[i][2] = place;
                        i++;
                    }else{
                        if(split[1].equalsIgnoreCase(this.userId)){
                            String id       = split[0];
                            String name     = this.getUser(split[1]).replace("+", " ");
                            String place    = this.getPackage(split[2]).replace("+", " ");

                            data[i][0] = id;
                            data[i][1] = name;
                            data[i][2] = place;
                            i++;
                        }
                    }
                    
                    
                }
            }
            
            fr.close();
            br.close();
            
//            System.out.println("Ekhane Shesh");
            
            return data;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
        String[][] defaultdata = new String[0][0];
        return defaultdata;
    }

    public String getPackage(String id){
        String line;
        int flag = 0;
        String name ="", place="", date="", budget="";
            
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    if(id.equalsIgnoreCase(split[0])){
                        fr.close();
                        br.close();
                        return split[1];
                    }
                }
            }
            
            fr.close();
            br.close();
            
            return "null";
            
        }
        catch (Exception ep) {
            System.out.println("ERROR 404! File-Not-Found");
            ep.printStackTrace();
        }
        
        return "null";
    }
    
    public String getUser(String userID){
        String line;
        try {
            FileReader fr = new FileReader(userpath);
            BufferedReader br = new BufferedReader(fr);
            
            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");

                    if(split[0].equals(userID) ){
                        fr.close();
                        br.close();
                        return split[1];
                    }
                } 
            }
            
            fr.close();
            br.close();
            
            return "null";
            
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
        
        return "null";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == b1 ){
            this.dispose();
        }
    }
}
