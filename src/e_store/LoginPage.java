package e_store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class LoginPage extends JFrame {

	private static LoginPage loginPage=new LoginPage();
	
	
	private LoginPage() {
		super("JStore");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(400, 400);
		setLocation((int)(dim.getWidth()/2-getWidth()/2),(int)(dim.getHeight()/2-getHeight()/2));
		JPanel login_panel1=new JPanel();
		JPanel login_panel2=new JPanel();
		
		JLabel login_label=new JLabel("LOGIN");
		JLabel username_label=new JLabel("Username:");
		JLabel password_label =new JLabel("Password:");
		JTextField username_field=new JTextField(24);
		JPasswordField password_field =new JPasswordField(24);
		JButton enter_button =new JButton("Enter");
		JTextField username_field=new JTextField(24);
		
		username_field.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		    	  username_field.setText("");
		      };
		      public void focusLost(FocusEvent e) {
		    	  if(username_field.getText().equals(""))
		    		  username_field.setText("Username");
		        }
		});

		JPasswordField password_field =new JPasswordField(24);
		password_field.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		    	  password_field.setText("");
		      };
		      public void focusLost(FocusEvent e) {
		    	  if(password_field.getText().equals(""))
		    		  password_field.setText("Password");
		        }
		});

		JButton enter_button =new JButton("Login");
		enter_button.setBackground(Color.CYAN);
		password_field.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		      }

		      public void keyTyped(KeyEvent e) {
		      }

		      public void keyPressed(KeyEvent e) {
		    	  switch(e.getKeyCode()) {
		    	  	case KeyEvent.VK_BACK_SPACE:
		    		  break;
		    	  	case KeyEvent.VK_ENTER:
		    	  		enter_button.doClick();
		    	  		break;
		    	  }
		      }
		    });
		
		
		enter_button.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	
				try {
					java.sql.Connection conn=JStoreDb.getConnection();
					java.sql.Statement sttmt=conn.createStatement();
					//replace table with table name
					String query="SELECT * FROM EMPLOYEE2 WHERE username='"+username_field.getText().toString()+"' AND password='"+password_field.getText().toString()+"'";
					ResultSet rs=sttmt.executeQuery(query);
					if(rs.next()) {
						dispose();
						SearchPage.getInstance();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid username/password.", "Login failed", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					username_field.setText("");
	    	  		password_field.setText("");
	    	  		username_field.requestFocus();
				}
		    }
		});

		JButton register_button =new JButton("New user");
		login_panel2.add(login_label);
		login_panel1.add(username_label);
		login_panel1.add(username_field);
		login_panel1.add(password_label);
		login_panel1.add(password_field);
		login_panel1.add(enter_button);
		login_panel1.add(register_button);
		add(login_panel2,BorderLayout.NORTH);
		add(login_panel1,BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static LoginPage getInstance(){
		return loginPage;
		}
	
	public static void main(String[] args) {
		
	}

}
