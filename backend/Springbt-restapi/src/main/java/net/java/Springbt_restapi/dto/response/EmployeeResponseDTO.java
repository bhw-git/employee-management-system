package net.java.Springbt_restapi.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.java.Springbt_restapi.enums.EmployeeStatus;
import net.java.Springbt_restapi.enums.Gender;
import net.java.Springbt_restapi.enums.Role;

import java.time.LocalDate;
@Data
@RequiredArgsConstructor
public class EmployeeResponseDTO {
    private String eeid;
    private String firstName;
    private String lastName;
    private String officialEmail;
    private String personalEmail;
    private LocalDate dob;
    private Gender gender;
    private EmployeeStatus empStatus;
    private String profilePhotoURL;
    private Role role;
    private String department;
    private Long departmentId;
}
