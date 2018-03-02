package org.example.exampleproject.domain.setup;

import javax.annotation.PostConstruct;

import org.example.exampleproject.domain.Customer;
import org.example.exampleproject.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSetup {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSetup.class);

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    private void initCustomers() {

	Customer firstCustomer = new Customer("Foo", "Bar");
	Customer secondCustomer = new Customer("Hello", "World");

	customerRepository.save(firstCustomer);
	customerRepository.save(secondCustomer);

	logCustomers();
    }

    private void logCustomers() {
	for (Customer customer : customerRepository.findAll()) {
	    LOGGER.info(">> Customer: id = {}, Firstname = {}, Lastname = {}", customer.getId(), customer.getFirstName(),
		    customer.getLastName());
	}
    }

}
