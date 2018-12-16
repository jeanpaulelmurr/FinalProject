package e_store;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewElement extends JFrame {

	
	public ViewElement(Laptop laptop) {
		super(laptop.name);
		initComponents(laptop);
		
	}

	private void initComponents(Laptop laptop) {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(600, 700);
		setLocation((int)(dim.getWidth()/2-getWidth()/2),(int)(dim.getHeight()/2-getHeight()/2)-10);
		setVisible(true);
		JLabel store_logo=new JLabel();
		ImageIcon ic=new ImageIcon("C:\\Users\\User\\Desktop\\Laptops\\estore.png");
		Image img = ic.getImage();
		Image newimg = img.getScaledInstance((int)this.getWidth(), 150,  java.awt.Image.SCALE_SMOOTH);
		ic = new ImageIcon(newimg);
		JLabel tempImg = new JLabel( ic, JLabel.CENTER);
		add(tempImg,BorderLayout.NORTH);
		JPanel elt_details= new JPanel(new GridLayout(12,2));
		JLabel label_barcode=new JLabel("Barcode: ");
		elt_details.add(label_barcode);
		JTextField barcode_value=new JTextField(Integer.toString(laptop.id));
		barcode_value.setEditable(false);
		elt_details.add(barcode_value);
		JLabel label_name=new JLabel("Name: ");
		elt_details.add(label_name);
		JTextField name_value=new JTextField(laptop.name);
		name_value.setEditable(false);
		elt_details.add(name_value);
		JLabel label_cpu=new JLabel("CPU: ");
		elt_details.add(label_cpu);
		JTextField cpu_value=new JTextField(laptop.cpu);
		cpu_value.setEditable(false);
		elt_details.add(cpu_value);
		JLabel label_ram=new JLabel("RAM: ");
		elt_details.add(label_ram);
		JTextField ram_value=new JTextField(laptop.ram);
		ram_value.setEditable(false);
		elt_details.add(ram_value);
		JLabel label_storage=new JLabel("Hard Disk: ");
		elt_details.add(label_storage);
		JTextField storage_value=new JTextField(laptop.storage);
		storage_value.setEditable(false);
		elt_details.add(storage_value);
		JLabel label_vga=new JLabel("VGA: ");
		elt_details.add(label_vga);
		JTextField vga_value=new JTextField(laptop.vga);
		vga_value.setEditable(false);
		elt_details.add(vga_value);
		JLabel label_screen=new JLabel("Screen: ");
		elt_details.add(label_screen);
		JTextField screen_value=new JTextField(laptop.screen);
		screen_value.setEditable(false);
		elt_details.add(screen_value);
		JLabel label_battery=new JLabel("Battery: ");
		elt_details.add(label_battery);
		JTextField battery_value=new JTextField(laptop.battery);
		battery_value.setEditable(false);
		elt_details.add(battery_value);
		JLabel label_os=new JLabel("OS: ");
		elt_details.add(label_os);
		JTextField os_value=new JTextField(laptop.windows);
		os_value.setEditable(false);
		elt_details.add(os_value);
		JLabel label_color=new JLabel("Color: ");
		elt_details.add(label_color);
		JTextField color_value=new JTextField(laptop.color);
		color_value.setEditable(false);
		elt_details.add(color_value);
		JLabel label_quantity=new JLabel("Quantity: ");
		elt_details.add(label_quantity);
		JSpinner quantity_value=new JSpinner(new SpinnerNumberModel(1,1,laptop.quantity,1));
		elt_details.add(quantity_value);
		JLabel label_price=new JLabel("Price: ");
		elt_details.add(label_price);
		JTextField price_value=new JTextField("$"+Integer.toString(laptop.price));
		price_value.setEditable(false);
		elt_details.add(price_value);
		
		JPanel image_panel=new JPanel();
		ImageIcon ic2=new ImageIcon(laptop.image);
		Image img2 = ic2.getImage();
		Image newimg2 = img2.getScaledInstance(200, 180,  java.awt.Image.SCALE_SMOOTH);
		ic2 = new ImageIcon(newimg2);
		JLabel tempImg2 = new JLabel( ic2, JLabel.CENTER);
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		//tempImg2.setBorder(border);
		
		
		JPanel action = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton add2Cart=new JButton("Add to cart");
		JButton back=new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Frame[] frame=SearchPage.getFrames();
				frame[0].setVisible(true);
				
			}
			
		});
		JButton purchase=new JButton("Proceed to checkout");
		action.add(back);
		action.add(add2Cart);
		action.add(purchase);
		add(action,BorderLayout.SOUTH);
		add(tempImg2,BorderLayout.EAST);
		add(elt_details,BorderLayout.CENTER);
		
	}
	
}
