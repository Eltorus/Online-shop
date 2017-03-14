package _java._ee._02._bean;

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
	private boolean loged;
	
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
	public boolean isLoged() {
		return loged;
	}
	public void setLoged(boolean loged) {
		this.loged = loged;
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
	
	@Override
	public String toString() {
		return this.id + this.email + this.name + this.surname + this.phonenumber;
	}
	
	
}
