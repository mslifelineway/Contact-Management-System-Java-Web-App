
package com.oyedost.contactapp.services;

import com.oyedost.contactapp.dao.BaseDAO;
import com.oyedost.contactapp.dao.UserDAO;
import com.oyedost.contactapp.domain.User;
import com.oyedost.contactapp.exceptions.UserBlockedException;
import com.oyedost.contactapp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service; 

@Service
public class UserServiceImplementation  extends BaseDAO implements UserServices{
    @Autowired
    private UserDAO userDAO;

    @Override
    public void registerUser(User u) {
       userDAO.save(u);
    }

    @Override
    public User loginUser(String loginName, String loginPassword) throws UserBlockedException{

        String sql = "SELECT * FROM user WHERE userLoginName=:lname AND userPassword=:lpass";
        Map m = new HashMap();
        m.put("lname",loginName);
        m.put("lpass",loginPassword);
        try {
            User u = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            
            if(u.getUserLoginStatus() == UserServices.LOGIN_STATUS_BLOCKED){
                System.out.println("Your Account has been blocked. please visit to Admin.");
                throw new UserBlockedException("Your Account has been blocked. please visit to Admin.");
            }
            else{
                System.out.println("------------------------------------ ^^^^^^^^^^^^^^^^^^^------------ ");
                return u;
            }
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
         String sql = "SELECT * FROM user";
        return getJdbcTemplate().query(sql, new UserRowMapper());
    }

    @Override
    public void blockUser(Integer userId, Integer loginStatus) {
        String sql = "UPDATE user SET userLoginStatus=:ls WHERE userId=:uid";
        Map m = new HashMap();
        m.put("ls",loginStatus);
        m.put("uid",userId);
        getNamedParameterJdbcTemplate().update(sql, m);

    }

    @Override
    public User isUserExists(User u) {
        String check = "SELECT * FROM user WHERE userLoginName=?";
        try {
            System.out.println("---------***********----login name:  "+u.getUserLoginName()+"---------- ");
            User user = getJdbcTemplate().queryForObject(check, new UserRowMapper(), u.getUserLoginName());
            return user;
            
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }
    
}
