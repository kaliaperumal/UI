package com.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{		
			sessionFactory=new Configuration().configure().buildSessionFactory();
			System.out.println("After Build SessionFactory....................");
		}		
		return sessionFactory;		
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory=HibernateUtil.sessionFactory;
	}
}
