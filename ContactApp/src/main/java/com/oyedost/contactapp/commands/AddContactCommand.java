/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oyedost.contactapp.commands;

import com.oyedost.contactapp.domain.Contact;

/**
 *
 * @author janeman
 */
public class AddContactCommand {
    Contact contact = new Contact();

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    
    
}
