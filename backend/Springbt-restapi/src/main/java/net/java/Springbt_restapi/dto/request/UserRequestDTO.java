package net.java.Springbt_restapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserRequestDTO {

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
    @NotNull
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Long departmentId;
}
