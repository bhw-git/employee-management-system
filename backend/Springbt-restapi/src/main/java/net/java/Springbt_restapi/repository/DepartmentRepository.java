package net.java.Springbt_restapi.repository;

import net.java.Springbt_restapi.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
