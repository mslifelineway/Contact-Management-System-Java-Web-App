
package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.UserDAO;
import com.oyedost.contactapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOFindSingleRecord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u =userDAO.findById(1);
    System.out.println("---------- User Details ------------");
        System.out.println(u.getUserId());
        System.out.println(u.getUserName());
        System.out.println(u.getUserEmail());
        System.out.println(u.getUserPhone());
        System.out.println(u.getUserLoginName());
        System.out.println(u.getUserAddress());
         System.out.println(u.getUserRole());
          System.out.println(u.getUserLoginStatus());
    
    }
    
}
