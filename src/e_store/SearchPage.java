package e_store;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;

import javax.swing.*;

public class SearchPage extends JFrame{
	JTabbedPane categories=new JTabbedPane();
	ArrayList itemNames=new ArrayList();
	JTextField searchField;
	
	public SearchPage() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000,650);
		setLocation(dim.width/2-getWidth()/2,dim.height/2-getHeight()/2);
		setVisible(true);
		databaseName();
		JPanel laptops=new JPanel();
		searchField=new JTextField(30);
		searchField.setText("Search");
		searchField.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		    	  searchField.setText("");
		      };
		      public void focusLost(FocusEvent e) {
		          searchField.setText("Search");
		        }
		});

		searchField.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		      }

		      public void keyTyped(KeyEvent e) {
		      }

		      public void keyPressed(KeyEvent e) {
		    	  switch(e.getKeyCode()) {
		    	  	case KeyEvent.VK_BACK_SPACE:
		    		  break;
		    	  	case KeyEvent.VK_ENTER:
		    	  		searchField.setText(searchField.getText());
		    	  		break;
		    	  	default:
		    	  		EventQueue.invokeLater(new Runnable() {
		    	  			@Override
		    	  			public void run() {
		    	  				String txt=searchField.getName();
		    	  				autoComplete(txt);
		    	  			}
		    	  		});
		    	  }
		      }
		    });
		laptops.add(searchField,BorderLayout.NORTH);
		
		JPanel cellPhones = new JPanel();
		
		JPanel routers =new JPanel();
		
		JPanel Accessories = new JPanel();
		
		JPanel pcParts=new JPanel();
		
		categories.addTab("Laptops", laptops);
		categories.addTab("Cell phones",cellPhones);
		categories.addTab("Routers", routers);
		categories.addTab("Computer parts",	 pcParts);
		categories.addTab("Accessories",Accessories);
		
		add(categories);
		
	}
	
	private void databaseName() {
		try {
			java.sql.Connection conn=JStoreDb.getConnection();
			java.sql.Statement sttmt=conn.createStatement();
			//replace table with table name
			String query="Select * from table;";
			ResultSet rs=sttmt.executeQuery(query);
			while(rs.next()) {
				//"name" is the column that contains laptop name
				String name=rs.getString("name");
				itemNames.add(name);
			}
			rs.close();
			sttmt.close();
			conn.close();
		}catch(Exception e) {
			
		}
	}
	
	public void autoComplete(String txt) {
		String complete="";
		int start=txt.length();
		int end=txt.length();
		for(int i=0;i<itemNames.size();i++) 
			if(itemNames.get(i).toString().startsWith(txt)) {
				complete=itemNames.get(i).toString();
				end=complete.length();
				break;
			}
		if(end>start) {
			searchField.setText(complete);
			searchField.setCaretPosition(end);
			searchField.moveCaretPosition(start);
		}
	}
	
	

	
	public static void main(String[] args) {
		SearchPage sp=new SearchPage();
	}
}	
