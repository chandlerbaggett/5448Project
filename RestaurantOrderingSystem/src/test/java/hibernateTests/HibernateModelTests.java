package hibernateTests;

import org.junit.Test;

import hibernateTests.utils.ModelBuilder;
import hibernateTests.utils.ModelVerifier;
import spring.models.OrderHistory;
import spring.models.Permission;
import spring.models.StaffList;
import spring.models.StaffMember;
import spring.models.User;
import utils.DBManager;

public class hibernateModelTests {
   @Test
   public void testUserModel() {
	   OrderHistory history = ModelBuilder.buildOrderHistory();
	   
	   User s = ModelBuilder.buildUser("steve", "", history);
	   DBManager.saveModel(s);
	   User r = DBManager.getUser("steve");
		
	   ModelVerifier.verifyUsers(s, r);
   }
   
   @Test
   public void testStaffMemberModel() {
	   OrderHistory history = ModelBuilder.buildOrderHistory();
	   User user = ModelBuilder.buildUser("greg", "", history);
	   Permission permission = ModelBuilder.buildPermission();
	   
	   StaffMember member = ModelBuilder.buildStaffMember(user, permission);
	   DBManager.saveModel(member);
	   
	   StaffMember member2 = (StaffMember) DBManager.getModel(StaffMember.class, member.getId());
	   
	   ModelVerifier.verifyStaffMembers(member, member2);
   }
   
   @Test
   public void testStaffListModel() {
	   StaffList list1 = ModelBuilder.buildStaffList();
	   
	   for (int x=0; x < 3; x++) {
		   OrderHistory history = ModelBuilder.buildOrderHistory();
		   User user = ModelBuilder.buildUser("greg"+x, "", history);
		   Permission permission = ModelBuilder.buildPermission();
		   
		   list1.addStaffMember(user, permission);
	   }
	   
	   DBManager.saveModel(list1);
	   
	   StaffList list2 = (StaffList) DBManager.getModel(StaffList.class, list1.getId());
	   
	   ModelVerifier.verifyStaffLists(list1, list2);
   }
}
