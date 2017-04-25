package by.epam.shop.bean;

public class Product implements java.io.Serializable {
    private static final long serialVersionUID = -5718487246202293608L;
    
    private int id;
    private String title;
    private String category;
    private int categoryID;
    private double price;
    private String description;
    private int amount;
    private String imgPath;

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

    public int getCategoryID() {
	return categoryID;
    }

    public void setCategoryID(int categoryID) {
	this.categoryID = categoryID;
    }
    
    public String getImgPath() {
	return imgPath;
    }

    public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (this == obj) {
	    return true;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Product other = (Product) obj;
	if (id != other.id) {
	    return false;
	}
	if (title == null) {
	    if (other.title != null) {
		return false;
	    }
	}
	if (!title.equals(other.title)) {
	    return false;
	}
	if (category == null) {
	    if (other.category != null) {
		return false;
	    }
	}
	if (!category.equals(other.category)) {
	    return false;
	}
	if (price != other.price) {
	    return false;
	}
	if (description == null) {
	    if (other.description != null) {
		return false;
	    }
	}
	if (!description.equals(other.description)) {
	    return false;
	}
	if (amount != other.amount) {
	    return false;
	}
	if (imgPath == null) {
	    if (other.imgPath != null) {
		return false;
	    }
	}
	if (!imgPath.equals(other.imgPath)) {
	    return false;
	}
	return true;
    }
    
    @Override
    public int hashCode() {
	int result = 31 * id + (title != null ? title.hashCode() : 0) + (category != null ? category.hashCode() : 0)
		+ categoryID + Double.valueOf(price).hashCode() + (description != null ? description.hashCode() : 0)
		+ amount + (imgPath != null ? imgPath.hashCode() : 0);
	return result;
    }
    
    @Override
    public String toString() {
	return getClass().getName() + "@" + "id: " + id + ", title: " + title + ", category: " + category + 
		", categoryID: " + categoryID + ", price: " + price + ", description: " + description + 
		", amount: " + amount + ", imgPath: " + imgPath;
    }
}
