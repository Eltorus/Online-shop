package by.epam.shop.command;

public class CommandList {
    private CommandList() {}
    
    public static final String CMD = "command";

    public static final String CMD_SIGNIN = "sign_in";
    public static final String CMD_SIGNUP = "sign_up";
    public static final String CMD_LOGOUT = "log_out";
    public static final String CMD_LOCAL = "local";
    public static final String CMD_CATALOG = "catalog";
    
    public static final String CMD_PRODUCT_PG = "product_page";
    public final static String CMD_PRODUCT_UPDATE = "update_product";
    public static final String CMD_PRODUCT_DELETE = "delete_product";

    public final static String CMD_CART_ADD = "add_to_cart";
    public final static String CMD_CART_DELETE = "delete_from_cart";

    public final static String CMD_MAKE_ORDER = "make_order";
    public final static String CMD_ORDER_PG = "order_page";
    public final static String CMD_ORDER_CHANGE = "change_order";
    public final static String CMD_ORDER_INFORM = "order_details";
    public final static String CMD_ADMIN_ORDER_PG = "admin_order_page";
    public final static String CMD_ADMIN_PRODUCT_PG = "admin_product_page";
    public final static String CMD_ADMIN_USER_PG = "admin_user_page";
    
    public final static String CMD_USER_UPDATE_BAN = "update_ban_status";
    public final static String CMD_USER_UPDATE_DISCOUNT = "update_user_discount";
    public static final String CMD_TOPBALANCE = "top_balance";
    public final static String CMD_USER_ORDERS = "user_orders";
    public final static String CMD_USER_ORDER_CANCEL = "order_cancel";
    public final static String CMD_USER_AVATAR_UPLOAD = "upload_avatar";
    public final static String CMD_USER_DELETE_PROFILE = "delete_user";

}
