package br.com.matteusmoreno.ow_interactive.repository;

import br.com.matteusmoreno.ow_interactive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
