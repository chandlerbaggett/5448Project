package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import spring.config.HibernateUtil;
import spring.models.Order;
import spring.models.User;

public class DBManager {
	public static void saveModel(Object object) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		session.saveOrUpdate(object);
		
		session.getTransaction().commit();	
		session.close();	
	}
	
	public static Object getModel(Class classType, int id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		Object object = session.get(classType, id);
		
		session.getTransaction().commit();	
		session.close();	
		
		return object;
	}
	
	public static User getUser(String username) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		Query<User> query=  session.createQuery("from User where userName = '"+username+"'");

		User user = (User) query.uniqueResult();
		
		session.getTransaction().commit();	
		session.close();	
		
		return user;
	}
	
	public static Order getOrder() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		Query<Order> query=  session.createQuery("from Order ");

		Order order = (Order) query.uniqueResult();
		
		session.getTransaction().commit();	
		session.close();	
		
		return order;
	}
}
