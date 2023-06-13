package dbms;
//these package for email
import java.util.Properties;
import java.util.Date;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// these package for frame

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

class NewClass extends JFrame implements ActionListener{
    JButton submit,close;
    static JTextField f9,f10,f11,f5,f6,f7,f8;
    String getName,getEmail,getQuantity;
    
    public NewClass(){
    Font myfont1 = new Font("Times New Roman",Font.BOLD,16);
    Font myfont = new Font("Times New Roman",Font.BOLD,14);

    Label l=new Label("RECEIPT INFORMATION");
    Label l5=new Label("NAME");
    Label l6=new Label("CONTACT");
    Label l7=new Label("Email");
    Label l8=new Label("QUANITITY");
    Label l9=new Label("Address");
    f6=new JTextField(11);
    f7=new JTextField(11);
    f8=new JTextField(11);
    f5=new JTextField(11);
    f9=new JTextField();
    f10=new JTextField();// temperary for get the price 
    f11=new JTextField();// temperary for get the name
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
    f10.setBounds(140,295,150, 30);// temperary for get the price 
    f11.setBounds(140,295,150, 30);// temperary for get the name
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
    

    add(l);
    add(l5);
    add(l6);
    add(l7);
    add(l8);
    add(l9);
    add(f5);
    add(f6);    
    add(f7);
    add(f8);
    add(f9);
    add(submit);
    add(close);

    
    
    submit.addActionListener(this);
    Image image=Toolkit.getDefaultToolkit().getImage("D:\\logo1.png");//logo image on 2nd frame
    close.addActionListener(new ActionListener (){public void actionPerformed(ActionEvent e){System.exit(0);}});

    JLabel background;
    ImageIcon img = new ImageIcon("D:\\logo1.JPG");
    background = new JLabel("",img,0);
    Dimension size = background.getPreferredSize();
    add(background);
    
    Image Icon = Toolkit.getDefaultToolkit().getImage("D:\\logo1.png");    
    setIconImage(Icon);
    
      
    setSize(350,450);
    setLayout(null);
    setTitle("personal information");
    setVisible(true);
    setIconImage(image);
    setResizable(false);
    
    
}
      
public void actionPerformed(ActionEvent e) {

    //Action on submit button
    if (e.getSource() == submit) {
        setVisible(false);// hide 2nd frame

        String Pri = f10.getText();
        String Qua = f8.getText();
        Random r = new Random();

        // Variable for DataBase
        int money = Integer.parseInt(Pri) * Integer.parseInt(Qua); //quantity * price
        int result = r.nextInt(1000); // random for order number
        String D_Name = f5.getText();
        String D_contact = f6.getText();
        String D_product = f11.getText();
        String D_quantity = f8.getText();
        String D_address = f9.getText();
        String ee = f7.getText(); // email of RecipientType

        try {
            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/shop?characterEncoding=latin1&useConfigs=maxPerformance";
            Connection con = DriverManager.getConnection(url, "root", "123456");

         
            // Prepare the SQL statement
       String sql = "INSERT INTO customer_info (price, tracking_id, Name, contact, product, quantity, address, order_date, order_time) VALUES "
                    + "('" + money + " pkr', '" + result + "', '" + D_Name + "', '" + D_contact + "', '" + D_product + "', '" + D_quantity + "', '" + D_address + "', CURDATE(), CURTIME())";
       System.out.println("SQL query: " + sql);

            // Execute the SQL statement
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(sql);
            System.out.println(rows + " row(s) affected");

            // Close the database connection
            con.close();
            System.out.println("Database connection closed");

            System.out.println("New record added successfully");

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    

        
        // email description  
        String EmailData="Dear "+D_Name+"\nYour order has been confirmed!\nThank you for choosing our store.\n\n" +
        "Your order details:"+"\nOrder Number: #"+result+"\nContact: "+D_contact+"\nProduct Name: "+
        D_product+"\nQuantity: "+D_quantity+"\nTotal Price: "+money+"/- PKR\nAddress: "+D_address+
        "\n\nWe have started processing your order and it will ship within 3-5 days. Contact us with any questions. \n" +
        "Thank you once again for choosing our store.\n\n"+"Best regards,\n\n"+
        "Tushar Kanjwani\n"+"Alpha SmartPhones";
        
        sendMail(ee, "Order Confirmation", EmailData);// send email
        //last thankyou message show on screen
        JOptionPane.showMessageDialog(this,"ThankYou "+f5.getText()+", for Shopping","Congtratulation",JOptionPane.INFORMATION_MESSAGE);System.exit(0);
        
    }}

//this code is for email
 static Properties properties = new Properties();
 static {
  properties.put("mail.smtp.host", "smtp.gmail.com");
  properties.put("mail.smtp.port", "587");
  properties.put("mail.smtp.auth", "true");
  properties.put("mail.smtp.starttls.enable", "true");
 }

 public static void sendMail(String email, String subject, String text) {
  String returnStatement = null;
  try {
   Authenticator auth = new Authenticator() {
    public PasswordAuthentication getPasswordAuthentication() {
     //return new PasswordAuthentication("email ", "password");
     return new PasswordAuthentication("digitasmartphoneshop@gmail.com", "rizshwhfpdofsqgz");
    }
   };
   Session session = Session.getInstance(properties, auth);
   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress("digitasmartphoneshop@gmail.com"));
   message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
   message.setSentDate(new Date());
   message.setSubject(subject);
   message.setText(text);
   returnStatement = "The e-mail was sent successfully";
   System.out.println(returnStatement);
   Transport.send(message);
  } catch (Exception e) {
   returnStatement = "Error in sending mail";
   e.printStackTrace();
  }
 }

}

