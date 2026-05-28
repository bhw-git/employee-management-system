//package net.java.Springbt_restapi.auth;
//
//import jakarta.persistence.*;
//import net.java.Springbt_restapi.entity.EmployeeEntity;
//
//import javax.management.relation.Role;
//
//@Entity
//@Table(name="users")
//public class UserAuthEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(unique = true)
//    private String username;
//
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @OneToOne
//    @JoinColumn(name="eeid")
//    private EmployeeEntity employeeEntity;
//}
