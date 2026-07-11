package net.java.Springbt_restapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Gender;
import net.java.Springbt_restapi.enums.Role;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", referencedColumnName = "id")
    private UserEntity userEntity; // Business/Public ID

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "official_email_id", nullable = false)
    private String officialEmail;

    @Column(name="personal_email_id")
    private String personalEmail;

    @Column(name="date_of_birth")
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "emp_status")
    private EmployeeStatus empStatus;

    @Column(name = "profile_photo_url")
    private String profilePhotoURL;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "salary")
    private Long salary;

//    @Column(name = "manager_id")
//    private Long ManagerId;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity departmentEntity;

}
