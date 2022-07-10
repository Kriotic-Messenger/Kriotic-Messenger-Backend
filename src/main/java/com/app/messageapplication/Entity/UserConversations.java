package com.app.messageapplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConversations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String username;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<User> users;

    public UserConversations(String username) {
        this.username = username;
        this.users = new ArrayList<>();
    }

    public UserConversations(String username, String user){
        this.username = username;
        this.users = new ArrayList<>();
        this.users.add(new User(user));
    }

}
