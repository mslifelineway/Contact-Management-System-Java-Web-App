
package com.oyedost.contactapp.dao;

import com.oyedost.contactapp.domain.User;
import java.util.List;

public interface UserDAO {
    
    public void save(User u);
    public void update(User u);
    public void delete(User u);
    public void delete(Integer userId);
    public User findById(Integer userId);
    public List<User> findAll();
    public List<User> findByProperty(String propName,Object propValue); //-- property means column (i.e by column name)
    
}
