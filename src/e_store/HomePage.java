package e_store;

import java.awt.*;
import javax.swing.*;

public class HomePage extends JFrame {


	public HomePage(String storeName) {
		super(storeName);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(300, 500);
		setLocation((int)(dim.getWidth()/2-getWidth()/2),(int)(dim.getHeight()/2-getHeight()/2));
		JPanel login_panel1=new JPanel();
		JPanel login_panel2=new JPanel();
		
		JLabel login_label=new JLabel("LOGIN");
		JLabel username_label=new JLabel("Username:");
		JLabel password_label =new JLabel("Password:");
		JTextField username_field=new JTextField(24);
		JTextField password_field =new JTextField(24);
		JButton enter_button =new JButton("Enter");
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
	public static void main(String[] args) {
		HomePage homepage=new HomePage("JStore");

	}

}
