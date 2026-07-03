package net.java.Springbt_restapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.java.Springbt_restapi.enums.CredentialStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "eeid")
    private UserEntity userEntity;

    @Column(name="email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private CredentialStatus status;

    @Column(name = "must_change_password")
    private Boolean mustChangePassword;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

}
