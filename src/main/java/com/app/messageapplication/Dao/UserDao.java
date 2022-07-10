package com.app.messageapplication.Dao;

import com.app.messageapplication.Entity.UserConversations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserConversations,Integer> {

    public UserConversations findByUsername(String username);


}
