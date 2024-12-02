package org.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please enter a user ID")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Please enter a job post ID")
    @Column(columnDefinition = "int not null")
    private Integer jobPostId;
}
