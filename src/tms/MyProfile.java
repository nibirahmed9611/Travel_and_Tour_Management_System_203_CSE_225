package tms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.*;


public class MyProfile extends JFrame implements ActionListener {
    
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\users.txt";
    
    private String loggedInUser;
    
    public String getLoggedInUser(){
        return this.loggedInUser;
    }
    
    public void setLoggedInUser(String loggedInUser){
        this.loggedInUser = loggedInUser;
    }

    public MyProfile( String user ){
        this.setLoggedInUser(user);
        
        initialize();     
        
        this.setTitle("My Profile | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(310, 100, 400, 400);
        this.setResizable(false);
    }
    
    JButton b1;
    
    public void initialize(){
        String line,firstName = "",lastName = "",fuserName = "",fuserEmail = "";
        
        if(this.getLoggedInUser().equalsIgnoreCase("-1")){
            firstName = "Admin";
            lastName = "Admin";
            fuserEmail = "Admin";
            fuserName = "Admin";
        }else{
            try {
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null){

                    if(line.contains(" ")) {
                        String[] split = line.split(" ");

                        if(split[0].equals(this.getLoggedInUser()) ){
                            firstName = split[1];
                            lastName = split[2];
                            fuserEmail = split[3];
                            fuserName = split[4];
                        }
                    } 
                }

                fr.close();
                br.close();

            }
            catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
            }

        }
        
        
        
        
        
        
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 400, 400);
        this.add(p1);
        
        JLabel t1 = new JLabel("First Name: ");
        t1.setForeground(Color.black);
        t1.setFont(new Font("Tahoma",Font.BOLD,16));
        t1.setBounds(20, 20, 100, 50);
        p1.add(t1);
        
        JLabel t2 = new JLabel(firstName);
        t2.setForeground(Color.black);
        t2.setFont(new Font("Tahoma",Font.PLAIN,16));
        t2.setBounds(120, 20, 250, 50);
        p1.add(t2);
        
        JLabel t3 = new JLabel("Last Name: ");
        t3.setForeground(Color.black);
        t3.setFont(new Font("Tahoma",Font.BOLD,16));
        t3.setBounds(20, 50, 100, 50);
        p1.add(t3);
        
        JLabel t4 = new JLabel(lastName);
        t4.setForeground(Color.black);
        t4.setFont(new Font("Tahoma",Font.PLAIN,16));
        t4.setBounds(120, 50, 250, 50);
        p1.add(t4);
        
        JLabel t5 = new JLabel("Email: ");
        t5.setForeground(Color.black);
        t5.setFont(new Font("Tahoma",Font.BOLD,16));
        t5.setBounds(20, 80, 100, 50);
        p1.add(t5);
        
        JLabel t6 = new JLabel(fuserEmail);
        t6.setForeground(Color.black);
        t6.setFont(new Font("Tahoma",Font.PLAIN,16));
        t6.setBounds(120, 80, 250, 50);
        p1.add(t6);
        
        JLabel t7 = new JLabel("Username: ");
        t7.setForeground(Color.black);
        t7.setFont(new Font("Tahoma",Font.BOLD,16));
        t7.setBounds(20, 110, 100, 50);
        p1.add(t7);
        
        JLabel t8 = new JLabel(fuserName);
        t8.setForeground(Color.black);
        t8.setFont(new Font("Tahoma",Font.PLAIN,16));
        t8.setBounds(120, 110, 250, 50);
        p1.add(t8);
        
        b1 = new JButton("Close");
        b1.setBounds(20, 190, 100, 25);
        b1.setBackground( new Color(234,29,98) );
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        p1.add(b1);
        
        

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() ==  b1 ){
            this.dispose();
        }
    }
    
}
