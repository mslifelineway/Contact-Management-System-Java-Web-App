package com.oyedost.contactapp.dao;

import com.oyedost.contactapp.domain.Contact;
import com.oyedost.contactapp.rm.ContactRowMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

    @Override
    public void save(Contact c) {
        String sql = "INSERT INTO contact (userId,name,phone,email,address,remark) "
                + "VALUES (:user_id, :name, :phone, :email, :address, :remark)";
        Map m = new HashMap();
        m.put("user_id", c.getUserId());
        m.put("name",c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());
        
        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
        Integer contactId = kh.getKey().intValue();
        System.out.print("----------- contact id is: " + contactId + " ----------------------- ");
        c.setContactId(contactId);
    
    }
    @Override
    public void update(Contact c) {
           
        String sql = "UPDATE contact SET name=:name, email=:email, phone=:phone, address=:address, remark=:remark WHERE contactId=:cid";
        Map m = new HashMap();
        m.put("name",c.getName());
        m.put("email", c.getEmail());
        m.put("phone", c.getPhone());
        m.put("address",c.getAddress());
        m.put("remark", c.getRemark());
        m.put("cid",c.getContactId());
        getNamedParameterJdbcTemplate().update(sql, m);
        System.out.print("---------- contact data updated successfully! ----");

    }

    @Override
    public void delete(Contact c) {
        this.delete(c.getContactId());
    }

    @Override
    public void delete(Integer contactId) {
       String sql = "DELETE FROM contact WHERE contactId=?";
         getJdbcTemplate().update(sql,contactId);
        
    }

    @Override
    public Contact findById(Integer contactId) {
        String sql = "SELECT * FROM contact WHERE contactId=?";
       Contact contact = getJdbcTemplate().queryForObject(sql, new ContactRowMapper(),contactId);
         return contact;
    }
    
    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";
        List<Contact> contacts = getJdbcTemplate().query(sql, new ContactRowMapper());
         return contacts;
      }

    @Override
    public List<Contact> findByProperty(String propName, Object propValue) {
        String sql = "SELECT * FROM contact WHERE "+propName+"=?";
        List<Contact> contacts = getJdbcTemplate().query(sql, new ContactRowMapper(),propValue);
         return contacts;
        
    }
    
}
