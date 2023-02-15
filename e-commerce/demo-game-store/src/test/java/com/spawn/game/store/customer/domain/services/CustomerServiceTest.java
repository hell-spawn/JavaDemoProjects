package com.spawn.game.store.customer.domain.services;

import com.spawn.game.store.customer.domain.models.Customer;
import com.spawn.game.store.customer.domain.services.configuration.CustomerServiceConfiguration;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.CustomerRepository;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.entities.CustomerEntity;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles ="test")
@Import(CustomerServiceConfiguration.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void givenCustomerValidData_whenSaveCustomer_thenSuccess() {
        String customerId = UUID.randomUUID().toString();
        CustomerEntity savedCustomerEntity = new CustomerEntity();
        savedCustomerEntity.setId(customerId);
        savedCustomerEntity.setFirstName("Homer");
        savedCustomerEntity.setLastName("Simpson");
        savedCustomerEntity.setEmail("pieman@example.com");
        savedCustomerEntity.setAddress("123 Main Street");
        savedCustomerEntity.setCity("Springfield");
        savedCustomerEntity.setCountry("USA");
        savedCustomerEntity.setZipCode("12345");
        savedCustomerEntity.setPhoneNumber("123-456-7890");

        //given
        Customer customer = new Customer();
        customer.setId(null);
        customer.setFirstName("Homer");
        customer.setLastName("Simpson");
        customer.setEmail("pieman@example.com");
        customer.setAddress("123 Main Street");
        customer.setCity("Springfield");
        customer.setCountry("USA");
        customer.setZipCode("12345");
        customer.setPhoneNumber("123-456-7890");
        given(customerRepository.save(Mockito.any(CustomerEntity.class))).willReturn(savedCustomerEntity);

        //when
        Customer customerSaved = customerService.createCustomer(customer);

        //then
        assertAll( "Create customer",
                () -> assertThat(customerSaved.getId(), is(customerId)),
                () -> assertThat(customerSaved.getFirstName(), is(customer.getFirstName())),
                () -> assertThat(customerSaved.getLastName(), is(customer.getLastName())),
                () -> assertThat(customerSaved.getEmail(), is(customer.getEmail())),
                () -> assertThat(customerSaved.getAddress(), is(customer.getAddress())),
                () -> assertThat(customerSaved.getCity(), is(customer.getCity())),
                () -> assertThat(customerSaved.getCountry(), is(customer.getCountry())),
                () -> assertThat(customerSaved.getZipCode(), is(customer.getZipCode())),
                () -> assertThat(customerSaved.getPhoneNumber(), is(customer.getPhoneNumber()))
        );

    }

}
