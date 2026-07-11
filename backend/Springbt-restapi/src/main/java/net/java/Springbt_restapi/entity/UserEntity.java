package net.java.Springbt_restapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "eeid", unique = true, nullable = false, updatable = false)
    private String eeid; // Business/Public ID
    private String firstName;
    private String lastName;
    private String officialEmail;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private LocalDate credentialExpiryDate;
    private LocalDate accountExpiryDate;
    private String twoFactorSecret;
    private boolean isTwoFactorEnabled;
    private String signUpMethod;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus empStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void prePersist(){
        if(role == null) role = Role.ROLE_EMPLOYEE;
    }

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity departmentEntity;
}
