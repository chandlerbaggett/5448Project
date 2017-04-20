package spring.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StaffList {
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
}
