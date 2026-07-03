package net.java.Springbt_restapi.repository;

import net.java.Springbt_restapi.entity.EmployeeEntity;
import net.java.Springbt_restapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT e FROM UserEntity e JOIN FETCH e.departmentEntity WHERE e.eeid = :eeid")
    Optional<UserEntity> findByEeid(@Param("eeid") String eeid);
}
