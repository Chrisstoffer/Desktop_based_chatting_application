import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class person implements ActionListener {
    static JFrame frame = new JFrame();
    JTextField type = new JTextField();
    static JPanel down = new JPanel();
    static Box virtical_box = Box.createVerticalBox();
    static DataOutputStream dout;
    
    // Constructor
    public person() {
        frame.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0xc8c8c8));
        p1.setBounds(0, 0, 450, 80);
        p1.setLayout(null);
        frame.add(p1);
        
        ImageIcon icon1 = new ImageIcon("src\\right-arrow.png");
        Image image1 = icon1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);  
        ImageIcon icon2 = new ImageIcon(image1);
        
        JLabel label1 = new JLabel(icon2);
        label1.setBounds(5, 20, 30, 30);
        p1.add(label1); 

        label1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent re) {
                System.exit(0);
            }
        });

        ImageIcon icon3 = new ImageIcon("src\\siraj.png");
        Image image2 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);  
        ImageIcon icon4 = new ImageIcon(image2);

        JLabel profile1 = new JLabel(icon4);
        profile1.setBounds(40, 10, 50, 50);
        p1.add(profile1);

        ImageIcon icon5 = new ImageIcon("src\\zoom.png");
        Image image3 = icon5.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);  
        ImageIcon icon6 = new ImageIcon(image3);
        JLabel Video = new JLabel(icon6);
        Video.setBounds(300, 20, 35, 35);
        p1.add(Video); 

        ImageIcon icon7 = new ImageIcon("src\\phone-call.png");
        Image image4 = icon7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);  
        ImageIcon icon8 = new ImageIcon(image4);
        JLabel audioc = new JLabel(icon8);
        audioc.setBounds(360, 20, 30, 30);
        p1.add(audioc); 

        ImageIcon icon9 = new ImageIcon("src\\dots.png");
        Image image5 = icon9.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT);  
        ImageIcon icon10 = new ImageIcon(image5);
        JLabel dot = new JLabel(icon10);
        dot.setBounds(400, 20, 30, 25);
        p1.add(dot); 

        JLabel name1 = new JLabel("Siraj");
        name1.setBounds(110, 25, 100, 22);
        name1.setForeground(Color.black);
        name1.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name1);

        JLabel active = new JLabel("Active Now");
        active.setBounds(110, 45, 100, 18);
        active.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        p1.add(active);

        down.setBounds(5, 75, 440, 570);
        frame.add(down);

        type.setBounds(5, 655, 310, 40);
        type.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        frame.add(type);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(0xc8c8c8));
        send.setForeground(Color.white);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        frame.add(send);

        frame.setUndecorated(true);
        frame.setSize(450, 700);
        frame.setLocation(850, 25);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent re) {
        try {
            String sender1 = type.getText().trim();
            if (sender1.isEmpty()) {
                return;  
            }

            JPanel sendJPanel = formatLabel(sender1);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    down.setLayout(new BorderLayout());
                    JPanel rightpanel = new JPanel(new BorderLayout());
                    rightpanel.add(sendJPanel, BorderLayout.LINE_END);
                    virtical_box.add(rightpanel);
                    virtical_box.add(Box.createVerticalStrut(15));
                    down.add(virtical_box, BorderLayout.PAGE_START);
                    try {
                        dout.writeUTF(sender1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    type.setText("");
                    frame.repaint();
                    frame.invalidate();
                    frame.validate();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String sender1) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel sendJLabel = new JLabel("<html><p style=\"width: 150px\">" + sender1 + "</p></html>");
        sendJLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
        sendJLabel.setForeground(Color.white);
        sendJLabel.setBorder(new EmptyBorder(15, 15, 15, 50));
        sendJLabel.setBackground(new Color(0x858585));
        sendJLabel.setOpaque(true);
        panel.add(sendJLabel);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel(sDateFormat.format(calendar.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new person();
        try {
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                try {
                    String mess = din.readUTF();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            down.setLayout(new BorderLayout());
                            JPanel panel = formatLabel(mess);
                            JPanel lefJPanel = new JPanel(new BorderLayout());
                            lefJPanel.add(panel, BorderLayout.LINE_START);
                            virtical_box.add(lefJPanel);
                            virtical_box.add(Box.createVerticalStrut(15));
                            down.add(virtical_box, BorderLayout.PAGE_START);
                            frame.validate();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error connecting to server.");
        }
        Object delay = new Object();
        synchronized(delay)
        {
            try {
                delay.wait(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
   
    }


}