class shop extends JFrame implements ActionListener{
  //JFrame f= new JFrame("CheckBox Example");
    JButton bPixel,bIphone,bSamsung,Buy,Cancel,cart;
    JTextArea screen;
    JRadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15;
    JLabel lab,lab1,lab2,lab3,lab4,LOGO,Price;
    String x;

 
    public shop(){
        String BRANDS[]={"BRANDS","Google Pixel","Iphone","Samsung",};        
        JComboBox combobox=new JComboBox(BRANDS);    
        
        
        //JButton aa=new JButton("BRANDS");    
         SpinnerModel value =new SpinnerNumberModel(5,0,10,1); //initial value  
        JSpinner spinner = new JSpinner(value);   
            spinner.setBounds(750,250,100,30);    
            add(spinner);  
            
            //aa.setBounds(230,350,100, 30);
           // add(aa);
            //aa.addActionListener(new ActionListener (){
        // public void actionPerformed(ActionEvent e){
  // System.out.println("Value"+spinner.getValue()); }});
              JPanel panel = new JPanel();
              Object[][] data = {{"Name", "Age", "Gender"},{"John", 25, "Male"},{"Jane", 30, "Female"},{"Bob", 40, "Male"},{"Alice", 20, "Female"}};
        
        // Column names for the table
        String[] columnNames = {" "," "," "};
        // Create the JTable
        JTable table = new JTable(data, columnNames);
        table.setBounds(950,300, 300, 100);
        add(panel);
        add(table);
        
  
  
            
    ButtonGroup bg=new ButtonGroup();  

    Font Fname = new Font("Times New Roman",Font.BOLD,40);
    Font Fbutton = new Font("Times New Roman",Font.ROMAN_BASELINE,15);
    Font Fname1 = new Font("Times New Roman",Font.BOLD,23);
    Font Fdetail = new Font("Times New Roman",Font.ROMAN_BASELINE,20);
    Font Fprice = new Font("Times New Roman",Font.BOLD,15);
    Font Bfont = new Font("Times New Roman",Font.BOLD,15);

    JLabel Mname = new JLabel("SPECIFICATIONS");
    JLabel name = new JLabel("Alpha SmartPhones");
    lab = new JLabel("");
    LOGO = new JLabel("");
    lab1 = new JLabel("");
    lab2 = new JLabel("");
    lab3 = new JLabel("");
    lab4 = new JLabel("");
    Price = new JLabel("");

    bPixel = new JButton("Pixel");
    bIphone = new JButton("Iphone");
    bSamsung = new JButton("Samsung");
    Buy = new JButton("Buy");
    Cancel = new JButton("Close");
    cart = new JButton("Cart");

    screen = new JTextArea();
    
    //Product(radio Buttons) Name 
    String PName[]={"Pixel 7","Pixel 6","Pixel 5","Pixel 4","Pixel 3",
                 "I phone 14","I phone 13","I phone 12","I phone 11","I phone x",
                 "Galaxy S10","Galaxy S9","Galaxy J5","Galaxy J2","Galaxy A53" };
    //show radio button 
    r1=new JRadioButton(PName[0]);    
    r2=new JRadioButton(PName[1]); 
    r3=new JRadioButton(PName[2]); 
    r4=new JRadioButton(PName[3]); 
    r5=new JRadioButton(PName[4]); 
    r6=new JRadioButton(PName[5]);    
    r7=new JRadioButton(PName[6]); 
    r8=new JRadioButton(PName[7]); 
    r9=new JRadioButton(PName[8]); 
    r10=new JRadioButton(PName[9]); 
    r11=new JRadioButton(PName[10]);    
    r12=new JRadioButton(PName[11]); 
    r13=new JRadioButton(PName[12]); 
    r14=new JRadioButton(PName[13]); 
    r15=new JRadioButton(PName[14]); 
    
    // set bounds of buttons,labels textfield
    name.setBounds(500, 40, 400, 50);
    Mname.setBounds(50, 290, 300, 30);
    combobox.setBounds(50,130,100,30);    
    
  //  bPixel.setBounds(50,100,100,30);
  //  bIphone.setBounds(250,100,100,30);
  //  bSamsung.setBounds(450,100,100,30);
    Buy.setBounds(1130,590,100,30);
    Cancel.setBounds(900,590,100,30);
    cart.setBounds(1015,590,100,30);
    Price.setBounds(1015,550,150,30);
    screen.setBounds(210, 320, 350, 180);
    lab.setBounds (245,120, 70, 70);
    lab1.setBounds(375,120, 70, 70);
    LOGO.setBounds(1000,120, 70, 70);
    lab2.setBounds(505,120, 70, 70);
    lab3.setBounds(635,120, 70, 70);
    lab4.setBounds(765,120, 70, 70);
    r1.setBounds(230,190,100,30);    
    r2.setBounds(360,190,100,30);
    r3.setBounds(490,190,100,30);   
    r4.setBounds(620,190,100,30);   
    r5.setBounds(750,190,100,30);      
    r6.setBounds(230,190,100,30);    
    r7.setBounds(360,190,100,30);
    r8.setBounds(490,190,100,30);   
    r9.setBounds(620,190,100,30);   
    r10.setBounds(750,190,100,30);      
    r11.setBounds(230,190,100,30);    
    r12.setBounds(360,190,100,30);
    r13.setBounds(490,190,100,30);   
    r14.setBounds(620,190,100,30);   
    r15.setBounds(750,190,100,30);      
    
    //set font of buttons ,labels textfield.
    name.setFont(Fname);
    Mname.setFont(Fname1);
    bPixel.setFont(Bfont);
    bIphone.setFont(Bfont);
    bSamsung.setFont(Bfont);
    Buy.setFont(Fbutton);
    Cancel.setFont(Fbutton);
    cart.setFont(Fbutton);
    Price.setFont(Fprice);
    screen.setFont(Fdetail);

    r1.setFont(Bfont);
    r2.setFont(Bfont);
    r3.setFont(Bfont);
    r4.setFont(Bfont);
    r5.setFont(Bfont);
    r6.setFont(Bfont);
    r7.setFont(Bfont);
    r8.setFont(Bfont);
    r9.setFont(Bfont);
    r10.setFont(Bfont);
    r11.setFont(Bfont);
    r12.setFont(Bfont);
    r13.setFont(Bfont);
    r14.setFont(Bfont);
    r15.setFont(Bfont);
    
    // show buttons labels textfield
    add(name);
    add(Mname);
    add(combobox);  
    add(bPixel);
    add(bIphone);
    add(bSamsung);
    add(Buy);
    add(Cancel);
    add(cart);
    add(Price);
    add(screen);
    add(lab);
    add(lab1);
    add(LOGO);
    add(lab2);
    add(lab3);
    add(lab4);
    add(r1);
    add(r2);
    add(r3);
    add(r4);
    add(r5);   
    add(r6);
    add(r7); 
    add(r8);
    add(r9);
    add(r10); 
    add(r11);
    add(r12); 
    add(r13);
    add(r14);
    add(r15); 
    
    bg.add(r1);
    bg.add(r2); 
    bg.add(r3);
    bg.add(r4);
    bg.add(r5);   
    bg.add(r6);
    bg.add(r7); 
    bg.add(r8);
    bg.add(r9);
    bg.add(r10); 
    bg.add(r11);
    bg.add(r12); 
    bg.add(r13);
    bg.add(r14);
    bg.add(r15); 

    //add actionlistener on buttons labels textfields
    r1.addActionListener(this);
    r2.addActionListener(this);
    r3.addActionListener(this);
    r4.addActionListener(this);
    r5.addActionListener(this);
    r6.addActionListener(this);
    r7.addActionListener(this);
    r8.addActionListener(this);
    r9.addActionListener(this);
    r10.addActionListener(this);
    r11.addActionListener(this);
    r12.addActionListener(this);
    r13.addActionListener(this);
    r14.addActionListener(this);
    r15.addActionListener(this);
    
    // for remove background of radiobuttons 
    r1.setOpaque(false);
    r2.setOpaque(false);
    r3.setOpaque(false);
    r4.setOpaque(false);
    r5.setOpaque(false);
    r6.setOpaque(false);
    r7.setOpaque(false);
    r8.setOpaque(false);
    r9.setOpaque(false);
    r10.setOpaque(false);
    r11.setOpaque(false);
    r12.setOpaque(false);
    r13.setOpaque(false);
    r14.setOpaque(false);
    r15.setOpaque(false);
    screen.setOpaque(false);
    lab.setOpaque(false);
    r1.setContentAreaFilled(false);
    r2.setContentAreaFilled(false);
    r3.setContentAreaFilled(false);
    r4.setContentAreaFilled(false);
    r5.setContentAreaFilled(false);
    r6.setContentAreaFilled(false);
    r7.setContentAreaFilled(false);
    r8.setContentAreaFilled(false);
    r9.setContentAreaFilled(false);
    r10.setContentAreaFilled(false);
    r11.setContentAreaFilled(false);
    r12.setContentAreaFilled(false);
    r13.setContentAreaFilled(false);
    r14.setContentAreaFilled(false);
    r15.setContentAreaFilled(false);
    r1.setBorderPainted(false);
    r2.setBorderPainted(false);
    r3.setBorderPainted(false);
    r4.setBorderPainted(false);
    r5.setBorderPainted(false);
    r6.setBorderPainted(false);
    r7.setBorderPainted(false);
    r8.setBorderPainted(false);
    r9.setBorderPainted(false);
    r10.setBorderPainted(false);
    r11.setBorderPainted(false);
    r12.setBorderPainted(false);
    r13.setBorderPainted(false);
    r14.setBorderPainted(false);
    r15.setBorderPainted(false);
    
 
    // background image
    JLabel background;
    ImageIcon img = new ImageIcon("D:\\.png");
    background = new JLabel("",img,0);
    Dimension size = background.getPreferredSize();
    background.setBounds(0, 0, size.width, size.height);
    add(background);

    //logo image
    ImageIcon Ilogo = new ImageIcon(new ImageIcon("D:\\SLOGO.png").getImage().getScaledInstance(300, 300, Image.SCALE_AREA_AVERAGING));
    ImageIcon imageIcon = new ImageIcon(new ImageIcon("D:\\logo1.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    //ImageIcon imageIcon = new (new ImageIcon("D:\\Innovaters.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
    LOGO.setIcon(Ilogo);
    lab.setIcon(imageIcon);
    //ImageIcon Pimage7 = new ImageIcon("D:\\logo1.png");
   // lab.setIcon(imageIcon);
    LOGO.setIcon(imageIcon);
    lab1.setIcon(imageIcon);
    lab2.setIcon(imageIcon);
    lab3.setIcon(imageIcon);
    lab4.setIcon(imageIcon);
    Image icon = Toolkit.getDefaultToolkit().getImage("D:\\logo1.png");    
    setIconImage(icon);
    
        


//Main Frame details
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLayout(null);
    setTitle("Mobile Shop");
    setVisible(true);
 
    //action on buy button
    Buy.addActionListener(new ActionListener (){
         public void actionPerformed(ActionEvent e){
            setVisible(false);
            NewClass  info = new NewClass();
            
            //when radio button select set name and price on temp textfield (f9, f10)
            if(r1.isSelected()){NewClass.f11.setText(PName[0]);NewClass.f10.setText(x);}
            if(r2.isSelected()){NewClass.f11.setText(PName[1]);NewClass.f10.setText(x);}
            if(r3.isSelected()){NewClass.f11.setText(PName[2]);NewClass.f10.setText(x);}
            if(r4.isSelected()){NewClass.f11.setText(PName[3]);NewClass.f10.setText(x);}
            if(r5.isSelected()){NewClass.f11.setText(PName[4]);NewClass.f10.setText(x);}
            if(r6.isSelected()){NewClass.f11.setText(PName[5]);NewClass.f10.setText(x);}
            if(r7.isSelected()){NewClass.f11.setText(PName[6]);NewClass.f10.setText(x);}
            if(r8.isSelected()){NewClass.f11.setText(PName[7]);NewClass.f10.setText(x);}
            if(r9.isSelected()){NewClass.f11.setText(PName[8]);NewClass.f10.setText(x);}
            if(r10.isSelected()){NewClass.f11.setText(PName[9]);NewClass.f10.setText(x);}
            if(r11.isSelected()){NewClass.f11.setText(PName[10]);NewClass.f10.setText(x);}
            if(r12.isSelected()){NewClass.f11.setText(PName[11]);NewClass.f10.setText(x);}
            if(r13.isSelected()){NewClass.f11.setText(PName[12]);NewClass.f10.setText(x);}
            if(r14.isSelected()){NewClass.f11.setText(PName[13]);NewClass.f10.setText(x);}
            if(r15.isSelected()){NewClass.f11.setText(PName[14]);NewClass.f10.setText(x);}}});
    
//action on canceln button
    Cancel.addActionListener(new ActionListener (){public void actionPerformed(ActionEvent e){System.exit(0);}});
    addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}});
 }
   
