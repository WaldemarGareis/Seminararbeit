package org.example.exampleproject.repository;

import java.util.List;

import org.example.exampleproject.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
