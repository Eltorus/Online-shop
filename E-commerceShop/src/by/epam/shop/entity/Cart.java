package by.epam.shop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.epam.shop.entity.bean.CartLine;

public class Cart implements Serializable{
    private static final long serialVersionUID = 1L;
    private final List<CartLine> productList = new ArrayList<>();

    public List<CartLine> getProductList() {
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
