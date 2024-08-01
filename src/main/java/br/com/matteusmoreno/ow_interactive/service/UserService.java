package br.com.matteusmoreno.ow_interactive.service;

import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.exception.UserNotFoundException;
import br.com.matteusmoreno.ow_interactive.mapper.UserMapper;
import br.com.matteusmoreno.ow_interactive.repository.UserRepository;
import br.com.matteusmoreno.ow_interactive.request.CreateUserRequest;
import br.com.matteusmoreno.ow_interactive.response.UserDetailsResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        User user = userMapper.createUserBuilder(request);
        return userRepository.save(user);
    }

    public Page<UserDetailsResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDetailsResponse::new);
    }

    public User userDetails(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
