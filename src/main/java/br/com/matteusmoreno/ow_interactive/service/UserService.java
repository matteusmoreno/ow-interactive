package br.com.matteusmoreno.ow_interactive.service;

import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.repository.UserRepository;
import br.com.matteusmoreno.ow_interactive.request.CreateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
