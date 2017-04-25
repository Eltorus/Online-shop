package by.epam.shop.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.shop.command.admin.AdminOrderPage;
import by.epam.shop.command.admin.AdminProductPage;
import by.epam.shop.command.admin.AdminUserPage;
import by.epam.shop.command.admin.order.OrderEditPage;
import by.epam.shop.command.admin.order.OrderUpdating;
import by.epam.shop.command.admin.product.DeletingProduct;
import by.epam.shop.command.admin.product.ProductUpdating;
import by.epam.shop.command.admin.user.UpdatingUserBanStatus;
import by.epam.shop.command.admin.user.UpdatingUserDiscount;
import by.epam.shop.command.authorization.LogOut;
import by.epam.shop.command.authorization.SignUp;
import by.epam.shop.command.authorization.SignIn;
import by.epam.shop.command.exception.CommandException;
import by.epam.shop.command.localization.Localization;
import by.epam.shop.command.order.AddingToCart;
import by.epam.shop.command.order.DeletingFromCart;
import by.epam.shop.command.order.MakingOrder;
import by.epam.shop.command.order.OrderCanceling;
import by.epam.shop.command.order.OrderPage;
import by.epam.shop.command.product.Catalog;
import by.epam.shop.command.product.ProductPage;
import by.epam.shop.command.user.DeletingUser;
import by.epam.shop.command.user.RechargingBalance;
import by.epam.shop.command.user.UploadUserAvatar;
import by.epam.shop.command.user.UserOrderList;

public class CommandProvider {
    private static CommandProvider instance;
    private static final Map<String, Command> commands = new HashMap<String, Command>();

    private CommandProvider() {
	commands.put(CommandList.CMD_SIGNIN, new SignIn());
	commands.put(CommandList.CMD_LOCAL, new Localization());
	commands.put(CommandList.CMD_LOGOUT, new LogOut());
	commands.put(CommandList.CMD_CATALOG, new Catalog());
	commands.put(CommandList.CMD_SIGNUP, new SignUp());
	commands.put(CommandList.CMD_PRODUCT_PG, new ProductPage());
	commands.put(CommandList.CMD_CART_ADD, new AddingToCart());
	commands.put(CommandList.CMD_CART_DELETE, new DeletingFromCart());
	commands.put(CommandList.CMD_TOPBALANCE, new RechargingBalance());
	commands.put(CommandList.CMD_ORDER_PG, new OrderPage());
	commands.put(CommandList.CMD_MAKE_ORDER, new MakingOrder());
	commands.put(CommandList.CMD_ADMIN_ORDER_PG, new AdminOrderPage());
	commands.put(CommandList.CMD_ORDER_INFORM, new OrderEditPage());
	commands.put(CommandList.CMD_ORDER_CHANGE, new OrderUpdating());
	commands.put(CommandList.CMD_ADMIN_PRODUCT_PG, new AdminProductPage());
	commands.put(CommandList.CMD_PRODUCT_UPDATE, new ProductUpdating());
	commands.put(CommandList.CMD_ADMIN_USER_PG, new AdminUserPage());
	commands.put(CommandList.CMD_USER_UPDATE_BAN, new UpdatingUserBanStatus());
	commands.put(CommandList.CMD_USER_ORDERS, new UserOrderList());
	commands.put(CommandList.CMD_USER_ORDER_CANCEL, new OrderCanceling());
	commands.put(CommandList.CMD_USER_AVATAR_UPLOAD, new UploadUserAvatar());
	commands.put(CommandList.CMD_PRODUCT_DELETE, new DeletingProduct());
	commands.put(CommandList.CMD_USER_DELETE_PROFILE, new DeletingUser());
	commands.put(CommandList.CMD_USER_UPDATE_DISCOUNT, new UpdatingUserDiscount());
    }

    public static CommandProvider getInstance() {
	CommandProvider localInstance = instance;
	if (localInstance == null) {
	    synchronized (CommandProvider.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = localInstance = new CommandProvider();
		}
	    }
	}
	return instance;
    }

    public Command getCommand(String cmdName) throws CommandException {
	Command command = commands.get(cmdName);
	if (command != null) {
	    return command;
	} else {
	    throw new CommandException("Cannot find command");
	}
    }
}
