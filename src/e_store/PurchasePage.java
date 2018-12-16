package e_store;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class PurchasePage extends JFrame {

	//fields
	private int amount;
	
	
	//constructor
	public PurchasePage(String title) {
		super(title);
		initComponents();
		
		
		
		
		
		
		
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000,650);
		setLocation(dim.width/2-getWidth()/2,dim.height/2-getHeight()/2);
		setVisible(true);
		
	}


	private void initComponents() {
		
		
	}
	
}
