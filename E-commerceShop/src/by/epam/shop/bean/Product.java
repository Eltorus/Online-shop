package by.epam.shop.bean;

public class Product {
	private int id;
	private String title;
	private String category;
	private double price;
	private String description;
	private int amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(this == obj) {
			return true;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		if(id != other.id) {
			return false;
		}
		if(title == null) {
			if(other.title != null) {
				return false;
			}
		}
		if(!title.equals(other.title)) {
			return false;
		}
		if(category == null) {
			if(other.category != null) {
				return false;
			}
		}
		if(!category.equals(other.category)) {
			return false;
		}
		if(price != other.price) {
			return false;
		}
		if(description == null) {
			if(other.description != null) {
				return false;
			}
		}
		if(!description.equals(other.description)) {
			return false;
		}
		if(amount != other.amount) {
			return false;
		}
		return true;
	}
}
