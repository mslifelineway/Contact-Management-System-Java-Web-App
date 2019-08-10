/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oyedost.contactapp.dao;

import com.oyedost.contactapp.domain.User;
import com.oyedost.contactapp.rm.UserRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author janeman
 */
@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public void save(User u) {
        String sql = "INSERT INTO user ( userName, userPhone, userEmail, userPassword, userAddress, userLoginName, userRole, userLoginStatus) "
                + "VALUES(:name, :phone, :email, :password, :address, :loginName, :role, :loginStatus)";
        Map m = new HashMap();
        m.put("name", u.getUserName());
        m.put("phone", u.getUserPhone());
        m.put("email", u.getUserEmail());
        m.put("password", u.getUserPassword());
        m.put("address", u.getUserAddress());
        m.put("loginName", u.getUserLoginName());
        m.put("role", u.getUserRole());
        m.put("loginStatus", u.getUserLoginStatus());

        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
        Integer userId = kh.getKey().intValue();
        System.out.print("----------- user id is: " + userId + " ----------------------- ");
        u.setUserId(userId);
        
    }

    @Override
    public void update(User u) {
        String update = "UPDATE user SET "
                + "userName=:name, userEmail=:email, userPhone=:phone, userAddress=:address, userRole=:role, userLoginStatus=:status"
                + " WHERE userId=:userId";
        Map m = new HashMap();
        m.put("name", u.getUserName());
        m.put("email", u.getUserEmail());
        m.put("phone", u.getUserPhone());
        m.put("address", u.getUserAddress());
        m.put("role", u.getUserRole());
        m.put("status", u.getUserLoginStatus());
        m.put("userId", u.getUserId());
        getNamedParameterJdbcTemplate().update(update, m);
        System.out.println("---------- Data Updated Successfully! ------------");

    }

    @Override
    public void delete(User u) {
        this.delete(u.getUserId());
    }

    @Override
    public void delete(Integer userId) {
        String del = "DELETE FROM user WHERE userId=?";
        getJdbcTemplate().update(del, userId);
    }

    @Override
    public User findById(Integer userId) {
        String sql = "SELECT * FROM user WHERE userId=?";
        User u = getJdbcTemplate().queryForObject(sql, new UserRowMapper(), userId);
        return u;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        /* List<User>users = getJdbcTemplate().query(sql,new UserRowMapper());
        return users; */
        return getJdbcTemplate().query(sql, new UserRowMapper());

    }

    @Override
    public List<User> findByProperty(String propName, Object propValue) {
        String sql = "SELECT * FROM user WHERE "+propName+"=?" ;
        /* List<User>users = getJdbcTemplate().query(sql,new UserRowMapper(),propValue);
        return users; */
        return getJdbcTemplate().query(sql, new UserRowMapper(),propValue);
    }

}
