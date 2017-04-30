package utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import spring.config.HibernateUtil;
import spring.models.MenuItem;
import spring.models.Order;
import spring.models.RealRestaurant;
import spring.models.Restaurant;
import spring.models.RestaurantProxy;
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
	
	public static void deleteModel(Object object)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		

	    //session = sessionFactory.getCurrentSession();
	    //myObject = (MyObject)session.load(MyObject.class,id);
	    //session.delete(myObject);
		session.delete(object);
	    //This makes the pending delete to be done
	    //session.flush() ;
		session.getTransaction().commit();	
		session.close();	
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
	
	public static MenuItem getMenuItem(String name) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		Query<MenuItem> query=  session.createQuery("from MenuItem where name = '"+name+"'");

		MenuItem item = (MenuItem) query.uniqueResult();
		
		session.getTransaction().commit();	
		session.close();	
		
		return item;
	}
	
	public static Restaurant getRestaurant() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();	
		session.beginTransaction();
		
		Query<RealRestaurant> query=  session.createQuery("from RealRestaurant");

		query.setFirstResult(0);
		query.setMaxResults(1);
		List<RealRestaurant> restaurantList = query.list();
		
		session.getTransaction().commit();	
		session.close();	
		
		if (restaurantList.size() == 0) {
			return null;
		}
		
		RealRestaurant restaurant = restaurantList.get(0);
		return new RestaurantProxy(restaurant);	
	}
	
	public static User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return getUser(authentication.getName());
	}
}
