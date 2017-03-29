package by.epam.shop.bean;

import java.sql.Timestamp; ///заменить 

public class Order {
	private int id;
	private User user;
	private Cart cart;
	private Timestamp orderDate;
	private Timestamp deliveryDate;
	private String address;
	private double bill;
	private boolean orderPaid;
	private boolean orderCompleted;
	
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
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Timestamp deliveryDate) {
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
}
