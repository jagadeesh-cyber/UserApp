package com.upgrad.userApp.dao;

import com.upgrad.userApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {

}
