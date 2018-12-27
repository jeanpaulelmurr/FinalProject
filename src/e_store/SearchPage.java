package e_store;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class SearchPage extends JFrame{
	
	//fields
	JTabbedPane categories=new JTabbedPane();
	ArrayList laptops_array,laptop_images;
	JTextField searchField;
	JPanel laptops_panel,phones_panel,routers_panel , accessories_panel,parts_panel;
	JPanel startingLaptops=new JPanel(new GridLayout(3,1));
	Map<JLabel, Laptop> mapLaptops = new HashMap<JLabel, Laptop>(25);
	
	//constructor
	public SearchPage() {
		initComponents();
	}
	

	//methods
	
	
	//to create home page for every tab
	private void setStart() {
		laptop_images=new ArrayList();
		startingLaptops.removeAll();
		mapLaptops.clear();
		laptop_images.clear();
		try {
			Connection conn=JStoreDb.getConnection();
			java.sql.Statement sttmt=conn.createStatement();
			
			//replace table with table name
			String query="Select * from LAPTOPS";
			ResultSet rs=sttmt.executeQuery(query);
			
			while(rs.next()) {
				//"name" is the column that contains laptop name
				Laptop laptop=new Laptop(rs.getInt("laptop_id"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("supplier_id"),rs.getInt("price"),rs.getString("cpu"),rs.getString("ram"),rs.getString("screen"),rs.getString("battery"),rs.getString("windows"),rs.getString("storage"),rs.getString("color"),rs.getString("image"),rs.getString("vga"));
				ImageIcon ic=new ImageIcon(laptop.image);
				Image img = ic.getImage();
				Image newimg = img.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(newimg);
				JLabel tempImg = new JLabel(laptop.toString(), ic, JLabel.CENTER);
				javax.swing.border.Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
				tempImg.setBorder(border);
				laptop_images.add(tempImg);
				mapLaptops.put(tempImg,laptop);
				tempImg.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						dispose();
						ViewElement ve=new ViewElement(mapLaptops.get(tempImg));
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				laptop_images.add(tempImg);

			}
			
			rs.close();
			sttmt.close();
			
		}catch(Exception e) {
			
		}
	
		for(int i=0;i<laptop_images.size();i++)
			startingLaptops.add((JLabel)laptop_images.get(i));
		laptops_panel.add(startingLaptops);
	}

	
	// to search for item according to barcode
	private void searchForItem(int barcode) {
		startingLaptops.removeAll();
		mapLaptops.clear();
		laptop_images.clear();
		validate();
		
		try {
			Connection conn=JStoreDb.getConnection();
			java.sql.Statement sttmt=conn.createStatement();
			//replace table with table name
			String query="Select * from LAPTOPS where laptop_id="+barcode;
			ResultSet rs=sttmt.executeQuery(query);
			while(rs.next()) {
				//"name" is the column that contains laptop name
				Laptop laptop=new Laptop(rs.getInt("laptop_id"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("supplier_id"),rs.getInt("price"),rs.getString("cpu"),rs.getString("ram"),rs.getString("screen"),rs.getString("battery"),rs.getString("windows"),rs.getString("storage"),rs.getString("color"),rs.getString("image"),rs.getString("vga"));
				ImageIcon ic=new ImageIcon(laptop.image);
				Image img = ic.getImage();
				Image newimg = img.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
				ic = new ImageIcon(newimg);
				JLabel tempImg = new JLabel(laptop.toString(), ic, JLabel.CENTER);
				javax.swing.border.Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
				tempImg.setBorder(border);
				laptop_images.add(tempImg);
				tempImg.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						dispose();
						ViewElement ve=new ViewElement(mapLaptops.get(tempImg));
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				mapLaptops.put(tempImg,laptop);
			}
			rs.close();
			sttmt.close();
		}catch(Exception e) {
			
		}
		
		for(int i=0;i<laptop_images.size();i++)
			startingLaptops.add((JLabel)laptop_images.get(i));
		
		laptops_panel.add(startingLaptops);
		validate();
	}
	
	public static void main(String[] args) {
		SearchPage sp=new SearchPage();
	}


	private void initComponents() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000,650);
		setLocation(dim.width/2-getWidth()/2,dim.height/2-getHeight()/2);
		setVisible(true);
		laptops_panel=new JPanel();
		LayoutManager layout = new BoxLayout(laptops_panel, BoxLayout.Y_AXIS);
		laptops_panel.setLayout(layout);
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
		    	  		if(searchField.getText().equals(""))
		    	  			setStart();
		    	  		else
		    	  			searchForItem(Integer.parseInt(searchField.getText()));
		    	  		break;
		    	  }
		      }
		    });
		
		JPanel searchPanel =new JPanel();
		searchPanel.add(searchField,BorderLayout.CENTER);
		
		laptops_panel.add(searchPanel);
		
		phones_panel = new JPanel();
		
		routers_panel =new JPanel();
		
		accessories_panel = new JPanel();
		
		parts_panel=new JPanel();
		
		setStart();
		
		categories.addTab("Laptops", laptops_panel);
		categories.addTab("Cell phones",phones_panel);
		categories.addTab("Routers", routers_panel);
		categories.addTab("Computer parts",	 parts_panel);
		categories.addTab("Accessories",accessories_panel);
		
		add(categories);
		
		
	}
	
}	
