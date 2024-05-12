package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class InsertForm extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame formInsert;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtID;
    static Connection conn;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InsertForm window = new InsertForm();
                window.formInsert.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public InsertForm() {
        initialize();
    }

    private void initialize() {
        formInsert = new JFrame();
        formInsert.getContentPane().setBackground(SystemColor.activeCaption);
        formInsert.setTitle("Insert a Doctor");
        formInsert.setBounds(100, 100, 450, 300);
        formInsert.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formInsert.getContentPane().setLayout(null);

        JButton btnInsert = new JButton("Insert");
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    int d_id = Integer.parseInt(txtID.getText());
                    String d_name = txtName.getText();
                    String d_surname = txtSurname.getText();
                    PreparedStatement p = (PreparedStatement) LoginForm.conn.prepareStatement("INSERT INTO DOCTORS VALUES(?,?,?)");
                    p.setInt(1, d_id);
                    p.setString(2,d_name);
                    p.setString(3, d_surname);
                    p.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Insert Done", "INSERT", JOptionPane.PLAIN_MESSAGE);
                    p.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        btnInsert.setBounds(182, 226, 77, 25);
        formInsert.getContentPane().add(btnInsert);

        JLabel lbName = new JLabel("Name:");
        lbName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbName.setBounds(108, 71, 47, 16);
        formInsert.getContentPane().add(lbName);

        JLabel lbSUR = new JLabel("Last Name:");
        lbSUR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbSUR.setBounds(71, 119, 84, 13);
        formInsert.getContentPane().add(lbSUR);

        JLabel lbID = new JLabel("ID:");
        lbID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbID.setBounds(132, 158, 23, 23);
        formInsert.getContentPane().add(lbID);
        
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnExit.setBounds(348, 232, 59, 18);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        formInsert.getContentPane().add(btnExit);

        txtName = new JTextField();
        txtName.setBounds(185, 71, 96, 19);
        formInsert.getContentPane().add(txtName);
        txtName.setColumns(10);

        txtSurname = new JTextField();
        txtSurname.setBounds(185, 117, 96, 19);
        formInsert.getContentPane().add(txtSurname);
        txtSurname.setColumns(10);

        txtID = new JTextField();
        txtID.setBounds(185, 161, 29, 19);
        formInsert.getContentPane().add(txtID);
        txtID.setColumns(10);
    }

    public JFrame getFrmInsertForm() {
        return formInsert;
    }
}