import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

public class LoginPage implements ActionListener {

    JFrame frame1 = new JFrame();
    JButton logButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userId = new JTextField();
    JPasswordField userpass = new JPasswordField();
    JLabel userIDLabel = new JLabel("User ID: ");
    JLabel userpassLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();
    JPanel logpane = new JPanel();
    JLabel wlcome = new JLabel("Log in");
    JLabel exitLabel;
    JLabel T_name = new JLabel();



    HashMap<String,String> logininfo = new HashMap<String,String>();
    
    LoginPage(HashMap<String,String> loginnfoorg)
{
    logpane.setBounds(0,0,500,800);
    logpane.setBackground(Color.white);
    logpane.setLayout(null);
    logpane.setVisible(true);

    T_name.setBounds(200,600,200,200);
    T_name.setText("x86");
    T_name.setForeground(new Color(166,166,166));
    T_name.setFont(new Font("Robot",Font.BOLD,54));

    wlcome.setForeground(Color.black);
    wlcome.setLayout(null);
    wlcome.setBounds(200,240,75,75);
    wlcome.setFont(new Font("Robot",Font.BOLD,24));
    wlcome.setVisible(true);

    ImageIcon icon = new ImageIcon("src\\arrow.png");
    Image image = icon.getImage().getScaledInstance(50, 50,Image.SCALE_FAST);
    ImageIcon icon2 = new ImageIcon(image);

    JLabel bla = new JLabel(icon2);
    bla.setBounds(10,10,50,50);


        bla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                System.exit(0);
            }
        });


        ImageIcon icon3 = new ImageIcon("src\\origami2.png");
        Image image2 = icon3.getImage().getScaledInstance(100, 100,Image.SCALE_FAST);
        ImageIcon icon4 = new ImageIcon(image2);

        JLabel logo = new JLabel(icon4);
        logo.setBounds(190,100,100,100);

    logininfo = loginnfoorg;
    userIDLabel.setBounds(150,300,75,75);
    userpassLabel.setBounds(150,350,100,75);
    userpassLabel.setForeground(Color.black);
    userIDLabel.setForeground(Color.black);

    messageLabel.setBounds(150,490,250,35);
    messageLabel.setFont(new Font(null,Font.BOLD,18));

    userId.setBounds(150,350,200,25);
    userId.setBackground(Color.white);

    userpass.setBounds(150,400,200,25);
    userpass.setBackground(Color.white);
    userIDLabel.setFont(new Font("Robot",Font.BOLD,14));
    userId.setFont(new Font("Robot",Font.BOLD,18));
    userpassLabel.setFont(new Font("Robot",Font.BOLD,14));
    userpass.setFont(new Font("Robot",Font.BOLD,18));


    logButton.setBounds(150,450,100,25);
    logButton.setFocusable(false);
    logButton.setBackground(new Color(44,44,44));
    logButton.setBorderPainted(false);
    logButton.setForeground(Color.white);
    logButton.addActionListener(this);

    resetButton.setBounds(251,450,100,25);
    resetButton.setFocusable(false);
    resetButton.setBorderPainted(false);
    resetButton.setBackground(new Color(44,44,44));
    resetButton.setForeground(Color.white);
    resetButton.addActionListener(this);

   
    frame1.add(logpane);
    logpane.add(T_name);
    logpane.add(wlcome);
    logpane.add(bla);
    logpane.add(logo);
    logpane.add(messageLabel);
    logpane.add(logButton);
    logpane.add(resetButton);
    logpane.add(userId);
    logpane.add(userpass);
    logpane.add(userIDLabel);
    logpane.add(userpassLabel);
    logpane.revalidate();
    logpane.repaint();
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setLayout(null);
    frame1.setBounds(600,50,500,800);
    frame1.setBackground(new Color(30,30,30));
    frame1.setUndecorated(true);
    frame1.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
    if(e.getSource()==resetButton)
    {
        userId.setText("");
        userpass.setText((""));
    }
    
    if(e.getSource()==logButton)
    {
        String ID = userId.getText();
        String passowrd = String.valueOf(userpass.getPassword());
        if(logininfo.containsKey(ID))
    {
        if(logininfo.get(ID).equals(passowrd))
        {
            messageLabel.setForeground(Color.orange);
            messageLabel.setText("You are logged in..");
            person newperson = new person();
            Server server = new Server();
            frame1.dispose();

        }
        else 
        {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Wrong password");
        }
    }
    else
    {
        messageLabel.setForeground(Color.red);
        messageLabel.setText("user name not found");
    }
    }
}
}
