package com.example.demo.services;

import com.example.demo.dao.ContactRepository;
import com.example.demo.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public int addContact(Contact contact){
      return contactRepository.addContact(contact);
    }

    public int removeContact(int contact_id){
        return contactRepository.removeContact(contact_id);
    }

    public int updateContact(){
        return 0;
    }

}
