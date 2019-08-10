
package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.UserDAO;
import com.oyedost.contactapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAODelete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        userDAO.delete(2);
    System.out.println("---------- Data Deleted Successfully! ------------");
    
    }
    
}
