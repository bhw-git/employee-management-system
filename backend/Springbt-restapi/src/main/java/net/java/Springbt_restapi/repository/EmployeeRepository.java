package net.java.Springbt_restapi.repository;

import net.java.Springbt_restapi.entity.EmployeeEntity;
import net.java.Springbt_restapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface  EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    // ✅ Loads employee + department in ONE query
//    @Query("SELECT e FROM EmployeeEntity e JOIN FETCH e.departmentEntity")
    Optional<EmployeeEntity> findByUserEntity(UserEntity userEntity);
}