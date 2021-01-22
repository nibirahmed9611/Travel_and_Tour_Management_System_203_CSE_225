/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class AllHotels extends JFrame implements ActionListener{
    String path = System.getProperty("user.dir") + "\\src\\tms\\storefiles\\hotels.txt";

    AllHotels(){
        this.getAllHotels();
        initialize();     
        
        this.setTitle("All Hotels | Travel and Tour Management System");
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
        
        JLabel h1 = new JLabel("All Hotels");
        h1.setForeground(Color.black);
        h1.setFont(new Font("Tahoma",Font.BOLD,26));
        h1.setBounds(10, 15, 200, 50);
        h1.setHorizontalAlignment(JLabel.LEFT);
        p1.add(h1);
        
        b1 = new JButton("Close");
        b1.setBounds(765, 30, 100, 25);
        b1.setBackground( new Color(234,29,98) );
        b1.setForeground(Color.white);
        b1.addActionListener(this);
        p1.add(b1);
        
        String data[][]=getAllHotels();    
        String column[]={"ID","NAME","NO OF ROOMS","DATE","BUDGET"};         
        JTable jt=new JTable(data,column);    
        jt.setBounds(30,80,200,500);      
        jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
        jt.setFillsViewportHeight(true);
        jt.setRowHeight(30);
        JScrollPane sp = new JScrollPane(jt); 
        sp.setBounds(10,80,865,500);
                
        p1.add(sp);
        
        
    }
    
    public String[][] getAllHotels(){
        
        String line;
        
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            
            int linecount = 0;
            
            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
//                    System.out.println(line);
                    linecount++;
                }
            }
            
            
            fr.close();
            br.close();
            
//            fr = new FileReader(path);
//            br = new BufferedReader(fr);
//            
//            while ((line = br.readLine()) != null){
//
//                if(line.contains(" ")) {
//                    
//                    String[] split = line.split(" ");
//                    
//                    System.out.println(split[0]);
//                    System.out.println(split[1]);
//                    System.out.println(split[2]);
//                    System.out.println(split[3]);
//                    System.out.println(split[4]);
//                    System.out.println();
//                }
//            }
//            
//            fr.close();
//            br.close();
            
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            
            String[][] data = new String[linecount][5];
            int i = 0;
            while ((line = br.readLine()) != null){
                if(line.contains(" ")) {
                    String[] split  = line.split(" ");
                    String id       = split[0];
                    String name     = split[1].replace("+", " ");
                    String place    = split[2].replace("+", " ");
                    String date     = split[3].replace("+", " ");
                    String budget   = split[4].replace("+", " ");
                                        
                    data[i][0] = id;
                    data[i][1] = name;
                    data[i][2] = place;
                    data[i][3] = date;
                    data[i][4] = budget; 
                    i++;
                }
            }
            
            fr.close();
            br.close();
                        
            return data;
        }
        catch (Exception ep) {
                System.out.println("ERROR 404! File-Not-Found");
                ep.printStackTrace();
        }
        String[][] defaultdata = new String[0][0];
        return defaultdata;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == b1 ){
            this.dispose();
        }
    }
}
