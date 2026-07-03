package net.java.Springbt_restapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.java.Springbt_restapi.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_roles")
@Entity
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
