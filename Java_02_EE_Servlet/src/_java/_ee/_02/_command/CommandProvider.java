package _java._ee._02._command;

import java.util.HashMap;
import java.util.Map;

import _java._ee._02._command.admin.AdminPage;
import _java._ee._02._command.authorization.LogOut;
import _java._ee._02._command.authorization.SignInPage;
import _java._ee._02._command.authorization.SignUp;
import _java._ee._02._command.authorization.SignUpPage;
import _java._ee._02._command.authorization.SingIn;
import _java._ee._02._command.exception.CommandException;
import _java._ee._02._command.goods.Catalog;
import _java._ee._02._command.goods.ProductPage;
import _java._ee._02._command.localization.Localization;
import _java._ee._02._command.order.AddingToCart;
import _java._ee._02._command.order.CartPage;
import _java._ee._02._command.order.DeletingFromCart;
import _java._ee._02._command.user.RechargingBalance;
import _java._ee._02._command.user.UserPage;

public class CommandProvider {
	private static CommandProvider instance;
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	private CommandProvider() {
		commands.put(ParameterList.CMD_SIGNIN, new SingIn());
		commands.put(ParameterList.CMD_LOCAL, new Localization());
		commands.put(ParameterList.CMD_LOGOUT, new LogOut());
		commands.put(ParameterList.ADMIN_PAGE, new AdminPage());
		commands.put(ParameterList.CMD_CATALOG,  new Catalog());
		commands.put(ParameterList.CMD_SIGNUP, new SignUp());
		commands.put(ParameterList.CMD_GOOD_PG, new ProductPage());
		commands.put(ParameterList.CMD_SIGNIN_PG, new SignInPage());
		commands.put(ParameterList.CMD_CART, new CartPage());
		commands.put(ParameterList.CMD_CART_ADD, new AddingToCart());
		commands.put(ParameterList.CMD_SIGNUP_PG, new SignUpPage());
		commands.put(ParameterList.CMD_CART_DELETE, new DeletingFromCart());
		commands.put(ParameterList.USER_PAGE, new UserPage());
		commands.put(ParameterList.CMD_TOPBALANCE, new RechargingBalance());
	}
	
	public static CommandProvider getInstance() {
		if(instance==null) {
			instance = new CommandProvider();
		}
		return instance;
	}

	public Command getCommand(String cmdName) throws CommandException {
		System.out.println(cmdName);
		Command command = null;
		command = commands.get(cmdName);
		if(command!=null) {
			return command;
		} else {
			throw new CommandException("Cannot find command");
		}
	}
}
