package com.eazybytes.controller;

import com.eazybytes.model.Customer;
import com.eazybytes.repo.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/myAccount")
    public String getAccountDetails() {

        return "welcome to security";
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {

        Customer savedCustomer = null;
        ResponseEntity<String> response = null;

        try {
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0) {

                response = ResponseEntity.status(HttpStatus.CREATED).body("Given details are saved successfully");
            }
        }
        catch (Exception e) {

            logger.error(e.getMessage());
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an exception occured");
        }
        return response;
    }
}
