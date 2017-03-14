package _java._ee._02._dao;

public class QueryList {
	private QueryList() {}
	
	public static final String AddUserQuery = "INSERT INTO `online_shop`.`clients` "
											+ "(`c_id`, `c_name`, `c_surname`,`c_email`,`c_password`,`c_phonenumber`,`c_isbanned`,`is_admin`) "
											+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
	public static final String GetUserQuery = "SELECT `c`.`c_id`, `c_name`, `c_surname`,`c_email`,`c_phonenumber`,`c_isbanned`,`is_admin`,`discount_coefficient`,`c_balance`,`c_password` "
											+ "FROM `online_shop`.`clients` as `c` "
											+ "JOIN `online_shop`.`discounts` as `d` "
											+ "ON  `c`.`c_id` = `d`.`c_id` "
											+ "WHERE `c_email`=? && `c_password`=?";
	public static final String RechargeBalanceQuery = "UPDATE `online_shop`.`clients` "
													+ "SET `c_balance`=? "
													+ "WHERE `c_email`=? && `c_password`=?";
	public static final String DeleteUserQuery = "DELETE FROM `online_shop`.`clients` "
											   + "WHERE `c_email`=? && `c_password`=?";
	public static final String GetAllUsersQuery = "";
	
	//Goods
	public static final String GetGoodQuery = "SELECT `g_id`,`g_title`,`category`,`g_price`,`g_description`,`g_amount` "
											+ "FROM `online_shop`.`goods` AS `g` "
											+ "JOIN `online_shop`.`categories` AS `cat` "
											+ "ON `g`.`category_id` = `cat`.`c_id` ";
	
	public static final String GetGoodWithIdQuery = "WHERE `g`.`g_id` =?";
												
	
	public static final String GetAllGoodsQuery = "SELECT `g_id`,`g_title`,`category`,`g_price`,`g_description`,`g_amount` "
												+ "FROM `online_shop`.`goods` AS `g` "
												+ "JOIN `online_shop`.`categories` AS `cat` "
												+ "ON `g`.`category_id` = `cat`.`c_id` "
												+ "WHERE `g`.`g_amount` <> 0";
}
