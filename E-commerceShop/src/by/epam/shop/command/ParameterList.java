package by.epam.shop.command;

public final class ParameterList {
	private ParameterList() {}
	
	public static final String CMD = "command";
	
	public static final String CMD_SIGNIN = "sign_in";
	public static final String CMD_SIGNIN_PG = "sign_in_page";
	
	public static final String CMD_SIGNUP = "sign_up";
	
	public static final String CMD_LOGOUT = "log_out";
	
	public static final String CMD_LOCAL = "local";
	
	public static final String ADMIN_PAGE = "admin_page";
	
	public static final String USER_PAGE = "user_page";
	public static final String CMD_TOPBALANCE = "top_balance";
	public static final String CMD_CREDIT = "credit";
	public static final String CMD_USER_ADDRESS = "address";
	public static final String USER_ID="user_id";
	public static final String USER_NAME ="name";
	public static final String USER_SURNAME="surname";
	public static final String USER_PHONE="phone";
	public static final String USER_PSWRD = "password";
	public static final String USER_EMAIL = "email";
	public static final String USER_BANNED = "user_banned";
	public static final String USER_PSWRD_CONFIRM="password_confirm";
	
	public static final String CMD_CATALOG = "catalog";
	
	public static final String CMD_PRODUCT_PG = "product_page";
	public static final String PRODUCT_ID = "product_id";
	public static final String PRODUCT_TITLE = "product_title";
	public static final String PRODUCT_CATEGORY="category";
	public static final String PRODUCT_CATEGORY_ID = "category_id";
	public static final String PRODUCT_PRICE = "price";
	public static final String PRODUCT_DESCRIPTION = "description";
	public static final String PRODUCT_AMOUNT = "amount";
	public static final String PRODUCT_IMG_UPLOAD = "upload_product_img";
	public static final String PRODUCT_IMG = "pr-img";
	public static final String PRODUCT_IMG_PATH = "product_img_path";

	public static final String CMD_PRODUCT_INDX = "product_index";
	public final static String CMD_PRODUCT_ADD = "add_product";
	public final static String CMD_PRODUCT_UPDATE="update_product";
	public static final String CMD_PRODUCT_DELETE="delete_product";
	
	public final static String CMD_CART_ADD = "add_to_cart";
	public final static String CMD_CART_DELETE = "delete_from_cart";
	
	
	public final static String CMD_MAKE_ORDER = "make_order";
	public final static String CMD_ORDER_PG = "order_page";
	public final static String ADDRESS = "address";
	public final static String DELIVERY_DATE = "delivery_date";
	
	public final static String CMD_ADMIN_ORDER_PG = "admin_order_page";
	public final static String CMD_ADMIN_PRODUCT_PG = "admin_product_page";
	public final static String CMD_ADMIN_USER_PG = "admin_user_page";
	
	public final static String CMD_ORDER_INFORM = "order_details";
	public final static String CMD_ORDER_CHANGE = "change_order";
	public final static String CMD_ORDER_ID = "order_id";
	public final static String CMD_USER_UPDATE = "user_update";
	public final static String CMD_USER_ORDERS = "user_orders";
	public final static String CMD_USER_ORDER_CANCEL = "order_cancel";
	public final static String CMD_USER_AVATAR_UPLOAD= "upload_avatar";
	public final static String CMD_USER_DELETE_PROFILE="delete_user";

	public final static String CMD_ORDER_IS_COMPLITED= "isOrderComplited";
	public final static String CMD_ORDER_COMPLITED= "Complited";
}
