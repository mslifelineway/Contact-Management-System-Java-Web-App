package com.oyedost.contactapp.commands;

import com.oyedost.contactapp.domain.User;

/**
 * The number of variables or fields equal to the number of fields available in
 * the corresponding forms --> or form input
 *
 * @author janeman
 */
public class RegisterCommand {

    User user = new User();
    
    //TODO : add more fields if required
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
