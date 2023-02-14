package com.spawn.game.store.customer.domain.services.configuration;

import com.spawn.game.store.customer.domain.models.Customer;
import com.spawn.game.store.customer.domain.services.CustomerService;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.CustomerRepository;
import com.spawn.game.store.product.domain.services.ProductService;
import com.spawn.game.store.product.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CustomerServiceConfiguration {

    @MockBean
    CustomerRepository customerRepository;



    @Bean
    public CustomerService customerService() {
        return new CustomerService();

    }

}
