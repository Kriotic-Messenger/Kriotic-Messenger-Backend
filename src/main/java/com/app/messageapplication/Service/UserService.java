package com.app.messageapplication.Service;

import com.app.messageapplication.Dao.UserDao;
import com.app.messageapplication.Entity.User;
import com.app.messageapplication.Entity.UserConversations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    HashSet<String> cache = new HashSet<>();

    public UserConversations findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public List<User> getConversations(String username){
        UserConversations userConversations = findByUsername(username);
        return userConversations==null ? new ArrayList<>() : userConversations.getUsers();
    }

    public void insertConversation(String from, String to) {
        String id = ConversationIdService.getId(from, to);
        if (cache.contains(id)) {
            return;
        }

        if (findByUsername(from) == null) {
            userDao.save(new UserConversations(from, to));
        } else {
            UserConversations userConversations = findByUsername(from);
            boolean found = false;
            for (User user : userConversations.getUsers()) {
                if (user.getUsername().equals(to)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<User> userList = userConversations.getUsers();
                userList.add(new User(to));
                userConversations.setUsers(userList);
                userDao.save(userConversations);
            }
        }

        if (findByUsername(to) == null) {
            userDao.save(new UserConversations(to, from));
        }
        else {
            UserConversations userConversations = findByUsername(to);
            boolean found = false;
            for (User user : userConversations.getUsers()) {
                if (user.getUsername().equals(from)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<User> userList = userConversations.getUsers();
                userList.add(new User(from));
                userConversations.setUsers(userList);
                userDao.save(userConversations);
            }
        }

        cache.add(id);
    }

}
