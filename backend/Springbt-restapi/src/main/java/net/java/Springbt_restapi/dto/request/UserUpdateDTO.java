package net.java.Springbt_restapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserUpdateDTO {

    @NotBlank
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String officialEmail;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private LocalDate credentialExpiryDate;
    private LocalDate accountExpiryDate;
    private String signUpMethod;
    private EmployeeStatus empStatus;
    private Role role;
    private Long salary;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long departmentId;
}
