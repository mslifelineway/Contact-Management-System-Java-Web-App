
package com.oyedost.contactapp.test;

import com.oyedost.contactapp.config.SpringRootConfig;
import com.oyedost.contactapp.dao.ContactDAO;
import com.oyedost.contactapp.domain.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestContactDAOUpdate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRootConfig.class);
        ContactDAO contactDao = ctx.getBean(ContactDAO.class);
        Contact c = new Contact();
        
        c.setName("Om kumar");
        c.setAddress("Harinagar India");
        c.setEmail("OmShiv03@gmail.com"); 
        c.setPhone("222222");
        c.setRemark("Relative");
        c.setContactId(14);
    contactDao.update(c);
    System.out.print("------------- data updated ! -------------");
    
    }
    
}
