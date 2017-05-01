package spring.formModels;

public class AddMenuItem{
	private int id;
	private int price;
	private String description;

	public int getId(){
		return id;
	}
	
	public void setId(){
		this.id = id;
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(){
		this.price = price;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}
}
