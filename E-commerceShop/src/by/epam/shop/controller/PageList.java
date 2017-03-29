package by.epam.shop.controller;

public final class PageList {
	private PageList() {}
	
	public static final String PG_ERROR = "/WEB-INF/jsp/error.jsp";
	public static final String PG_INDEX = "/";
	
	public static final String PG_CATALOG = "/WEB-INF/jsp/catalog.jsp";
	public static final String PG_GOOD = "/WEB-INF/jsp/product.jsp";
	public static final String PG_CART = "/cart";
	
	public static final String PG_ORDER = "/order";
	
	public static final String PG_SIGNIN = "/signin";
	public static final String PG_PROFILE = "/profile";
	
	public static final String PG_ORDER_INFO = "/WEB-INF/jsp/administration/orderInfo.jsp";
	public static final String PG_ADMIN_ORDER = "/WEB-INF/jsp/administration/adminOrders.jsp";
	public static final String PG_ADMIN_ORDER_R = "/Controller?command=admin_order_page";
	
	public static final String PG_ADMIN_USER = "/WEB-INF/jsp/administration/adminUsers.jsp";
	public static final String PG_ADMIN_USER_R="/Controller?command=admin_user_page";
	
	public static final String PG_ADMIN_PRODUCT = "/WEB-INF/jsp/administration/adminProducts.jsp";
	public static final String PG_ADMIN_PRODUCT_R = "/Controller?command=admin_product_page";
	
	public static final String PG_ADMIN_PRODUCT_EDIT = "/WEB-INF/jsp/administration/productEdit.jsp";
}
