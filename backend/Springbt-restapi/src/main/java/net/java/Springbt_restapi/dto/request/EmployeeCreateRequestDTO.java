package net.java.Springbt_restapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.java.Springbt_restapi.CustomClass.EmployeeStatus;
import net.java.Springbt_restapi.CustomClass.Gender;
import net.java.Springbt_restapi.entity.EmployeeEntity;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String officialEmail;
    @Email
    @NotBlank
    private String personalEmail;
    @NotNull
    private LocalDate dob;
    @NotNull
    private Gender gender;
    @NotNull
    private EmployeeStatus empStatus;
    private String profilePhotoURL;
    @NotNull
    private Long departmentId;
}
