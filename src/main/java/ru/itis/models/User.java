package ru.itis.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String car;
    private String country;
    private Double salary;


}