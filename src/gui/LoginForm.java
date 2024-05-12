package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginForm extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
    private JFrame formLogin;
    private MainForm formMain;
    static Connection conn;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginForm window = new LoginForm();
                    window.formLogin.setVisible(true);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */

    public LoginForm() {
        initialize();
    }

    private void initialize() {
        formLogin = new JFrame();
       
        formLogin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                String url="jdbc:mysql://127.0.0.1:3307/doctorsdb";
                String username="root";
                String password="ellada2001";
                System.out.println("Connecting database...");

                try {
                    conn = DriverManager.getConnection(url, username, password);
                }catch(SQLException ex) {
                    throw new IllegalStateException("Cannot connect the database!", ex);
                }System.out.println("Successfully connected.");

            }

        });
        formLogin.getContentPane().setBackground(SystemColor.activeCaption);
        formLogin.setTitle("Manager");
        formLogin.setBounds(100, 100, 450, 300);
        formLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formLogin.getContentPane().setLayout(null);

        JButton btnManage = new JButton("Manage");
        btnManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainForm();
            }
        });
        btnManage.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnManage.setBounds(164, 133, 97, 35);
        formLogin.getContentPane().add(btnManage);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.activeCaption);
        panel.setBounds(0, 0, 434, 65);
        formLogin.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("Doctor's Manager");
        lblTitle.setFont(new Font("Ubuntu", Font.BOLD, 24));
        lblTitle.setBounds(116, 10, 211, 43);
        panel.add(lblTitle);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnExit.setBounds(348, 232, 59, 18);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        formLogin.getContentPane().add(btnExit);
    }

    private void showMainForm() {
    	 if (formMain == null) {
             formMain = new MainForm();
         }
        formMain.getFrmMainForm().setVisible(true);
    }

    public JFrame getFrmMainWindow() {
        return formLogin;
    }


}
