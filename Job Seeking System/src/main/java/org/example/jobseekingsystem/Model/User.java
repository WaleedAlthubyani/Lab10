package org.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "age>21 and (role='JOB_SEEKER' or role='EMPLOYER') and length(name)>4")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a name")
    @Size(min = 5,message = "Name must be 5 or more characters long")
    @Pattern(regexp = "^[a-zA-Z]{5,}$",message = "Name may contain letters only")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email format")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;

    @NotEmpty(message = "Please enter a password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",message = "Password must contain at least one digit [0-9].Password must contain at least one lowercase Latin character [a-z].Password must contain at least one uppercase Latin character [A-Z].Password must contain at least one special character like ! @ # & ( ).Password must contain a length of at least 8 characters and a maximum of 20 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotNull(message = "Please enter an age")
    @Positive
    @Min(value = 22,message = "Age must be older that 21")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Please enter a role")
    @Pattern(regexp = "^(?i)(JOB_SEEKER|EMPLOYER)$",message = "Role can only be JOB_SEEKER or EMPLOYER")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;
}
