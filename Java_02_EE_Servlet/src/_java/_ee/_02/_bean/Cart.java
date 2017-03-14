package _java._ee._02._bean;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private int userId;
	private final List <Good> goodList = new ArrayList<>();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List <Good> getGoodList() {
		return goodList;
	}
	public void addToGoodList(Good good) {
		this.goodList.add(good);
	}
	public void removeFromCart(Good good) {
		this.goodList.remove(good);
	}
	public void removeFromCartWithInd(int index) {
		this.goodList.remove(index);
	}
	

}
