package e_store;

public class Laptop {

	//fields
	protected int id,price,quantity,supplier;
	protected String name,cpu,ram,screen,battery,windows,storage,color,image,vga;
	
	//constructor
	public Laptop(int id, String name,int quantity,  int supplier,int price, String cpu, String ram, String screen, String battery,
			String windows, String storage, String color, String image,String vga) {
		
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.supplier = supplier;
		this.name = name;
		this.ram = ram;
		this.cpu=cpu;
		this.screen = screen;
		this.battery = battery;
		this.vga=vga;
		this.windows = windows;
		this.storage = storage;
		this.color = color;
		this.image = image;
	}

	@Override
	public String toString() {
		return  name +" " + cpu +" "+ram+" RAM "+vga+" VGA $"+price;
	}

	
	
}
