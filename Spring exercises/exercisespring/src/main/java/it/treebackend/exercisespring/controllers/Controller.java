package it.treebackend.exercisespring.controllers;

import it.treebackend.exercisespring.services.DatabaseService;
import it.treebackend.exercisespring.view.User;
import org.apache.tomcat.util.json.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.UUID;

@RestController
public class Controller {

    @PostMapping("/registrazione")
    ResponseEntity<String> registration(@RequestBody User body, @Autowired DatabaseService dbService){
        HttpHeaders header = new HttpHeaders();
        if(dbService.registration(body)) return new ResponseEntity<>("registrazione ok", header, HttpStatus.ACCEPTED);
        return new ResponseEntity<>("registrazione fallita:\nutente già registrato", header, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/login")
    ResponseEntity<String> login (@RequestBody User body,@Autowired DatabaseService dbService){
        HttpHeaders header = new HttpHeaders();
        if(dbService.searchUserByLoginDetails(body.getUsername(), body.getPassword())) {
            header.add("cookie", dbService.login(body.getUsername(), body.getPassword()));
            return new ResponseEntity<>(
                    "logged", header, HttpStatus.OK);
        }
        return new ResponseEntity<>("login fallito:\ndati errati o utente già loggato", header, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/usernameLogged")
    ResponseEntity<String> searchUserByCookie(@RequestBody String cookie, @Autowired DatabaseService dbService){
        HttpHeaders header = new HttpHeaders();
        String userSearched = dbService.searchUserByCookie(cookie);
        if(userSearched != null) return new ResponseEntity<>("Username of cookie: " + userSearched, header, HttpStatus.OK);
        return new ResponseEntity<>("Cookie non associato ad utenti", header, HttpStatus.BAD_REQUEST);
    }

}
