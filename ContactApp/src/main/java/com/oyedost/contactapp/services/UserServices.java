package com.oyedost.contactapp.services;

import com.oyedost.contactapp.domain.User; 
import com.oyedost.contactapp.exceptions.UserBlockedException;
import java.util.List;

public interface UserServices {

    public static final Integer LOGIN_STATUS_ACTIVE = 1;
    public static final Integer LOGIN_STATUS_BLOCKED = 2;
    public static final Integer ADMIN_ROLE = 1;
    public static final Integer USER_ROLE = 2;

    public User isUserExists(User u);
    
    public void registerUser(User u);

    public User loginUser(String loginName, String loginPassword) throws UserBlockedException;

    //---- fetching or retrieving all the registered users (mainly used by admins)
    public List<User> getAllUsers();

    public void blockUser(Integer userId, Integer loginStatus);

}
