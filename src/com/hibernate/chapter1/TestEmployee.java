package com.hibernate.chapter1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEmployee {

	private static Logger log = LoggerFactory.getLogger(TestEmployee.class);
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public static void main(String[] args) {
		sessionFactory = configureSessionFactory();
		
		if (sessionFactory == null){
			log.error("Session factory is blank");
		}
		
		Session session = sessionFactory.getCurrentSession();
		
		Employee younus = new Employee();
		younus.setEmpId(100);
		younus.setEmpName("M Younus Raza");
		
		Employee feynman = new Employee();
		feynman.setEmpId(101);
		feynman.setEmpName("Richard Feynman");
		
		Transaction tx = session.getTransaction();
		tx.begin();
		session.save(younus);
		session.save(feynman);
		tx.commit();
	}
	


	private static SessionFactory configureSessionFactory() throws HibernateException {
	   
		Configuration config = new Configuration();
		config.addAnnotatedClass(Employee.class);
		
		config.configure("hibernate.cfg.xml");
		
		new SchemaExport(config).create(true, true);
		
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();        
	    sessionFactory = config.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
}
