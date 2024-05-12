package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainForm extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame formMain;
    private InsertForm InsertForm;
    private UpDelForm UpDelForm;
    static Connection conn;
    public static String searchSurnameVar;
    private JTextField searchSurname;



    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainForm window = new MainForm();
                window.formMain.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public MainForm() {
        initialize();
    }

    private void initialize() {
        
    	formMain = new JFrame();
        formMain.getContentPane().setBackground(SystemColor.activeCaption);
        formMain.setTitle("Main Hub");
        formMain.setBounds(100, 100, 450, 300);
        formMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formMain.getContentPane().setLayout(null);

        JButton btnInsert = new JButton("Insert a Doctor");
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInsert.setBounds(147, 192, 135, 22);
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInsertForm();

            }
        });
        formMain.getContentPane().add(btnInsert);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSearch.setBounds(160, 120, 103, 24);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchSurnameVar=searchSurname.getText();
                showUpDelForm();
            }
        });
        formMain.getContentPane().add(btnSearch);

        JLabel lblSearch = new JLabel("Search by Surname");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSearch.setBounds(139, 31, 144, 27);
        formMain.getContentPane().add(lblSearch);
        
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnExit.setBounds(348, 232, 59, 18);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        formMain.getContentPane().add(btnExit);

        searchSurname = new JTextField();
        searchSurname.setBounds(147, 76, 130, 21);
        formMain.getContentPane().add(searchSurname);
        searchSurname.setColumns(10);
    }

    public JFrame getFrmMainForm() {
        return formMain;
    }

    private void showInsertForm() {
        if (InsertForm == null) {
            InsertForm = new InsertForm();
        }
        InsertForm.getFrmInsertForm().setVisible(true);
    }

    private void showUpDelForm() {
        if (UpDelForm == null) {
            UpDelForm = new UpDelForm();
        }
        UpDelForm.getFrmUpDelForm().setVisible(true);
    }


}
