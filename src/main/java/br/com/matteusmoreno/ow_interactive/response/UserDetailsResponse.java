package br.com.matteusmoreno.ow_interactive.response;

import br.com.matteusmoreno.ow_interactive.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDetailsResponse(
        Long id,
        String name,
        String email,
        LocalDate birthday,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public UserDetailsResponse(User user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getBirthday(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
