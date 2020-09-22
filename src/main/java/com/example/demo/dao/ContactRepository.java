package com.example.demo.dao;


import com.example.demo.Queries;
import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addContact(Contact contact){
        try{
            KeyHolder keyHolder  = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(Queries.SQL_ADD_CONTACT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,contact.getName());
                ps.setString(2,contact.getEmail());
                ps.setString(3,contact.getAddress());
                ps.setString(4,contact.getPhone());
                return ps;
            }, keyHolder);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int removeContactById(int contact_id){
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(Queries.SQL_DELETE_BY_ID, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, contact_id);
                return ps;
            });
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int removeContactByEmail(String email){
        try{
            System.out.println(email);
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(Queries.SQL_DELETE_BY_EMAIL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                return ps;
            });
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Contact findById(int contact_id){
        Contact contact = jdbcTemplate.queryForObject(Queries.SQL_FIND_BY_ID, new Object[]{contact_id}, BeanPropertyRowMapper.newInstance(Contact.class));
        System.out.println(contact.getPhone());
        return contact;
    }

    public Contact findByEmail(String email){
        Contact contact = jdbcTemplate.queryForObject(Queries.SQL_FIND_BY_EMAIL, new Object[]{email}, BeanPropertyRowMapper.newInstance(Contact.class));
        return contact;
    }

    public List<Contact> findAll(){
         List<Contact> listContacts = jdbcTemplate.query(Queries.SQL_FIND_ALL, BeanPropertyRowMapper.newInstance(Contact.class));
         return listContacts;
    }
   /* private RowMapper<Contact> userRowMapper = ((rs, rowNum) -> {
        return new Contact(rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });*/


}
