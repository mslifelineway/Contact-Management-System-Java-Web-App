
package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.UserDAO;
import com.oyedost.contactapp.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDAOSave {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserDAO userDAO = ctx.getBean(UserDAO.class);
        User u = new User();
        
        u.setUserName("Mankesh kumar");
        u.setUserAddress("Harinagar Bihar");
        u.setUserEmail("mankesh03@gmail.com");
        u.setUserPassword("mankesh@123");
        u.setUserPhone("12093833");
        u.setUserRole(1);// Admin role
        u.setUserLoginName("mukeshms40003");
        u.setUserLoginStatus(1); // Active status
    userDAO.save(u);
    System.out.print("------------- data inserted ! -------------");
    
    }
    
}
