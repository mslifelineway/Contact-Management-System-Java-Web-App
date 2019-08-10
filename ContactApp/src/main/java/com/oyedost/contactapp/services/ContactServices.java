package com.oyedost.contactapp.services;

import com.oyedost.contactapp.domain.Contact;
import java.util.List;

public interface ContactServices {

    public void saveContact(Contact c);

    public void updateContact(Contact c);

    public void deleteContact(Integer contactId);//----- deleting single contact at a time

    public void deleteContact(Integer[] contactIds); //-- deleting multiple contact (Bulk) at a time

    public List<Contact> searchContact(Integer userId); //--- listing all the contacts of a particular user

    public List<Contact> searchContact(Integer userId, String text); //--- listing or searching users with name or a text value

    public Contact findContactByContactId(Integer contactId);
   
    /**
        * In this method, Contact data will be verified, 
        * hint : two or more user can not have same contact
     * @param c
     * @return 
        */
    public Contact validateContact(Contact c);
}