public void actionPerformed(ActionEvent e) {   
    //show specifiaction of all mobilephones and also image when selected
    if(r1.isSelected()){    
    screen.setText("\n               Ram                      8GB\n"
    +"               Memory                256GB\n"
    +"               Battery                  4355mAh\n"
    +"               Front Camera   \t10MP\n"
    +"               Main Camera   \t50MP \n");
    ImageIcon Pimage7 = new ImageIcon("D:\\pixel7.jpg");
    lab.setIcon(Pimage7);
    x = "210000";
    Price.setText(x+"/- PKR");}

if(r2.isSelected()){    
    screen.setText("\n               Ram                      8GB\n"
    +"               Memory                128GB\n"
    +"               Battery                  4614mAh\n"
    +"               Front Camera   \t8MP\n"
    +"               Main Camera   \t60MP \n");
    ImageIcon pimage6 = new ImageIcon("D:\\pixel6.jpg");
    lab.setIcon(pimage6);
    x = "170000";
    Price.setText(x+"/- PKR");}

if(r3.isSelected()){    
    screen.setText("\n               Ram                      8GB\n"
    +"               Memory                256GB\n"
    +"               Battery                  4080mAh\n"
    +"               Front Camera   \t8MP\n"
    +"               Main Camera   \t16MP \n");
    ImageIcon pimage5 = new ImageIcon("D:\\pixel5.jpg");
    lab.setIcon(pimage5);
    x="150000";
    Price.setText(x+"/- PKR");} 

if(r4.isSelected()){    
    screen.setText("\n               Ram                      6GB\n"
    +"               Memory                128GB\n"
    +"               Battery                  2800mAh\n"
    +"               Front Camera   \t8MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon pimage4 = new ImageIcon("D:\\Pixel4.jpg");
    lab.setIcon(pimage4);
    x= "150000";
    Price.setText(x+"/- PKR");}

if(r5.isSelected()){    
    screen.setText("\n               Ram                      4GB\n"
    +"               Memory                64GB\n"
    +"               Battery                  2915mAh\n"
    +"               Front Camera   \t8MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon pimage3 = new ImageIcon("D:\\pixel 3.jpg");
    lab.setIcon(pimage3);  
    x="105000";
    Price.setText(x+"/- PKR");}

if(r6.isSelected()){    
    screen.setText("\n               Ram                      6GB\n"
    +"               Memory                128GB\n"
    +"               Battery                  3500mAh\n"
    +"               Front Camera   \t12MP\n"
    +"               Main Camera   \t48MP \n");
    ImageIcon Iimage1 = new ImageIcon("D:\\iphone14.jpg");
    lab.setIcon(Iimage1);
    x="350000";
    Price.setText(x+"/- PKR");}

if(r7.isSelected()){    
    screen.setText("\n               Ram                      4GB\n"
    +"               Memory                128GB\n"
    +"               Battery                  3000mAh\n"
    +"               Front Camera   \t12MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon Iimage2 = new ImageIcon("D:\\iphone13.jpg");
    lab.setIcon(Iimage2);
    x="270000";
    Price.setText(x+"/- PKR");}

if(r8.isSelected()){    
    screen.setText("\n               Ram                      4GB\n"
    +"               Memory                64GB\n"
    +"               Battery                  2800m Ah\n"
    +"               Front Camera   \t12MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon Iimage3 = new ImageIcon("D:\\iphone12.jpg");
    lab.setIcon(Iimage3);
    x="210000";
    Price.setText(x+"/- PKR");} 

if(r9.isSelected()){    
    screen.setText("\n               Ram                      4GB\n"
    +"               Memory                64GB\n"
    +"               Battery                  3100mAh\n"
    +"               Front Camera   \t12MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon Iimage4 = new ImageIcon("D:\\iphone11.jpg");
    lab.setIcon(Iimage4);
    x="170000";
    Price.setText(x+"/- PKR");} 

if(r10.isSelected()){    
    screen.setText("\n               Ram                      3GB\n"
    +"               Memory                64GB\n"
    +"               Battery                  2700mAh\n"
    +"               Front Camera   \t7MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon Iimage5 = new ImageIcon("D:\\iphoneX.jpg");
    lab.setIcon(Iimage5);
    x="130000";
    Price.setText(x+"/- PKR");} 

if(r11.isSelected()){    
    screen.setText("\n               Ram                      8GB\n"
    +"               Memory                512\n"
    +"               Battery                  3400mAh\n"
    +"               Front Camera   \t10MP\n"
    +"               Main Camera   \t12MP \n");
   ImageIcon Simage1 = new ImageIcon("D:\\galaxy s10.jpg");
    lab.setIcon(Simage1);
    x="220000";
    Price.setText(x+"/- PKR");}

if(r12.isSelected()){    
    screen.setText("\n               Ram                      4GB\n"
    +"               Memory                256GB\n"
    +"               Battery                  3000mAh\n"
    +"               Front Camera   \t8MP\n"
    +"               Main Camera   \t12MP \n");
    ImageIcon Simage2 = new ImageIcon("D:\\galaxy s9.jpg");
    lab.setIcon(Simage2);
    x="160000";
    Price.setText(x+"/- PKR");}

if(r13.isSelected()){    
    screen.setText("\n               Ram                      1.5GB\n"
    +"               Memory                16GB\n"
    +"               Battery                  2600mAh\n"
    +"               Front Camera   \t5MP\n"
    +"               Main Camera   \t13MP \n");
    ImageIcon Simage3 = new ImageIcon("D:\\galaxy j5.jpg");
    lab.setIcon(Simage3);
    x="21000";
    Price.setText(x+"/- PKR");} 

if(r14.isSelected()){    
    screen.setText("\n               Ram                      1GB\n"
    +"               Memory                8GB\n"
    +"               Battery                  2000mAh\n"
    +"               Front Camera   \t2MP\n"
    +"               Main Camera   \t5MP \n");
    ImageIcon Simage4 = new ImageIcon("D:\\galaxyj2.jpg");
    lab.setIcon(Simage4);
    x="10000";
    Price.setText(x+"/- PKR");} 

if(r15.isSelected()){    
    screen.setText("\n               Ram                      8GB\n"
    +"               Memory                256GB\n"
    +"               Battery                  5000mAh\n"
    +"               Front Camera   \t32MP\n"
    +"               Main Camera   \t64MP \n");
    ImageIcon Simage5 = new ImageIcon("D:\\galaxy a53.jpg");
    lab.setIcon(Simage5);
    x="35000";
    Price.setText(x+"/- PKR");} 

if(e.getSource()==Cancel){screen.setText(" ");}}
    
public static void main(String[] args) {
    shop obj = new shop();

}
}