package by.epam.shop.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.shop.command.admin.AdminPage;
import by.epam.shop.command.authorization.LogOut;
import by.epam.shop.command.authorization.SignUp;
import by.epam.shop.command.authorization.SingIn;
import by.epam.shop.command.authorization.SingInPage;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.localization.Localization;
import by.epam.shop.command.order.AddingToCart;
import by.epam.shop.command.order.DeletingFromCart;
import by.epam.shop.command.order.MakingOrder;
import by.epam.shop.command.order.OrderPage;
import by.epam.shop.command.products.Catalog;
import by.epam.shop.command.products.ProductPage;
import by.epam.shop.command.user.RechargingBalance;

public class CommandProvider {
	private static CommandProvider instance;
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	private CommandProvider() {
		commands.put(ParameterList.CMD_SIGNIN, new SingIn());
		commands.put(ParameterList.CMD_SIGNIN_PG, new SingInPage());
		commands.put(ParameterList.CMD_LOCAL, new Localization());
		commands.put(ParameterList.CMD_LOGOUT, new LogOut());
		commands.put(ParameterList.ADMIN_PAGE, new AdminPage());
		commands.put(ParameterList.CMD_CATALOG,  new Catalog());
		commands.put(ParameterList.CMD_SIGNUP, new SignUp());
		commands.put(ParameterList.CMD_PRODUCT_PG, new ProductPage());
		commands.put(ParameterList.CMD_CART_ADD, new AddingToCart());
		commands.put(ParameterList.CMD_CART_DELETE, new DeletingFromCart());
		commands.put(ParameterList.CMD_TOPBALANCE, new RechargingBalance());
		commands.put(ParameterList.CMD_ORDER_PG, new OrderPage());
		commands.put(ParameterList.CMD_MAKE_ORDER, new MakingOrder());
	}
	
	public static CommandProvider getInstance() {
		if(instance==null) {
			instance = new CommandProvider();
		}
		return instance;
	}

	public Command getCommand(String cmdName) throws CommandException {
		System.out.println(cmdName);
		
		Command command = commands.get(cmdName);
		if(command!=null) {
			return command;
		} else {
			throw new CommandException("Cannot find command");
		}
	}
}