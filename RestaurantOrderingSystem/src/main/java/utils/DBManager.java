package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import spring.config.HibernateUtil;

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
}
