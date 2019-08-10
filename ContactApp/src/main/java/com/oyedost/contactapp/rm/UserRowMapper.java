
package com.oyedost.contactapp.rm;

import com.oyedost.contactapp.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("userId"));
        u.setUserName(rs.getString("userName"));
        u.setUserEmail(rs.getString("userEmail"));
        u.setUserPhone(rs.getString("userPhone"));
        u.setUserAddress(rs.getString("userAddress"));
        u.setUserLoginName(rs.getString("userLoginName"));
        u.setUserLoginStatus(rs.getInt("userLoginStatus"));
        u.setUserRole(rs.getInt("userRole"));
        return u;
    }
  
    
    
}
