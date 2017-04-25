package by.epam.shop.jsptag;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.epam.shop.bean.CartLine;

public class JspCartLineTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private CartLine value;
    
    public void setValue(CartLine value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
	JspWriter out = pageContext.getOut();
	try {
	    out.write("<td>" + value.getProduct().getTitle() + "</td>");
	    out.write("<td>" + value.getProduct().getCategory() + "</td>");

	    NumberFormat formatter = new DecimalFormat("#0.00");
	    out.write("<td>" + formatter.format(value.getProduct().getPrice()) + "</td>");
	    out.write("<td>" + value.getQuantity() + "</td>");

	} catch (IOException e) {
	    throw new JspException(e);
	}
	return SKIP_BODY;
    }
}
