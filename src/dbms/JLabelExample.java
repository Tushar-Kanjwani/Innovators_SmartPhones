package dbms;

/**
 *
 * @author tusha
 */
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


import javax.swing.*;
import java.awt.*;

public class JLabelExample extends JFrame {

    private JLabel label1, label2;

    public JLabelExample() {
        setTitle("JLabel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Load image files
        ImageIcon image2 = new ImageIcon(new ImageIcon("D:\\SLOGO.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH ));
        ImageIcon image1 = new ImageIcon(new ImageIcon("D:\\pixel 3.jpg").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
    //    ImageIcon image2 = new ImageIcon("D:\\pixel 3.jpg");

        // Create JLabels with images
        label1 = new JLabel(image1);
        label2 = new JLabel(image2);
        label1.setBounds(50, 50, 100, 100);
        label2.setBounds(170, 50, 100, 100);
        // Add JLabels to frame
        add(label1);
        add(label2);

  //      pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new JLabelExample();
    }
}
