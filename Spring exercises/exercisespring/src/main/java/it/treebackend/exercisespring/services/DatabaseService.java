package it.treebackend.exercisespring.services;

import it.treebackend.exercisespring.models.EncryptedUser;
import it.treebackend.exercisespring.view.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.UUID;

@Service
public class DatabaseService {
    private static HashMap<String, EncryptedUser> users = new HashMap<>(); //key:username, value:user
    private static HashMap<String, String> loggedUsers = new HashMap<>(); //key:cookie, value:username

    public boolean registration(User user){
        EncryptedUser newUser = new EncryptedUser(user.getUsername(), user.getPassword());
        if(users.containsKey(user.getUsername())) return false;
        users.put(newUser.getUsername(), newUser);
        return true;
    }

    public boolean searchUserByLoginDetails(String username, String password){
        if(loggedUsers.containsValue(username))
            return false;
        if(users.containsKey(username)){
            if(password.hashCode() == users.get(username).getEncryptedPassword()){
                return true;
            }
            return false;
        }
        return false;
    }

    public String login(String username, String password) {
        if (searchUserByLoginDetails(username, password)) {
            String cookie = UUID.randomUUID().toString();
            loggedUsers.put(cookie, username);
            return cookie;
        }
        return null;
    }

    public String searchUserByCookie(String cookie){
        return loggedUsers.get(cookie);
    }


}
