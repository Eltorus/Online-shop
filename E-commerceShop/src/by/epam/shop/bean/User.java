package by.epam.shop.bean;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String passwordHash;
    private String phonenumber;
    private double balance;
    private boolean is_banned;
    private boolean is_admin;
    private double discountCoefficient;
    private String imgPath;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhonenumber() {
	return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
    }

    public boolean isIs_banned() {
	return is_banned;
    }

    public void setIs_banned(boolean is_banned) {
	this.is_banned = is_banned;
    }

    public boolean isIs_admin() {
	return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
	this.is_admin = is_admin;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public double getDiscountCoefficient() {
	return discountCoefficient;
    }

    public void setDiscountCoefficient(double discountCoefficient) {
	this.discountCoefficient = discountCoefficient;
    }

    public double getBalance() {
	return balance;
    }

    public void setBalance(double balance) {
	this.balance = balance;
    }

    public String getPasswordHash() {
	return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
    }
    
    public String getImgPath() {
	return imgPath;
    }

    public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
    }

    @Override
    public String toString() {
	return this.id + this.email + this.name + this.surname + this.phonenumber;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (this == obj) {
	    return true;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	User other = (User) obj;
	if (id != other.id) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	}
	if (!name.equals(other.name)) {
	    return false;
	}
	if (surname == null) {
	    if (other.surname != null) {
		return false;
	    }
	}
	if (!surname.equals(other.surname)) {
	    return false;
	}
	if (email == null) {
	    if (other.email != null) {
		return false;
	    }
	}
	if (!email.equals(other.email)) {
	    return false;
	}
	if (passwordHash == null) {
	    if (other.passwordHash != null) {
		return false;
	    }
	}
	if (!passwordHash.equals(other.passwordHash)) {
	    return false;
	}
	if (phonenumber == null) {
	    if (other.phonenumber != null) {
		return false;
	    }
	}
	if (!phonenumber.equals(other.phonenumber)) {
	    return false;
	}
	if (balance != other.balance) {
	    return false;
	}
	if (discountCoefficient != other.discountCoefficient) {
	    return false;
	}
	if (is_banned != other.is_banned) {
	    return false;
	}
	if (is_admin != other.is_admin) {
	    return false;
	}
	return true;
    }

}
