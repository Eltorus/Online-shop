package by.epam.shop.dao;

public class QueryList {
    private QueryList() {
    }

    public static final String AddUserQuery = "INSERT INTO `online_shop`.`clients` "
                                	    + "(`c_id`, `c_name`, `c_surname`,`c_email`,`c_password`,`c_phonenumber`,`c_isbanned`,`is_admin`) "
                                	    + "VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
    public static final String GetUserQuery = "SELECT `c`.`c_id`, `c_name`, `c_surname`,`c_email`,`c_phonenumber`,`c_isbanned`,`is_admin`,`discount_coefficient`,`c_balance`,`c_password` "
                                	    + "FROM `online_shop`.`clients` as `c` " + "JOIN `online_shop`.`discounts` as `d` "
                                	    + "ON  `c`.`c_id` = `d`.`c_id` ";

    public static final String GetUserQueryLogin = "WHERE `c_email`=? && `c_password`=?;";
    public static final String GetUserQueryId = "WHERE `c`.`c_id`=?;";

    public static final String UpdateUserQuery = "UPDATE `online_shop`.`clients` " + "SET `c_balance`=? "
	    + "WHERE `c_email`=? && `c_password`=?";

    /*
     * public static final String SetBalanceQuery_P = "SET `c_balance`=? ";
     * 
     * public static final String WhereCondUserQuery_P =
     * "WHERE `c_email`=? && `c_password`=?";
     */

    public static final String DeleteUserQuery = "";
    public static final String GetAllUsersQuery = "SELECT `c`.`c_id`, `c_name`, `c_surname`,`c_email`,`c_phonenumber`,`c_isbanned`,`is_admin`,`discount_coefficient`,`c_balance`,`c_password` "
	    + "FROM `online_shop`.`clients` as `c` " + "JOIN `online_shop`.`discounts` as `d` "
	    + "ON  `c`.`c_id` = `d`.`c_id`; ";

    // Products
    public static final String AddProductQuery = "INSERT  INTO `online_shop`.`products` "
                                        	    + "(`p_id`,`p_title`,`category_id` ,`p_price`,`p_description`,`p_amount`) "
                                        	    + "VALUES (DEFAULT, ?, ?, ?, ?, ?); ";
    
    public static final String UpdateProductQuery = "UPDATE `online_shop`.`products` "
    						  + "SET `p_title`=?, `category_id`=?, `p_price`=?,`p_description`=?,`p_amount`=? "
    						  + "WHERE p_id=?; ";

    public static final String GetProductQuery = "SELECT `p_id`,`p_title`,`category`,`p_price`,`p_description`,`p_amount`,`p`.`category_id` "
                                        	+ "FROM `online_shop`.`products` AS `p` "
                                        	+ "JOIN `online_shop`.`categories` AS `cat` "
                                        	+ "ON `p`.`category_id` = `cat`.`c_id`";

    public static final String GetProductWithIdQuery = "WHERE `p`.`p_id` =?";

    public static final String GetAllProductsQuery = "SELECT `p_id`,`p_title`,`category`,`p_price`,`p_description`,`p_amount`, `p`.`category_id` "
	    + "FROM `online_shop`.`products` AS `p` " + "JOIN `online_shop`.`categories` AS `cat` "
	    + "ON `p`.`category_id` = `cat`.`c_id`;";

    public static final String AddOrderQuery = "INSERT INTO `online_shop`.`orders` "
	    + "(`o_id`, `c_id`, `o_order_date`,`o_delivery_date`, `o_address`,`o_bill`,`o_ispaid`,`o_iscompleted`) "
	    + "VALUES (DEFAULT, ?, NOW(), ?, ?, ?, ?, DEFAULT); ";

    public static final String AddOrderedProductsQuery = "INSERT INTO `online_shop`.`ordered_products` "
	    + "(`order_id`, `product_id`, `product_quantity`) " + "VALUES (LAST_INSERT_ID(), ?, ?); ";

    public static final String DecreaseProductAmountQuery = "UPDATE `online_shop`.`products` SET p_amount=p_amount-? "
	    + "WHERE p_id=?; ";

    public static final String GetAllOrdersQuery = "SELECT `o_id`, `c_id`, `o_order_date`,`o_delivery_date`, `o_address`,`o_bill`,`o_ispaid`,`o_iscompleted` "
	    + "FROM `online_shop`.`orders`";

    public static final String GetOrderQuery = "SELECT `o_id`, `c_id`, `o_order_date`,`o_delivery_date`, `o_address`,`o_bill`,`o_ispaid`,`o_iscompleted` "
	    + "FROM `online_shop`.`orders` AS `ord`" + "WHERE `o_id`=?;";

    public static final String GetOrderedProducts = "SELECT `p_id`,`p_title`,`p_price`, `product_quantity` "
	    + "FROM `online_shop`.`products` AS `p` " + "JOIN `online_shop`.`ordered_products` AS `ordpr` "
	    + "ON `p`.`p_id` = `ordpr`.`product_id` " + "WHERE `ordpr`.`order_id` =?";

    public static final String UpdateOrderQuery = "UPDATE `online_shop`.`orders` "
	    + "SET `o_delivery_date`=?, `o_address`=?, `o_iscompleted`=? " + "WHERE o_id=?; ";
}
