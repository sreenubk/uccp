package  com.lotus.uccp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lotus.uccp.authentication.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
