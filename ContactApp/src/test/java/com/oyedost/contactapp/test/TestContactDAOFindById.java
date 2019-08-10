package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.ContactDAO;
import com.oyedost.contactapp.domain.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestContactDAOFindById {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        ContactDAO contactDAO = ctx.getBean(ContactDAO.class);
        Contact c = contactDAO.findById(3);
        System.out.println("--------- single record from contact table -------------");
        System.out.println("contact id:"+c.getContactId());
         System.out.println("user id:"+c.getUserId());
          System.out.println("name :"+c.getName());
           System.out.println("email :"+c.getEmail());
            System.out.println("phone :"+c.getPhone());
             System.out.println("remark :"+c.getRemark());
              System.out.println("address :"+c.getAddress());
    }

}
