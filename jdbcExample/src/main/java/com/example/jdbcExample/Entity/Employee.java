package com.example.jdbcExample.Entity;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Data
public class Employee {
    @Id
    private long id;
    private String name;
    private String department;
    private String address;
    private long salary;
}
