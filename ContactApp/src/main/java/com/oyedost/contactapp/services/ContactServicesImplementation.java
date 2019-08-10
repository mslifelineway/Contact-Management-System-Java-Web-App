package com.oyedost.contactapp.services;

import com.oyedost.contactapp.dao.BaseDAO;
import com.oyedost.contactapp.dao.ContactDAO;
import com.oyedost.contactapp.domain.Contact;
import com.oyedost.contactapp.rm.ContactRowMapper;
import com.oyedost.contactapp.util.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ContactServicesImplementation extends BaseDAO implements ContactServices {

    @Autowired
    private ContactDAO contactDAO;

    @Override
    public void saveContact(Contact c) {
        contactDAO.save(c);
    }

    @Override
    public void updateContact(Contact c) {
        contactDAO.update(c);
    }

    @Override
    public void deleteContact(Integer contactId) {
        contactDAO.delete(contactId);
    }

    @Override
    public void deleteContact(Integer[] contactIds) {
        String ids = StringUtil.toCommaSeperatedString(contactIds);

        String sql = "DELETE FROM contact WHERE contactID IN (" + ids + ")";
        getJdbcTemplate().update(sql);
        System.out.println("------ all the selected contacts has been deleted!-----");
    }

    @Override
    public List<Contact> searchContact(Integer userId) {
        return contactDAO.findByProperty("userId", userId);
    }

    @Override
    public List<Contact> searchContact(Integer userId, String text) {
        String sql = "SELECT * FROM contact WHERE"
                + " userId=? AND (name LIKE '%" + text + "%' OR email LIKE '%" + text + "%' OR phone LIKE '%" + text + "%' OR address LIKE '%" + text + "%' OR remark LIKE '%" + text + "%')";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), userId);
    }

    @Override
    public Contact validateContact(Contact c) {
        String sql = "SELECT * FROM contact WHERE name=:name AND phone=:phone";
        Map m = new HashMap();
        m.put("name",c.getName());
        m.put("phone",c.getPhone());
        try {
            Contact contact = getNamedParameterJdbcTemplate().queryForObject(sql, m, new ContactRowMapper());
            return contact;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return null;
        }
    }

    @Override
    public Contact findContactByContactId(Integer contactId) {

        Contact c = contactDAO.findById(contactId);
        return c;
    }
}
