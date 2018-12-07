package project;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

public class Register extends JFrame {
	//Panels
	private JPanel mainPanel=new JPanel();
	private JPanel usernamePanel=new JPanel();
	private JPanel passwordPanel=new JPanel();
	private JPanel passwordPanel2=new JPanel();
	private JPanel firstnamePanel=new JPanel();
	private JPanel lastnamePanel=new JPanel();
	private JPanel birthdayPanel=new JPanel();
	private JPanel emailPanel=new JPanel();
	private JPanel rolePanel=new JPanel();
	private JPanel buttonPanel=new JPanel();
	private JPanel rightpnl=new JPanel();
	private JPanel leftpnl=new JPanel();
	//Labels
	private JLabel usernameLabel=new JLabel("Username: ");
	private JLabel passwordLabel=new JLabel("Password: ");
	private JLabel passwordLabel2=new JLabel("Re-type password: ");
	private JLabel firstnameLabel=new JLabel("First Name: ");
	private JLabel lastnameLabel=new JLabel("Last Name: ");
	private JLabel birthdayLabel=new JLabel("Birthdate: ");
	private JLabel emailLabel=new JLabel("Email:");
	private JLabel role=new JLabel("Role: ");
	//Fields
	private JTextField usernameField=new JTextField(22);
	private JPasswordField passwordField=new JPasswordField(23);
	private JPasswordField passwordField2=new JPasswordField(18);
	private JButton submitButton=new JButton("Submit");
	private JButton cancelButton=new JButton("Cancel");
	private JTextField firstnameField=new JTextField(20);
	private JTextField lastnameField=new JTextField(20);
	private JSpinner spin=new JSpinner(new SpinnerDateModel());
	private JTextField emailField=new JTextField(10);
	private JComboBox<String> domain=new JComboBox<String>();
	private JComboBox<String> rolebox=new JComboBox<String>();
	
	
	public Register() {
		firstnamePanel.add(firstnameLabel);
		firstnamePanel.add(firstnameField);
		
		lastnamePanel.add(lastnameLabel);
		lastnamePanel.add(lastnameField);
		
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		passwordPanel2.add(passwordLabel2);
		passwordPanel2.add(passwordField2);
		
		birthdayPanel.add(birthdayLabel);
		spin.setEditor(new JSpinner.DateEditor(spin,"dd/MM/yyyy"));
		birthdayPanel.add(spin);
		domain.addItem("@hotmail.com");
		domain.addItem("@live.com");
		domain.addItem("@gmail.com");
		domain.addItem("@outlook.com");
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		emailPanel.add(domain);
		
		rolebox.addItem("admin");
		rolebox.addItem("user");
		rolePanel.add(role);
		rolePanel.add(rolebox);
		
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		
		leftpnl.setLayout(new BoxLayout(leftpnl,BoxLayout.Y_AXIS));
		leftpnl.add(firstnamePanel);leftpnl.add(lastnamePanel);leftpnl.add(emailPanel);leftpnl.add(birthdayPanel);
		
		rightpnl.setLayout(new BoxLayout(rightpnl,BoxLayout.Y_AXIS));
		rightpnl.add(usernamePanel);rightpnl.add(passwordPanel);rightpnl.add(passwordPanel2);rightpnl.add(rolebox);
		
		
		setLayout(new FlowLayout());
		
		add(leftpnl);
		add(rightpnl);
		add(buttonPanel);
		
		
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> errorList = new ArrayList<String>();
				String fname=firstnameField.getText().toString();
				String lname=lastnameField.getText().toString();
				String username=usernameField.getText().toString();
				String password=new String(passwordField.getPassword());
				String password2=new String(passwordField2.getPassword());
				String name=emailField.getText().toString().toLowerCase();
				String dom=domain.getSelectedItem().toString();
				String email=name.concat(dom);
				SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
				String spinnerValue = formater.format(spin.getValue());
				String role=rolebox.getSelectedItem().toString();
				if(!isValidEmailAddress(email)) {
					JOptionPane.showMessageDialog(null,"The format of email is incorrect");
					return;
				}
				if(username.isEmpty() || fname.isEmpty() || lname.isEmpty() || password.isEmpty() || email.isEmpty()) {
					JOptionPane.showMessageDialog(null,"All fields are required");
					return;
				}
				if(!isValid(password,password2,errorList)) {
						JOptionPane.showMessageDialog(null,errorList);
						return;
				}
				try {
					Connection con = Sqlconnection.getConnection();
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("SELECT USERNAME,EMAIL FROM LOGIN");
					while(rs.next()) {
						if(rs.getString("USERNAME").equals(username)) {
							JOptionPane.showMessageDialog(null, "Username exists");
							return;
						}
						if(rs.getString("EMAIL").equals(email)) {
							JOptionPane.showMessageDialog(null, "Email exists");
							return;
						}
					}
					PreparedStatement insertion = con.prepareStatement("INSERT INTO LOGIN (USERNAME,PASSWORD,FIRSTNAME,LASTNAME,BIRTHDAY,EMAIL,ROLE) VALUES ('"+username+"','"+password+"','"+fname+"','"+lname+"',to_date(?, 'DD-MM-YYYY'),'"+email+"','"+role+"') ");
					insertion.setString(1, spinnerValue);
					insertion.executeUpdate();
					con.close();
					JOptionPane.showMessageDialog(null,"Registration completed");
					Login login=new Login();
					login.setSize(300,250);
					login.setVisible(true);
					dispose();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}				
			}
		});
	}
	
	
	
	public static void main(String[] args) {
		Register frame=new Register();
		frame.setSize(700,400);
		frame.setVisible(true);
	}
	
	
	//validation of email
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	//Check password validation
	public static boolean isValid(String passwordhere,String confirmhere, ArrayList<String> errorList) {

	    Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
	    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
	    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
	    errorList.clear();

	    boolean flag=true;
	    if (!passwordhere.equals(confirmhere)) {
	        errorList.add("The passwords does not match");
	        flag=false;
	    }
	    
	    if (passwordhere.length() < 8) {
	        errorList.add("Password length must have at least 8 character !!");
	        flag=false;
	    }
	    if (!specailCharPatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one special character !!");
	        flag=false;
	    }
	    if (!UpperCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one uppercase character !!");
	        flag=false;
	    }
	    if (!lowerCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one lowercase character !!");
	        flag=false;
	    }
	    if (!digitCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one digit character !!");
	        flag=false;
	    }

	    return flag;

	}
}