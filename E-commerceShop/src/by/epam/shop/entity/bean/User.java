package by.epam.shop.entity.bean;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = 3985751944667531750L;
    
    private int id;
    private String name;
    private String surname;
    private String email;
    private String passwordHash;
    private String phonenumber;
    private double balance;
    private boolean banned;
    private boolean admin;
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
    public boolean isBanned() {
	return banned;
    }

    public void setBanned(boolean banned) {
	this.banned = banned;
    }

    public boolean isAdmin() {
	return admin;
    }

    public void setAdmin(boolean admin) {
	this.admin = admin;
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
	if (banned != other.banned) {
	    return false;
	}
	if (admin != other.admin) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	int result = id * 31 + (name != null ? name.hashCode() : 0) + (surname != null ? surname.hashCode() : 0) + 
		(email != null ? email.hashCode() : 0) + (passwordHash != null ? passwordHash.hashCode() : 0) +
		(phonenumber != null ? phonenumber.hashCode() : 0) + Double.valueOf(balance).hashCode() + 
		(banned ? 1: 0) + (admin ? 1 : 0) + Double.valueOf(discountCoefficient).hashCode() + (imgPath != null ? imgPath.hashCode() : 0);
	return result;
    }
    
    @Override
    public String toString() {
	return getClass().getName() + "@" + "id: " + id + ", name: " + name + ", surname: " + surname + ", email: "+
			email + ", passwordHash: " + passwordHash + ", phonenumber: " + phonenumber + ", balance: " + 
			balance + ", banned: " + banned + ", admin: " + admin + ", discountCoefficient: "
			+ discountCoefficient + ", imgPath:" + imgPath;
    
    }

}
