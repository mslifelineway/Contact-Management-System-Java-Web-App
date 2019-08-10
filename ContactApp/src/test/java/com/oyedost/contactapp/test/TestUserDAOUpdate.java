
package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.UserDAO;
import com.oyedost.contactapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOUpdate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u = new User();
        
        u.setUserName("Om kumar");
        u.setUserAddress("Harinagar India");
        u.setUserEmail("OmShiv03@gmail.com"); 
        u.setUserPhone("222222");
        u.setUserRole(0);// Admin role 
        u.setUserLoginStatus(0); // Active status
        u.setUserId(2);
    userDAO.update(u);
    System.out.print("------------- data updated ! -------------");
    
    }
    
}
