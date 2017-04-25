package spring.models;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STAFFMEMBERS")
public class StaffMember extends Model {
	@OneToOne(cascade = {CascadeType.ALL})
	private User user;
	
	@Embedded
	private Permission permission;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Permission getPermission() {
		return permission;
	}
	
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
