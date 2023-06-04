package com.neon.springbootprodready.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department
{
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "department name is mandatory")
    private String name;
    private String address;
    private String code;
}

/*
Other Validators for Reference:
    @Length(min = 0, max = 4847)
    @Size(min = 2398, max = 29837)
    @Min(10)
    @Max(99)
    @Positive
    @PositiveOrZero
    @Negative
    @NegativeOrZero
    @Future
    @FutureOrPresent
    @Past
    @PastOrPresent
    @Email
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern
 */
