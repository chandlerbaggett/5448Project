package spring.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STAFFLISTS")
public class StaffList extends Model {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Collection<StaffMember> staffMembers;

	public StaffList() {
		super();
		staffMembers = new ArrayList<StaffMember>();
	}
	
	public void addStaffMember(User user, Permission permission) {
		StaffMember staffMember = new StaffMember();
		staffMember.setUser(user);
		staffMember.setPermission(permission);
		staffMembers.add(staffMember);
	}
	
	public void removeStaffMember(User user) {
		Iterator<StaffMember> iterator = staffMembers.iterator();
		
		while (iterator.hasNext()) {
			StaffMember member = iterator.next();
			
			if (member.getUser().equals(user)) {
				iterator.remove();
				break;
			}
		}
	}
	
	public boolean isStaffMember(User user) {
		Iterator<StaffMember> iterator = staffMembers.iterator();
		
		while (iterator.hasNext()) {
			StaffMember member = iterator.next();
			
			if (member.getUser().equals(user)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Permission getPermissionForStaff(User user){
		Iterator<StaffMember> iterator = staffMembers.iterator();
		
		while (iterator.hasNext()) {
			StaffMember member = iterator.next();
			
			if (member.getUser().equals(user)) {
				return member.getPermission();
			}
		}
		
		return null;
	}

	public Collection<StaffMember> getStaffMembers() {
		return staffMembers;
	}

	public void setStaffMembers(Collection<StaffMember> staffMembers) {
		this.staffMembers = staffMembers;
	}
}
