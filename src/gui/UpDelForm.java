package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpDelForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtID;
	private JButton btnApply;
	private ResultSet rs;
    public PreparedStatement p;
    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnLast;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpDelForm frame = new UpDelForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpDelForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
                    String sql="SELECT id,name,surname FROM doctors WHERE surname LIKE ?";
                    p = LoginForm.conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    p.setString(1, MainForm.searchSurnameVar+'%');
                    rs = p.executeQuery();

                    if(rs.next()) {
                        txtID.setText(Integer.toString(rs.getInt("id")));
                        txtName.setText(rs.getString("name"));
                        txtSurname.setText(rs.getString("surname"));
                    }

                }catch(SQLException ex1) {
                    ex1.printStackTrace();
                }
			}
		});
		
		setTitle("Edit Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setColumns(10);
		
		JButton btnDel = new JButton("Delete Entry");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String query = "DELETE FROM doctors WHERE id=?";
		            PreparedStatement p = LoginForm.conn.prepareStatement(query);
		            p.setInt(1, Integer.parseInt(txtID.getText()));

		            int dialogButton;
		            dialogButton = JOptionPane.showConfirmDialog(null,"Are you sure?","Warning", JOptionPane.YES_NO_OPTION);
		            if(dialogButton == JOptionPane.YES_OPTION) p.execute();
		            else {}
		        }catch (SQLException e6){

		            e6.printStackTrace();
		        }
			}
		});
		
		txtID = new JTextField();
		txtID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Surname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnApply = new JButton("Apply Changes");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String query = "UPDATE doctors SET surname=?, name=? WHERE id=?";
		            PreparedStatement p = LoginForm.conn.prepareStatement(query);

		            p.setString(1, txtSurname.getText());
		            p.setString(2, txtName.getText());
		            p.setInt(3, Integer.parseInt(txtID.getText()));
		            p.executeUpdate();
		            JOptionPane.showMessageDialog(null,"Update Done", "UPDATE", JOptionPane.PLAIN_MESSAGE);
		            p.close();

		        }catch (SQLException e1){

		            e1.printStackTrace();
		        }
			}
		});
		
		btnFirst = new JButton("<<");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.first()) {
						txtID.setText(Integer.toString(rs.getInt("id")));
						txtSurname.setText(rs.getString("surname"));
						txtName.setText(rs.getString("name"));
					}
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.previous()) {
						txtID.setText(Integer.toString(rs.getInt("id")));
						txtSurname.setText(rs.getString("surname"));
						txtName.setText(rs.getString("name"));
					}else rs.first();
				}catch(SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		
		btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.next()) {
						txtID.setText(Integer.toString(rs.getInt("id")));
						txtSurname.setText(rs.getString("surname"));
						txtName.setText(rs.getString("name"));
					}else rs.last();
				}catch(SQLException e4) {
					e4.printStackTrace();
				}
			}
			
		});
		
		btnLast = new JButton(">>");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rs.last()) {
						txtID.setText(Integer.toString(rs.getInt("id")));
						txtSurname.setText(rs.getString("surname"));
						txtName.setText(rs.getString("name"));
					}
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addComponent(btnDel)
					.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
					.addComponent(btnApply)
					.addGap(45))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(btnFirst)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel))
							.addGap(28))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(41)
							.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(44)
					.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFirst)
						.addComponent(btnNext)
						.addComponent(btnLast)
						.addComponent(btnPrevious))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDel)
						.addComponent(btnApply))
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public JFrame getFrmUpDelForm() {
        return new UpDelForm();
    }

}
