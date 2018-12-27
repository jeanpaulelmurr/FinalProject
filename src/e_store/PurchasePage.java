package e_store;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
public class PurchasePage extends JFrame {

	//fields
	private int amount=0;
	public static int invoice=0;
	private String[] columnNames= {"Barcode","Name","Specs","Unit price","Quantity"};
	private static final String EMAIL_PATTERN = 
		    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	protected String c_name,c_email;
	protected int c_phone;
	//constructor
	public PurchasePage(String title,ArrayList cart) {
		super(title);
		initComponents();
		
	}
	
	
	  public void sendMail(Customer customer,ArrayList cart) {
	        //Setting up configurations for the email connection to the Google SMTP server using TLS
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        //Establishing a session with required user details
	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("elecstore.inc@gmail.com", "Hell0W0rld");
	            }
	        });
	        try {
	            //Creating a Message object to set the email content
	            MimeMessage msg = new MimeMessage(session);
	            String bill="Dear "+customer.name+",\n\nInvoice NO."+invoice+"\n";
	            bill+="ID\tName\tSpecs\tQuantity\tPrice\n";
	            for(int i=0;i<ViewElement.cart.size();i++) {
	            	Laptop laptop=(Laptop)ViewElement.cart.get(i);
	            	bill+=laptop.id+"\t"+laptop.name+"\t"+laptop.toString()+"\t"+laptop.quantity+"\t"+laptop.price+"\n";
	            }
	            bill+="TOTAL= "+amount+"\n";
	            //Storing the comma seperated values to email addresses
	            String to = customer.email;
	            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
	            addresses in an array of InternetAddress objects*/
	            InternetAddress[] address = InternetAddress.parse(to, true);
	            //Setting the recepients from the address variable
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject("Bill confirmation");
	            msg.setSentDate(new Date());
	            msg.setText(bill);
	            msg.setHeader("XPriority", "1");
	            Transport.send(msg);
	            System.out.println("Mail has been sent successfully");
	        } catch (MessagingException mex) {
	            System.out.println("Unable to send an email" + mex);
	        }
	    }
	

	private void initComponents() {
		JPanel billing_header=new JPanel();
		JLabel title=new JLabel("Billing");
		title.setFont (title.getFont ().deriveFont (64.0f));
		billing_header.add(title);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel invoice_info=new JPanel();
		
		JPanel customer_details=new JPanel(new GridLayout(4,2));
		JLabel invoice_label=new JLabel("Invoice: ");
		customer_details.add(invoice_label);
		JTextField invoice_field =new JTextField(5);
		invoice_field.setEditable(false);
		invoice_field.setText(Integer.toString(invoice));
		
		customer_details.add(invoice_field);
		JLabel name_label=new JLabel("Customer name: ");
		customer_details.add(name_label);
		JTextField name_field=new JTextField(40);
		customer_details.add(name_field);
		name_field.requestFocus();
		JLabel phone_label=new JLabel("Customer phone: ");
		customer_details.add(phone_label);
	
		MaskFormatter phone_format = null;
		try {
			phone_format = new MaskFormatter("########");
			} catch (ParseException e1) {
				e1.printStackTrace();
				}

		JFormattedTextField phone_field = new JFormattedTextField(phone_format);
		customer_details.add(phone_field);

		JLabel email_label =new JLabel ("Customer email: ");
		customer_details.add(email_label);
		JTextField email_field=new JTextField(40);
		email_field.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		      }

		      public void keyTyped(KeyEvent e) {
		      }

		      public void keyPressed(KeyEvent e) {
		    	  switch(e.getKeyCode()) {
		    	  	case KeyEvent.VK_BACK_SPACE:
		    		  break;
		    	  	case KeyEvent.VK_ENTER:
		    	  		email_field.setText(email_field.getText());
		    	  		if(!(email_field.getText().matches(EMAIL_PATTERN))){
		    	  			JOptionPane.showMessageDialog(null, "Invalid email format", "Warning", JOptionPane.WARNING_MESSAGE);
		    	  			email_field.setText("");
		    	  			email_field.requestFocus();
		    	  		}
		    	  			
		    	  		break;
		    	  }
		      }
		    });
		email_field.addFocusListener(new FocusListener() {
		      public void focusGained(FocusEvent e) {
		      };
		      public void focusLost(FocusEvent e) {
		    	  if(!(email_field.getText().matches(EMAIL_PATTERN))){
	    	  			JOptionPane.showMessageDialog(null, "Invalid email format", "Warning", JOptionPane.WARNING_MESSAGE);
	    	  			email_field.setText("");
	    	  			email_field.requestFocus();
	    	  		}
		        }
		});
		
		customer_details.add(email_field);
		invoice_info.add(customer_details);
		
		JPanel table_panel=new JPanel();
		LayoutManager layout2=new BoxLayout(table_panel,BoxLayout.Y_AXIS);
		table_panel.setLayout(layout2);
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0)
		  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false;//This causes all cells to be not editable
		    }
		  };
		JTable items = new JTable(tableModel);	
		for(int i=0;i<ViewElement.cart.size();i++) {
			Laptop temp=(Laptop)ViewElement.cart.get(i);
            Object[] obj= {temp.id,temp.name,temp.toString(),temp.price,temp.quantity};
            tableModel.addRow(obj);
            } 
				
		table_panel.add(items);
		JButton remove=new JButton("Remove");
	
		table_panel.add(remove);
	
		
		JPanel math_panel=new JPanel(new GridLayout(3,2));
		JLabel subt_label=new JLabel("SubTotal: ");
		math_panel.add(subt_label);
		JTextField subt_field=new JTextField(10);
		subt_field.setEditable(false);
		amount=changeTotal();
		subt_field.setText(Integer.toString(amount));
		math_panel.add(subt_field);
		JLabel disc_label=new JLabel("Discount");
		math_panel.add(disc_label);
		MaskFormatter disc_format = null;
		try {
			disc_format = new MaskFormatter("##.##");
			} catch (ParseException e1) {
				e1.printStackTrace();
				}

		JFormattedTextField disc_field = new JFormattedTextField(disc_format);
		disc_field.setText("00.00");
		math_panel.add(disc_field);

		JLabel total_label=new JLabel("Total");
		math_panel.add(total_label);
		JTextField total_field=new JTextField(10);
		total_field.setEditable(false);
		total_field.setText(Integer.toString(amount));
		math_panel.add(total_field);
		disc_field.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		      }

		      public void keyTyped(KeyEvent e) {
		      }

		      public void keyPressed(KeyEvent e) {
		    	  switch(e.getKeyCode()) {
		    	  	case KeyEvent.VK_BACK_SPACE:
		    		  break;
		    	  	case KeyEvent.VK_ENTER:
		    			Float disc1=Float.valueOf(disc_field.getText());
		    			amount=(int) (amount*(100-disc1)/100);
		    			total_field.setText(Integer.toString(amount));
		    	  		break;
		    	  }
		      }
		    });
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i=items.getSelectedRow();
				if(i!=-1) {
					ViewElement.cart.remove(i);
					tableModel.removeRow(i);
					amount=changeTotal();
					subt_field.setText(Integer.toString(amount));
	    			Float disc1=Float.valueOf(disc_field.getText());
					amount=(int) (amount*(100-disc1)/100);
	    			total_field.setText(Integer.toString(amount));
				}
				
			}
			
		});
		invoice_info.add(math_panel);
		table_panel.add(math_panel);
		invoice_info.add(table_panel);
		JPanel action =new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton clear=new JButton("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				name_field.setText("");
				phone_field.setText("");
				email_field.setText("");
				invoice--;
				tableModel.setRowCount(0);
				ViewElement.cart.clear();
				subt_field.setText(Integer.toString(changeTotal()));
				disc_field.setValue("00.00");
				total_field.setText(Integer.toString(changeTotal()));
			}
			
		});
		JButton back=new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SearchPage().setVisible(true);
				
			}
		});
		JButton pay=new JButton("Purchase");
		pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(name_field.getText().equals("") || phone_field.getText().equals("")||email_field.getText().equals("")||ViewElement.cart.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Missing information", getWarningString(), JOptionPane.WARNING_MESSAGE);
				}else {
					Customer customer =new Customer(name_field.getText(),email_field.getText(),Integer.parseInt(phone_field.getText()));
				sendMail(customer,ViewElement.cart);	
				ViewElement.cart.clear();
				invoice++;
				}
			}
		});
		
		action.add(back);action.add(clear);action.add(pay);
		add(billing_header,BorderLayout.NORTH);
		add(invoice_info,BorderLayout.CENTER);
		add(action,BorderLayout.SOUTH);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000,650);
		setLocation(dim.width/2-getWidth()/2,dim.height/2-getHeight()/2);
		setVisible(true);
		
	}


	private int changeTotal() {
		amount=0;
		for(int i=0;i<ViewElement.cart.size();i++) {
			Laptop temp=(Laptop)ViewElement.cart.get(i);
			amount+=temp.price;
		}
		return amount;
	}

	
	
}
