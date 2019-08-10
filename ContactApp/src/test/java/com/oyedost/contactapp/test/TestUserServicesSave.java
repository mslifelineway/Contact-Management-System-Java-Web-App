
package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.UserDAO;
import com.oyedost.contactapp.domain.User;
import com.oyedost.contactapp.services.UserServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserServicesSave {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        UserServices userServices = ctx.getBean(UserServices.class);
        User u = new User();
        
        u.setUserName("Janeman");
        u.setUserAddress("Janampur Bihar");
        u.setUserEmail("janeman999@gmail.com");
        u.setUserPassword("janeman@123");
        u.setUserPhone("7015720216");
        u.setUserRole(UserServices.ADMIN_ROLE);// Admin role
        u.setUserLoginName("janeman999");
        u.setUserLoginStatus(UserServices.LOGIN_STATUS_ACTIVE); // Active status
    userServices.registerUser(u);
    System.out.print("------------- User Registered Successfully! -------------");
    
    }
    
}
