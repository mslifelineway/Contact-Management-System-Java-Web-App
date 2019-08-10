package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.ContactDAO;
import com.oyedost.contactapp.domain.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestContactDAOSave {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        ContactDAO contactDAO = ctx.getBean(ContactDAO.class);
        Contact c = new Contact();

        c.setUserId(4);
        c.setName("Mahesh kumar");
        c.setAddress(" Bihar");
        c.setEmail("mahesh@gmail.com");
        c.setPhone("12093833");
        c.setRemark("friend");
        contactDAO.save(c);
        System.out.print("-------------contact data inserted ! -------------");

    }

}
