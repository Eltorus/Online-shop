package by.epam.shop.bean;

import java.util.Date;

public class Order implements java.io.Serializable {
    private static final long serialVersionUID = -2201882877765941819L;
    
    private int id;
    private User user;
    private Cart cart;
    private Date orderDate;
    private Date deliveryDate;
    private String address;
    private double bill;
    private double discount;
    private double total;
    private boolean orderPaid;
    private boolean orderCompleted;

    public double getDiscount() {
	return discount;
    }

    public void setDiscount(double discount) {
	this.discount = discount;
    }

    public double getTotal() {
	return total;
    }

    public void setTotal(double total) {
	this.total = total;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Date getOrderDate() {
	return orderDate;
    }

    public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
	return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
	this.deliveryDate = deliveryDate;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public boolean isOrderPaid() {
	return orderPaid;
    }

    public void setOrderPaid(boolean orderPaid) {
	this.orderPaid = orderPaid;
    }

    public boolean isOrderCompleted() {
	return orderCompleted;
    }

    public void setOrderCompleted(boolean orderCompleted) {
	this.orderCompleted = orderCompleted;
    }

    public double getBill() {
	return bill;
    }

    public void setBill(double bill) {
	this.bill = bill;
    }

    public Cart getCart() {
	return cart;
    }

    public void setCart(Cart cart) {
	this.cart = cart;
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
	Order other = (Order) obj;
	if (id != other.id) {
	    return false;
	}
	if (user == null) {
	    if (other.user != null) {
		return false;
	    }
	}
	if (!user.equals(other.user)) {
	    return false;
	}

	if (cart == null) {
	    if (other.cart != null) {
		return false;
	    }
	}
	if (!cart.equals(other.cart)) {
	    return false;
	}
	if (orderDate == null) {
	    if (other.orderDate != null) {
		return false;
	    }
	}
	if (!orderDate.equals(other.orderDate)) {
	    return false;
	}
	if (deliveryDate == null) {
	    if (other.deliveryDate != null) {
		return false;
	    }
	}
	if (!deliveryDate.equals(other.deliveryDate)) {
	    return false;
	}
	if (address == null) {
	    if (other.address != null) {
		return false;
	    }
	}
	if (!address.equals(other.address)) {
	    return false;
	}
	if (bill != other.bill) {
	    return false;
	}
	if (discount != other.discount) {
	    return false;
	}
	if (total != other.total) {
	    return false;
	}
	if (orderPaid != other.orderPaid) {
	    return false;
	}
	if (orderCompleted != other.orderCompleted) {
	    return false;
	}
	return true;
    }
    
    @Override
    public int hashCode() {
	int result = id * 31 + (user != null ? user.hashCode() : 0) + (cart != null ? cart.hashCode() : 0) 
		+ (orderDate != null ? orderDate.hashCode() : 0) + (deliveryDate != null ? deliveryDate.hashCode() : 0) 
		+ (address != null ? address.hashCode() : 0) + Double.valueOf(bill).hashCode() + Double.valueOf(discount).hashCode() 
		+ Double.valueOf(total).hashCode() + (orderPaid ? 1 : 0) + (orderCompleted ? 1 : 0);
	return result;
    }
    
    @Override
    public String toString() {
	return getClass().getName() + "@" + "id: " + id + user.toString() + cart.toString() 
		+ ", orderDate: " + orderDate.toString() + ", deliveryDate: " + deliveryDate.toString()
		+ ", address: " + address + ", bill: " + bill + ", discount: " + discount + ", total: "
		+ total + ", orderPaid: " + orderPaid + ", orderCompleted: " + orderCompleted;
    }
}
