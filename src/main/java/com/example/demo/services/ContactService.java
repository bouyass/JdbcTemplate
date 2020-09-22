package com.example.demo.services;

import com.example.demo.Queries;
import com.example.demo.dao.ContactRepository;
import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public int addContact(Contact contact){
      return contactRepository.addContact(contact);
    }

    public int removeContactById(int contact_id){
        return contactRepository.removeContactById(contact_id);
    }

    public int removeContactByEmail(String email){
        return contactRepository.removeContactByEmail(email);
    }

    public Contact findById(int contact_id){
        return contactRepository.findById(contact_id);
    }

    public Contact findByEmail(String email){
        return contactRepository.findByEmail(email);
    }

    public List<Contact> findAll(){
       return contactRepository.findAll();
    }

    public int updateContact(){
        return 0;
    }

}
