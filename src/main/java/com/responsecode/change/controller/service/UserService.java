package com.responsecode.change.controller.service;

import com.responsecode.change.model.User;
import com.responsecode.change.model.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This is a service class for User
 * This calls "https://gorest.co.in/public/v2/users" to get list of users
 */
@Service
public class UserService {

    @Autowired
    private GatewayService gatewayService;

    public Mono<ResponseEntity<User>> getUser(int id) {
        Mono<User> responseMono =  gatewayService.getUser(id);
        return responseMono.flatMap(user -> {
            return Mono.just(mapToUser(user));
        });
    }

    public ResponseEntity<User> mapToUser(User user) {
        ResponseEntity<User> responseEntity;
        if (user!=null && user.getId()!=0) {
            responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    public Mono<List<User>> getUsers() {
        return gatewayService.getUsers();
    }

    public Mono<ResponseEntity<String>> addUser(User user) {
        Mono<String> responseMono =  gatewayService.addUser(user);

        return responseMono.flatMap(s -> {
            System.out.println("ADD USER RESPONSE -> "+s);
            Mono<ResponseEntity<String>> responseEntityMono = null;
            if (s.equals("SUCCESS")) {
                responseEntityMono = Mono.just(new ResponseEntity<>(s, HttpStatus.OK));
            } else if (s.equals("FAILURE")) {
                responseEntityMono = Mono.just(new ResponseEntity<>(s, HttpStatus.BAD_REQUEST));
            }
            return responseEntityMono;
        });
    }
}
