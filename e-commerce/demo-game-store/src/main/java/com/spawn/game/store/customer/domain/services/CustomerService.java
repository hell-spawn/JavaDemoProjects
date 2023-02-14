package com.spawn.game.store.customer.domain.services;

import com.spawn.game.store.customer.application.input.CreateCustomerUseCase;
import com.spawn.game.store.customer.domain.models.Customer;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

@RequiredArgsConstructor
public class CustomerService implements CreateCustomerUseCase {
    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

}
