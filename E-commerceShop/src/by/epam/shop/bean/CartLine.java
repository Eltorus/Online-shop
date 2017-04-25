package by.epam.shop.bean;

public class CartLine implements java.io.Serializable {
    private static final long serialVersionUID = -8933905883667025453L;
    
    private Product product;
    private int quantity;

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
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
	CartLine other = (CartLine) obj;
	if(product == null) {
	    if(other.product != null) {
		return false;
	    }
	}
	if(!product.equals(other.product)) {
	    return false;
	}
	if(quantity != other.quantity) {
	    return false;
	}
	return true;
    }
    
    @Override
    public int hashCode() {
	int result = 31 * (product != null ? product.hashCode() : 0) + quantity;
	return result;
    }
    
    @Override
    public String toString() {
	return getClass().getName() + "@" + product.toString() + ", quantity: " + quantity;
    }
}
