package dbms;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class Frame2 extends JFrame implements ActionListener{
    static JTextArea screen1;
    JButton submit,close;
    static JTextField f9,f5;
    static JLabel pic;
    static String mara;
public Frame2(){
    screen1 = new JTextArea();
    screen1.setBounds(30, 30,350, 180);
    add(screen1);
   String x =mara; 
   ImageIcon meraa = new ImageIcon(new ImageIcon(x).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH ));
   pic = new JLabel(meraa);
   pic.setBounds(50, 300,100, 100);
   add(pic);
  
    //screen1.setText("Hello");
    
    Font myfont1 = new Font("Times New Roman",Font.BOLD,16);
    Font myfont = new Font("Times New Roman",Font.BOLD,14);

    Label l=new Label("RECEIPT INFORMATION");
    Label l5=new Label("NAME");
    Label l6=new Label("CONTACT");
    Label l7=new Label("CNIC No.");
    Label l8=new Label("QUANITITY");
    Label l9=new Label("PRICE");
    JTextField f6=new JTextField();
    JTextField f7=new JTextField();
    JTextField f8=new JTextField();
    f5=new JTextField();
    f9=new JTextField();
    submit=new JButton("Submit");
    close=new JButton("Close");

    l.setBounds(70,50,250, 30); 
    l5.setBounds(40,95,70, 30); 
    l6.setBounds(40, 145, 70, 30);
    l7.setBounds(40,195,70, 30);
    l8.setBounds(40,245,90, 30);
    l9.setBounds(40,295,70, 30);
    f5.setBounds(140,95,150, 30);
    f6.setBounds(140, 145, 150, 30);
    f7.setBounds(140, 195, 150, 30);
    f8.setBounds(140,245,150, 30);
    f9.setBounds(140,295,150, 30);
    submit.setBounds(230,385,100, 30);
    close.setBounds(230,350,100, 30);

    l.setFont(myfont1);
    l5.setFont(myfont);
    l6.setFont(myfont);
    l7.setFont(myfont);
    l8.setFont(myfont);
    l9.setFont(myfont);
    submit.setFont(myfont);
    close.setFont(myfont);


//    add(l);
 //   add(l5);
  //  add(l6);
  //  add(l7);
   // add(l8);
  //  add(l9);
 //   add(f5);
  //  add(f6);    
  ///  add(f7);
   // add(f8);
  //  add(f9);
 //   add(submit);
      add(close);

    submit.addActionListener(this);
    Image image=Toolkit.getDefaultToolkit().getImage("C:\\IBA.JPG");
    close.addActionListener(new ActionListener (){
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            System.exit(0);}});

    setSize(800,700);
    setLayout(null);
    setTitle("personal information");
    setVisible(true);
    setIconImage(image);
    setResizable(false);

}

public void actionPerformed(ActionEvent e){
    if(e.getSource()== submit){
        JOptionPane.showMessageDialog(this,"ThankYou "+f5.getText()+", for Shopping");System.exit(0);}}
}
 

