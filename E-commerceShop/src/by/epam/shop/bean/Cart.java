package by.epam.shop.bean;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cart {
	private final Set <CartLine> productList = new LinkedHashSet<>();
	
	public Set <CartLine> getProductList() {
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
