package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;


public class Login extends JFrame implements ActionListener {
//    String p = Login.class.getName();
    
    public String userId;
    
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\users.txt";
    
    public static void main(String[] args) {
        
    }
    
    public Login(){       
        initialize();     
        
        this.setTitle("Login | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(300, 100, 850, 540);
        this.setResizable(false);
    }
    
    JButton b2;
    JButton b1;
    
    JTextField tf1;
    JPasswordField pf1;
    
    public void initialize(){
        JLabel bg; // initializing the background variable
        
        JPanel p1 = new JPanel(); // adding the panel to the frame
        p1.setLayout(null);
        p1.setBounds(0, 0, 850, 540);
        
        ImageIcon bgi = new ImageIcon(this.getClass().getResource("images/login.jpg")); // taking the image

        bg = new JLabel("",bgi,JLabel.CENTER); // adding the image to a JLabel as background
        bg.setBounds(0, 0, 850, 540);
        
        this.add(p1); // adding the panel to frame
        p1.add(bg); // adding the background to panel
        
        tf1 = new JTextField();
        tf1.setBounds(285,167,275,30);
        tf1.setToolTipText("Username");
        bg.add(tf1);
        
        pf1 = new JPasswordField();
        pf1.setBounds(285,229,275,30);
        pf1.setToolTipText("Password");
        bg.add(pf1);
        
        b1 = new JButton("Login");
        b1.setBounds(285, 319, 275, 30);
        b1.setBackground(new Color(234,29,98));
        b1.setForeground(Color.white);
//        b1.setFont(new Font("Tahoma",Font.BOLD,14));
        b1.addActionListener(this);
        bg.add(b1);
        
        JLabel t1 = new JLabel("or");
        t1.setForeground(Color.white);
        t1.setFont(new Font("Tahoma",Font.BOLD,16));
        t1.setBounds(365, 360, 100, 50);
        t1.setHorizontalAlignment(JLabel.CENTER);
        bg.add(t1);
        
        b2 = new JButton("Signup");
        b2.setBounds(330, 419, 175, 30);
        b2.setBackground(new Color(81,181,110));
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        bg.add(b2);
        
        JLabel ft = new JLabel("Designed and Developed by Nibir Ahmed and Maliha Anzum Maisha");
        ft.setForeground(Color.white);
        ft.setFont(new Font("Tahoma",Font.BOLD,10));
        ft.setBounds(10, 460, 500, 50);
//        ft.setHorizontalAlignment(JLabel.CENTER);
        bg.add(ft);
        
        JLabel docu = new JLabel("Admin username : admin");
        docu.setForeground(Color.white);
        docu.setFont(new Font("Tahoma",Font.BOLD,10));
        docu.setBounds(700, 5, 500, 50);
//        ft.setHorizontalAlignment(JLabel.CENTER);
        bg.add(docu);
        
        JLabel docp = new JLabel("Admin password : admin");
        docp.setForeground(Color.white);
        docp.setFont(new Font("Tahoma",Font.BOLD,10));
        docp.setBounds(700, 15, 500, 50);
//        ft.setHorizontalAlignment(JLabel.CENTER);
        bg.add(docp);
        
        JLabel docx = new JLabel("Users have to register");
        docx.setForeground(Color.white);
        docx.setFont(new Font("Tahoma",Font.BOLD,10));
        docx.setBounds(700, 25, 500, 50);
//        ft.setHorizontalAlignment(JLabel.CENTER);
        bg.add(docx);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            try{
                String username = tf1.getText();
                if( username.equals("") ){
                    JOptionPane.showMessageDialog(null, "Please Enter Your Username","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    username = username.replace(" ", "+");
                }
                String pass = String.valueOf(pf1.getPassword());
                if( pass.equals("") ){
                    JOptionPane.showMessageDialog(null, "Please Enter Your Password","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }else{
                    pass = pass.replace(" ", "+");
                }
                
                
                
                if(this.checkCredentials(username, pass) == 2){
                    this.dispose();
                    
                    UserDashboard d = new UserDashboard(this.userId);
                    d.setVisible(true);
                }else if(this.checkCredentials(username, pass) == 1){
                    this.dispose();
                    
                    Dashboard d = new Dashboard(this.userId);
                    d.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Username Pass not matched!","Error", JOptionPane.ERROR_MESSAGE);
                }  
            }
            catch (Exception ep) {
                  System.out.println("ERROR 404! File-Not-Found");
                  ep.printStackTrace();
            }
        }else if(e.getSource() == b2){
            this.dispose();
            
            Reg reg = new Reg();
            reg.setVisible(true);
        }
    }
    
    public int checkCredentials(String username, String pass){
    
        if(username.equals("admin") && pass.equals("admin")){
            this.userId = "-1";
            return 1;
        }
        
        int u=0;
        String line,fusername,fpass, userID;
        
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            while ((line = br.readLine()) != null){
                
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    fusername = split[4];
                    fpass = split[5];
                    userID = split[0];
                    
                    if(username.equals(fusername) && pass.equals(fpass) ){
                        this.userId = userID;
                        return 2;
                    }
                } 
            }
            fr.close();
            br.close();
            return 0;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
	return 0;
    }
    

}


