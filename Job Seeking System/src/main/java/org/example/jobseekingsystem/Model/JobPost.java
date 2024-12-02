package org.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "salary>=0")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Please enter a title")
    @Size(min = 5, message = "Title must be 5 or more characters long")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @NotEmpty(message = "Please enter a description")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @NotEmpty(message = "Please enter a location")
    @Column(columnDefinition = "varchar(255) not null")
    private String location;

    @NotNull(message = "Please enter a salary")
    @PositiveOrZero(message = "Salary can't be a negative number")
    @Column(columnDefinition = "double not null")
    private Double salary;

    @CreationTimestamp
    @Column(columnDefinition = "timestamp not null")
    private LocalDate postingDate;
}
