package com.eazybytes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Customer {

    /**
     * this GenericGenerator to be native means that jpa is relying on database to generate the ID values
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nativeStrategy")
    @GenericGenerator(name = "nativeStrategy", strategy = "native")
    private int id;
    private String email;
    private String pwd;
    private String role;
}
