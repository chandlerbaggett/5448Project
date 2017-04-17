package spring.models;

public class User {
	private String displayName;
	private String userName;
	private String password;
	private OrderHistory orderHistory;
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean doesPasswordMatch(String input) {
		return password.equals(input);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public OrderHistory getOrderHistory() {
		return orderHistory;
	}
	
	public void setOrderHistory(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}
}
