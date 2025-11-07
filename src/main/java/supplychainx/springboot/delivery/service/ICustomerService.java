package supplychainx.springboot.delivery.service;


import supplychainx.springboot.delivery.dto.CustomerRequestDto;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;

import java.util.List;

public interface ICustomerService {
    CustomerResponseDto save(CustomerRequestDto customerRequest);
    CustomerResponseDto update(CustomerRequestDto customerRequest, Long id);
    void delete(Long id);
    CustomerResponseDto findById(Long id);
    List<CustomerResponseDto> findAllCustomers();
}
