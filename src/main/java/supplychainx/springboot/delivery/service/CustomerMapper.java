package supplychainx.springboot.delivery.service;

import org.springframework.stereotype.Service;
import supplychainx.springboot.delivery.dto.CustomerRequestDto;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;
import supplychainx.springboot.delivery.model.Customer;

@Service
public class CustomerMapper {
    public CustomerResponseDto toResponse(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setCity(customer.getCity());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    public Customer toEntity(CustomerRequestDto customerRequest){
        Customer customer = new Customer();
        customer.setName(customer.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());
        return customer;
    }
}
