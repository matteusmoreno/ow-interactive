package br.com.matteusmoreno.ow_interactive.mapper;

import br.com.matteusmoreno.ow_interactive.entity.User;
import br.com.matteusmoreno.ow_interactive.request.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User createUserBuilder(CreateUserRequest request)
    {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .birthday(request.birthday())
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
