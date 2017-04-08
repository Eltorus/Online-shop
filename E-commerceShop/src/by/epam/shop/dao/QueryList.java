package by.epam.shop.dao;

public class QueryList {
    private QueryList() {
    }

    //Users
    public static final String AddUserQuery = "INSERT INTO `online_shop`.`clients` "
                                	    + "(`c_id`, `c_name`, `c_surname`,`c_email`,`c_password`,`c_phonenumber`,`c_isbanned`,`is_admin`) "
                                	    + "VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
    
    public static final String GetUserQuery = "SELECT `c`.`c_id`, `c_name`, `c_surname`,`c_email`,`c_password`,`c_phonenumber`,`c_isbanned`,`is_admin`,`discount_coefficient`,`c_balance`, `c_imgurl` "
                                	    + "FROM `online_shop`.`clients` as `c` " 
                                	    + "LEFT JOIN `online_shop`.`discounts` as `d` "
                                	    + "ON  `c`.`c_id` = `d`.`c_id` ";

    public static final String GetUserQueryLogin_P = "WHERE `c_email`=? && `c_password`=? && `c_deleted`=false;";
    public static final String GetUserQueryId_P = "WHERE `c`.`c_id`=? && `c_deleted`=false;";

    public static final String UpdateUserQuery_P = "UPDATE `online_shop`.`clients` AS `c` ";
    
    public static final String SetBalanceAndImageQuery_P = "SET `c_balance`=?, `c_imgurl`=? ";
    public static final String SetBalanceQuery_P = "SET `c_balance`=`c_balance`-?";
    public static final String SetBannedQuery_P = "SET `c_isbanned`=? ";

    /*
     * public static final String SetBalanceQuery_P = "SET `c_balance`=? ";
     * 
     * public static final String WhereCondUserQuery_P =
     * "WHERE `c_email`=? && `c_password`=?";
     */
    
    public static final String UserBalanceRecoveryQuery = "UPDATE `online_shop`.`clients` "
                                                	+ "SET `c_balance`=`c_balance`+? "
                                                	+ "WHERE c_id=? && `c_deleted`=false; ";
    
    public static final String GetAllUsersQuery = "SELECT `c`.`c_id`, `c_name`, `c_surname`,`c_email`,`c_password`,`c_phonenumber`,`c_isbanned`,`is_admin`,`discount_coefficient`,`c_balance`, `c_imgurl` "
                                        	+ "FROM `online_shop`.`clients` as `c` "
                                        	+ "LEFT JOIN `online_shop`.`discounts` as `d` "
                                        	+ "ON  `c`.`c_id` = `d`.`c_id` "
                                        	+ "WHERE `c_deleted`=false;";
    
    public static final String DeleteUserQuery = "UPDATE `online_shop`.`clients` "
    					       + "SET `c_deleted`=true "
    					       + "WHERE `c_email`=? && `c_password`=?; ";
    

