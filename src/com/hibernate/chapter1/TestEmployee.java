package com.hibernate.chapter1;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestEmployee {

	public static void main(String[] args) {

		try {
		Configuration config = new Configuration();
		config.addAnnotatedClass(Employee.class);
		
		config.configure("hibernate.cfg.xml");
		
		new SchemaExport(config).create(true, true);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
