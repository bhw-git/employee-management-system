package net.java.Springbt_restapi.repository;

import net.java.Springbt_restapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserEntity_Eeid(String employeeId);
}
