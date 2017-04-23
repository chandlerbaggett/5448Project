package spring.controllers;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import spring.config.HibernateUtil;
import spring.models.Order;

public class Driver {

	public static void main(String[] args) {
	
	// Write the Student_Info object into the database	
	//Student_Info student = new Student_Info();	
	//student.setName("Java Fun");	
	//student.setRoll_no(6);
	//Write the order object into the database
	Order order = new Order();
	//order.setId(1);
	order.setorderId(100);
	order.setOrderStatus("ACTIVE");
	order.setOrderDate(new Date());
	
	Order nextOrder = new Order();
	//nextOrder.setId(1);
	nextOrder.setorderId(200);
	nextOrder.setOrderStatus("ACTIVE");
	nextOrder.setOrderDate(new Date());
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
	//Session session = sessionFactory.getCurrentSession();
	Session session = sessionFactory.openSession();	
	session.beginTransaction();
	
	// this would save the Student_Info object into the database	
	//session.save(student);
	session.save(order);
	session.save(nextOrder);
	
	// to update,	
	//Student_Info studentChange = (Student_Info)session.get("Student_Info", 2);	
	//studentChange.setName("Ralph Buff");	
	//session.update(studentChange);	
	//Order orderChange = (Order)session.get("Order", 2);
	
	
	session.getTransaction().commit();	
	session.close();	
	sessionFactory.close();	

	}

}