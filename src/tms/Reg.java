package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Reg extends JFrame implements ActionListener {
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\users.txt";
    
 
    JButton b1,b2;
    JTextField tf1, tf2, tf3, tf4;
    JPasswordField pf1;

    
    public Reg(){
        
//        System.out.println(this.path);
        
        initialize();     
        
        this.setTitle("Registration | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(310, 100, 800, 600);
        this.setResizable(false);
    }
    
    public void initialize(){
        JLabel bg;
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 800, 600);
        
        ImageIcon bgi = new ImageIcon(this.getClass().getResource("images/signup.jpg"));

        bg = new JLabel("",bgi,JLabel.CENTER);
        bg.setBounds(0, 0, 800, 600);
        
        this.add(p1);
        p1.add(bg);
        
        tf1 = new JTextField();
        tf1.setBounds(435,158,100,32);
        tf1.setToolTipText("First Name");
        tf1.setBorder(null);
        bg.add(tf1);
        
        tf2 = new JTextField();
        tf2.setBounds(562,158,140,32);
        tf2.setToolTipText("Last Name");
        tf2.setBorder(null);
        bg.add(tf2);
        
        tf3 = new JTextField();
        tf3.setBounds(436,229,267,32);
        tf3.setToolTipText("Email");
        tf3.setBorder(null);
        bg.add(tf3);
        
        tf4 = new JTextField();
        tf4.setBounds(436,300,267,32);
        tf4.setToolTipText("Username");
        tf4.setBorder(null);
        bg.add(tf4);
        
        pf1 = new JPasswordField();
        pf1.setBounds(436,370,267,32);
        pf1.setToolTipText("Password");
        pf1.setBorder(null);
        bg.add(pf1);
        
        b1 = new JButton("Register");
        b1.setBounds(433, 426, 277, 40);
        b1.setBackground(new Color(126,245,207));
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        bg.add(b1);
        
        b2 = new JButton("");
        b2.setBounds(173, 349, 94, 28);
        b2.setOpaque(false); // makes the button transparent
        b2.setContentAreaFilled(false); // content filled area set to null
        b2.setBackground(null);
        b2.setForeground(Color.black);
        b2.addActionListener(this);
        bg.add(b2);
        
    }
   
    @Override
    public void actionPerformed(ActionEvent e){

        if( e.getSource() == b2 ){
            this.dispose();
            
            Login l = new Login();
            l.setVisible(true);
        }else if( e.getSource() == b1 ){
            
            try{
                String firstName = !tf1.getText().equals("") ? tf1.getText().replace(" ", "+") : "null" ;
                String lastName = !tf2.getText().equals("") ? tf2.getText().replace(" ", "+") : "null";
                String email = !tf3.getText().equals("") ? tf3.getText().replace(" ", "+") : "null";
                String username = !tf4.getText().equals("") ? tf4.getText().trim().replace(" ", "+") : "null";
                String pass = String.valueOf(pf1.getPassword());
                if( pass.equals("") ){
                    JOptionPane.showMessageDialog(null, "Please Enter Your Password","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    pass = !pass.equals("") ? pass.replace(" ", "+") : "null";
                }
                
                int id = this.getNewId();
                
                if(!this.check(username)){
                    FileWriter myWriter = new FileWriter(path,true);
                    PrintWriter printWriter = new PrintWriter(myWriter);
                    printWriter.println(id+" "+firstName+" "+lastName+" "+email+" "+username+" "+pass+"\n");
                    printWriter.close();
                    
                    JOptionPane.showMessageDialog(null, "Successfully Registered! Please Login to continue...","Confirmation", JOptionPane.PLAIN_MESSAGE);

                    this.dispose();
                    Login l = new Login();
                    l.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Username already in use!","Error", JOptionPane.WARNING_MESSAGE);
                }
                
                
             }
            catch (IOException ep) {
                  System.out.println("ERROR 404! File-Not-Found");
                  ep.printStackTrace();
            }
        }
        
    }
    
    public boolean check(String username){
        String line;
        
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    if(username.equalsIgnoreCase(split[4])){
                        fr.close();
                        br.close();
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


