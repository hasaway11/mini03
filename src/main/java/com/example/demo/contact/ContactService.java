package com.example.demo.contact;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ContactService {
  @Autowired
  private ContactDao contactDao;

  public int save(Contact contact) {
    contactDao.save(contact);
    return contact.getCno();
  }

  public List<Contact> findAll() {
    return contactDao.findAll();
  }

  public Optional<Contact> findById(Integer cno) {
    return contactDao.findByCno(cno);
  }

  public boolean update(Contact contact) {
    return contactDao.update(contact)==1;
  }

  public boolean delete(Integer cno) {
    return contactDao.delete(cno)==1;
  }
}
