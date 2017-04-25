package spring.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User extends Model {
	private String displayName;
	private String userName;
	private String password;
	
	@Embedded
	private OrderHistory orderHistory;
	
	
	public User() {
		super();
	}

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
