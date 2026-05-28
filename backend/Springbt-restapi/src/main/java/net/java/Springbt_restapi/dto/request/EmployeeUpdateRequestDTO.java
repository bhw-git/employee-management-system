package net.java.Springbt_restapi.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.java.Springbt_restapi.CustomClass.EmployeeStatus;
import net.java.Springbt_restapi.CustomClass.Gender;
import net.java.Springbt_restapi.CustomClass.Role;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequestDTO {
    private String firstName;
    private String lastName;
    @Email
    private String officialEmail;
    @Email
    private String personalEmail;
    private LocalDate dob;
    private Gender gender;
    private EmployeeStatus empStatus;
    private MultipartFile profilePhoto;
    private Role role;
    private Long departmentId;
}
