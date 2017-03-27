package by.epam.shop.bean;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	private final List <CartLine> productList = new ArrayList<>();
	
	public List <CartLine> getProductList() {
		return productList;
	}
	public void addToProductList(CartLine cartLine) {
		this.productList.add(cartLine);
	}
	public boolean removeFromProductList(CartLine cartLine) {
		return this.productList.remove(cartLine);
	}
	public void removeFromListWithIndx(int index) {
		this.productList.remove(index);
	}

}
