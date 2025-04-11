package com.example.demo.contact;

import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
public class ContactService {
  @Autowired
  private ContactDao contactDao;

  private String uploadPhoto(MultipartFile photo, String name) {
    try {
      String ext = FilenameUtils.getExtension(photo.getOriginalFilename());
      String photoName = name + "." + ext;
      File file = new File("C:\\Users\\Tower\\IdeaProjects\\demo4\\upload\\images", photoName);
      System.out.println(System.getProperty("user.dir"));
      System.out.println(file.getAbsolutePath());
      photo.transferTo(file);
      return photoName;
    } catch (IOException e) {
      e.printStackTrace();
      return ContactConstants.DEFAULT_PHOTO;
    }
  }

  public Contact save(ContactCreateDto dto) {
    Contact contact = dto.toEntity(ContactConstants.DEFAULT_PHOTO);
    MultipartFile photo = dto.getPhoto();
    System.out.println(photo.getOriginalFilename());
    if(Objects.nonNull(dto.getPhoto()) && !dto.getPhoto().isEmpty()) {
      System.out.println("=====================================");
      contact.setPhoto(uploadPhoto(photo, dto.getName()));
    }
    contactDao.save(contact);
    return contact;
  }

  public List<Contact> findAll() {
    return contactDao.findAll();
  }

  public Contact findById(Integer cno) {
    return contactDao.findByCno(cno);
  }

  public Contact update(ContactUpdateDto dto) {
    Contact contact = contactDao.findByCno(dto.getCno());
    if(contact==null)
      return null;
    MultipartFile photo = dto.getPhoto();
    contact = dto.toEntity(contact);
    if(Objects.nonNull(dto.getPhoto()) && !dto.getPhoto().isEmpty()) {
      contact.setPhoto(uploadPhoto(photo, contact.getName()));
    }
    contactDao.update(contact);
    return contact;
  }

  public boolean delete(Integer cno) {
    return contactDao.delete(cno)==1;
  }
}
