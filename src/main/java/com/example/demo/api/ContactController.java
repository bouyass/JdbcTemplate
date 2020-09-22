package com.example.demo.api;

import com.example.demo.model.Contact;
import com.example.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("addContact")
    public ResponseEntity<Map<String,String>> addContact(@RequestBody Map<String, String> contactMap){
        String name = (String) contactMap.get("name");
        String email = (String) contactMap.get("email");
        String address = (String) contactMap.get("address");
        String telephone = (String) contactMap.get("telephone");
        Contact contact = new Contact(name, email, address, telephone);
        int result = contactService.addContact(contact);
        if(result == 1){
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","contact has been registered");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","operation failed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/removeContact/{contact_id}")
    public ResponseEntity<Map<String, String>> removeContact(@PathVariable int contact_id){
        int result = contactService.removeContact(contact_id);
        if(result == 1){
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","contact has been removed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","operation failed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}
