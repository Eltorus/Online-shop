package _java._ee._02._bean;

import java.util.Date;

public class Order {
	private int id;
	private int userId;
	private int goodId;
	private Date orderDate;
	private Date deliveryDate;
	private String address;
	private boolean orderPaid;
	private boolean orderCompleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
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
}
