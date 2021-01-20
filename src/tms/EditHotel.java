package tms;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import static java.lang.System.exit;
import javax.swing.*;

/**
 *
 * @author Nibir
 */
public class EditHotel extends JFrame implements ActionListener{
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt";

    public String id;
    
    EditHotel(String id){        
        this.id = id;
        
//        System.out.println(id);
        
        initialize();     
        
        this.setTitle("Edit Hotel | Travel and Tour Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(400, 150, 400, 400);
        this.setResizable(false);
    }
    
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton b1,b2;
    
    public void initialize(){
        
        String line;
        int flag = 0;
        String name ="", rooms="", date="", budget="";
            
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    if(line.split(" ")[0].equals(this.id)){
                        name = split[1].replace("+", " ");
                        rooms = split[2].replace("+", " ");
                        date = split[3].replace("+", " ");
                        budget = split[4].replace("+", " ");
                        flag++;
                    }
                }
            }
            fr.close();
            br.close();
            
            if( flag == 0 ){
                JOptionPane.showMessageDialog(null, "No data found","Error", JOptionPane.ERROR_MESSAGE);      
            }
        }
        catch (Exception ep) {
            System.out.println("ERROR 404! File-Not-Found");
            ep.printStackTrace();
        }
                
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0, 0, 400, 400);
        this.add(p1);
        
        JLabel h1 = new JLabel("Edit Hotel");
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
        
        tf1 = new JTextField(name);
        tf1.setBounds(160,95,170,25);
        tf1.setToolTipText("Name of Hotel");
        p1.add(tf1);
        
        JLabel t2 = new JLabel("No of rooms: ");
        t2.setForeground(Color.black);
        t2.setFont(new Font("Tahoma",Font.BOLD,14));
        t2.setBounds(20, 120, 150, 50);
        p1.add(t2);
        
        tf2 = new JTextField(rooms);
        tf2.setBounds(160,135,170,25);
        tf2.setToolTipText("No of rooms");
        p1.add(tf2);
        
        JLabel t3 = new JLabel("Date: ");
        t3.setForeground(Color.black);
        t3.setFont(new Font("Tahoma",Font.BOLD,14));
        t3.setBounds(20, 160, 150, 50);
        p1.add(t3);
        
        tf3 = new JTextField(date);
        tf3.setBounds(160,175,170,25);
        tf3.setToolTipText("Date");
        p1.add(tf3);
        
        JLabel t4 = new JLabel("Budget: ");
        t4.setForeground(Color.black);
        t4.setFont(new Font("Tahoma",Font.BOLD,14));
        t4.setBounds(20, 200, 150, 50);
        p1.add(t4);
        
        tf4 = new JTextField(budget);
        tf4.setBounds(160,215,170,25);
        tf4.setToolTipText("Budget");
        p1.add(tf4);
        
        b1 = new JButton("Cancel");
        b1.setBounds(20, 280, 100, 25);
        b1.setBackground( new Color(234,29,98) );
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        p1.add(b1);
        
        b2 = new JButton("Update");
        b2.setBounds(228, 280, 100, 25);
        b2.setBackground( new Color(81,181,110) );
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        p1.add(b2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == b1 ){
            this.dispose();
        }else if(e.getSource() == b2){
            this.update();
        }
    }
    
    public void update(){
        String line, newLine;
                
        try {
            
            File oldFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt");
            File newFile = new File(System.getProperty("user.dir") + "\\src\\tms\\storefiles\\htemp.txt");
            
            if(!newFile.exists()){
                newFile.createNewFile();
            }
            
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null){

                if(line.contains(" ")) {
                    String[] split = line.split(" ");
                    if(id.equalsIgnoreCase(split[0])){
                        String name = !tf1.getText().equals("") ? tf1.getText().replace(" ", "+") : "null" ;
                        String rooms = !tf2.getText().equals("") ? tf2.getText().replace(" ", "+") : "null";
                        String date = !tf3.getText().equals("") ? tf3.getText().replace(" ", "+") : "null";
                        String budget = !tf4.getText().equals("") ? tf4.getText().trim().replace(" ", "+") : "null";
                        
                        String updatedLine = id+" "+name+" "+rooms+" "+date+" "+budget+"\n";
                        newLine = line.replaceAll(line, updatedLine);
                        
                        FileWriter myWriter = new FileWriter(newFile.getAbsolutePath(),true);
                        PrintWriter printWriter = new PrintWriter(myWriter);
                        printWriter.println(updatedLine);
                        printWriter.close();
                        
                    }else{
                        FileWriter myWriter = new FileWriter(newFile.getAbsolutePath(),true);
                        PrintWriter printWriter = new PrintWriter(myWriter);
                        printWriter.println(line);
                        printWriter.close();
                    }
                }
            }
            
            fr.close();
            br.close();
//            System.gc();

            oldFile.delete();
            newFile.renameTo(oldFile);
            
            JOptionPane.showMessageDialog(null, "Successfully Updated","Success", JOptionPane.PLAIN_MESSAGE);
            this.dispose();

        }
        catch (Exception ep) {
            System.out.println("ERROR 404! File-Not-Found");
            ep.printStackTrace();
        }
    }
}
