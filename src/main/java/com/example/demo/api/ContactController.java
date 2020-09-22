package com.example.demo.api;

import com.example.demo.model.Contact;
import com.example.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/removeContactById/{contact_id}")
    public ResponseEntity<Map<String, String>> removeContactById(@PathVariable int contact_id){
        int result = contactService.removeContactById(contact_id);
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

    @PostMapping("/removeContactByEmail")
    public ResponseEntity<Map<String, String>> removeContactByEmail(@RequestBody String email){
        int result = contactService.removeContactByEmail(email);
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

    @GetMapping("/findAll")
    public ResponseEntity<Map<String, String>> findByAll(){
        List<Contact> contacts =  contactService.findAll();
        if(contacts.size() > 0){
            Map<String, String> map = new HashMap<String, String>();
            int i = 1;
            for (Contact contact : contacts){
                map.put("contact"+i,contact.toString());
                i++;
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","Il n'y aucun contact");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/findById/{contact_id}")
    public ResponseEntity<Map<String, String>> findById(@PathVariable int contact_id){
        Contact contact =  contactService.findById(contact_id);
        if(contact != null){
            Map<String, String> map = new HashMap<String, String>();
            map.put("message",contact.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","operation failed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @PostMapping("/findByEmail")
    public ResponseEntity<Map<String, String>> findByEmail(@RequestBody Map<String, String> email){
        Contact contact =  contactService.findByEmail(email.get("email"));
        if(contact != null){
            Map<String, String> map = new HashMap<String, String>();
            map.put("message",contact.toString());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","operation failed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @PostMapping("/updateContact")
    public ResponseEntity<Map<String, String>> updateContact(@RequestBody Map<String, String> contactMap){
        String name = (String) contactMap.get("name");
        String email = (String) contactMap.get("email");
        String address = (String) contactMap.get("address");
        String telephone = (String) contactMap.get("telephone");
        Contact contact = new Contact(name, email, address, telephone);
        int result = contactService.updateContact(contact);
        if(result == 1){
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","contact has been updated");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            Map<String, String> map = new HashMap<String, String>();
            map.put("message","operation failed");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}