    // Products
    public static final String GetTotalProductAmount = "SELECT COUNT(*) FROM `online_shop`.`products`";
    public static final String GetLimitProductsQuery_P = " LIMIT ?,?;";
    public static final String AddProductQuery = "INSERT  INTO `online_shop`.`products` "
                                               + "(`p_id`,`p_title`,`category_id` ,`p_price`,`p_description`,`p_amount`,`p_imgurl`) "
                                               + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?); ";
    
    public static final String UpdateProductQuery = "UPDATE `online_shop`.`products` "
    						  + "SET `p_title`=?, `category_id`=?, `p_price`=?,`p_description`=?,`p_amount`=?, `p_imgurl`=? "
    						  + "WHERE `p_id`=? && `p_deleted`=false; ";
    
    public static final String ProductAmountRecoveryQuery = "UPDATE `online_shop`.`products` "
	    						  + "SET p_amount=p_amount+? "
	    						  + "WHERE `p_id`=? && `p_deleted`=false; ";
    
    public static final String DecreaseProductAmountQuery = "UPDATE `online_shop`.`products` "
		  					  + "SET p_amount=p_amount-? "
		  					  + "WHERE p_id=? && `p_deleted`=false; ";

    public static final String GetProductQuery = "SELECT `p_id`,`p_title`,`category`,`p_price`,`p_description`,`p_amount`,`p`.`category_id`, `p`.`p_imgurl`"
                                        	+ "FROM `online_shop`.`products` AS `p` "
                                        	+ "JOIN `online_shop`.`categories` AS `cat` "
                                        	+ "ON `p`.`category_id` = `cat`.`c_id`";

    public static final String GetProductWithIdQuery = "WHERE `p`.`p_id` =? && `p_deleted`=false;";

    public static final String GetAllProductsQuery = "SELECT `p_id`,`p_title`,`category`,`p_price`,`p_description`,`p_amount`, `p`.`category_id`,`p`.`p_imgurl` "
                                        	   + "FROM `online_shop`.`products` AS `p` " 
                                        	   + "LEFT JOIN `online_shop`.`categories` AS `cat` "
                                        	   + "ON `p`.`category_id` = `cat`.`c_id` "
                                        	   + "WHERE `p_deleted`=false ";
    
    public static final String DeleteProductQuery = "UPDATE `online_shop`.`products` "
    						  + "SET `p_deleted`=true "
    						  + "WHERE `p_id`=?";
    
    //Orders

    public static final String AddOrderQuery = "INSERT INTO `online_shop`.`orders` "
                                	    + "(`o_id`, `c_id`, `o_order_date`,`o_delivery_date`, `o_address`,`o_bill`,`o_discount`,`o_total`,`o_ispaid`,`o_iscompleted`) "
                                	    + "VALUES (DEFAULT, ?, NOW(), ?, ?, ?, ?, ?, ?, DEFAULT); ";

    public static final String AddOrderedProductsQuery = "INSERT INTO `online_shop`.`ordered_products` "
                                        	    + "(`order_id`, `product_id`, `product_quantity`) " + "VALUES (LAST_INSERT_ID(), ?, ?); ";

    public static final String GetAllOrdersQuery = "SELECT `o_id`, `c_id`, `o_order_date`,`o_delivery_date`, `o_address`,`o_bill`,`o_discount`,`o_total`,`o_ispaid`,`o_iscompleted` "
	    					 + "FROM `online_shop`.`orders` "
	    					 + "WHERE `o_deleted`=false ";

    public static final String GetOrderQuery_P = "SELECT `o_id`, `c_id`, `o_order_date`,`o_delivery_date`, `o_address`,`o_bill`,`o_discount`,`o_total`,`o_ispaid`,`o_iscompleted` "
	    				       + "FROM `online_shop`.`orders` ";
    
    public static final String WhereConditionOrderId_P = "WHERE `o_id`=?;";
    
    public static final String GetOrderWhereConditionUserId_P = "WHERE `c_id`=? && `o_iscompleted`=false && `o_deleted`=false; ";

    public static final String GetOrderedProducts = "SELECT `p_id`,`p_title`,`p_price`, `product_quantity` "
                                        	    + "FROM `online_shop`.`products` AS `p` " 
                                        	    + "JOIN `online_shop`.`ordered_products` AS `ordpr` "
                                        	    + "ON `p`.`p_id` = `ordpr`.`product_id` " 
                                        	    + "WHERE `ordpr`.`order_id` =?";

    public static final String UpdateOrderQuery = "UPDATE `online_shop`.`orders` "
	    					+ "SET `o_delivery_date`=?, `o_address`=?, `o_iscompleted`=? " 
	    					+ "WHERE o_id=?; ";
    
    public static final String DeleteOrderQuery = "UPDATE `online_shop`.`orders` "
                                	        + "SET `o_deleted`=true "
                                	        + "WHERE o_id=?; ";
}
