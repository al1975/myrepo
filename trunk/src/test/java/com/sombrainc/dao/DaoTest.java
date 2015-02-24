package com.sombrainc.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {
    //@Test
	   public void testCreateUserDao() {
		      ApplicationContext context = 
		             new ClassPathXmlApplicationContext("applicationContext.xml");
//		      UserDAO g;
//		      UserDAOImpl userDAOImpl = 
//		      (UserDAOImpl)context.getBean("userDAO",UserDAO.class);
		      
		      System.out.println("------Creation User--------" );
		      //userDAOImpl.createUserReturnId(new User("Login","Password","Vasya","Pupkin"));
		   }
}
