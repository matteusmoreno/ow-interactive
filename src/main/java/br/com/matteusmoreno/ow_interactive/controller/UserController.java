package br.com.matteusmoreno.ow_interactive.controller;

import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.request.CreateUserRequest;
import br.com.matteusmoreno.ow_interactive.response.UserDetailsResponse;
import br.com.matteusmoreno.ow_interactive.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDetailsResponse> create(@RequestBody @Valid CreateUserRequest request, UriComponentsBuilder uriBuilder) {
        User user = userService.createUser(request);
        URI uri = uriBuilder.path("/users/create/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailsResponse(user));

    }
}
