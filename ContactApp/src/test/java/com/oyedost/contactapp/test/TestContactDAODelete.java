package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.ContactDAO;
import com.oyedost.contactapp.domain.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestContactDAODelete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        ContactDAO contactDAO = ctx.getBean(ContactDAO.class);
        contactDAO.delete(2);
        System.out.println("------------- One Record from Contact Table has been deleted! -------");
    }

}
