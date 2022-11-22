package com.responsecode.change.controller.service;

import com.responsecode.change.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GatewayService {

    public Mono<User> getUser(int id) {
        System.out.println("in UserService class");
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri("https://gorest.co.in/public/v2/users/"+id)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        System.out.println("SUCCESS");
                        return clientResponse.bodyToMono(User.class);
                    } else {
                        System.out.println("FAIL");
                        return Mono.just(new User(0, "NA", "NA", "NA", "NA"));
                    }
                });
    }

    public Mono<List<User>> getUsers() {
        System.out.println("in UserService class");
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri("https://gorest.co.in/public/v2/users")
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<List<User>>() {}));
    }

    public Mono<String> addUser(User user) {
        WebClient webClient = WebClient.create();
        return webClient.post()
                .uri("https://gorest.co.in/public/v2/users")
                .headers(httpHeaders -> httpHeaders.setBearerAuth("5ee5ac658bcec48b66e7e4b82a9886ecf2f3a9c4cf26e504ee632746ce1f7c40"))
                .body(Mono.just(user), User.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        System.out.println("SUCCCESSSSSSSSSS");
                        return Mono.just("SUCCESS");
                    } else {
                        System.out.println("FAILUREEEEEEEEEEE");
                        return Mono.just("FAILURE");
                    }
                });
    }
}
