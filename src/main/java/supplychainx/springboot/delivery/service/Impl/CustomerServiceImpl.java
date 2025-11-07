package supplychainx.springboot.delivery.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import supplychainx.springboot.delivery.dto.CustomerRequestDto;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;
import supplychainx.springboot.delivery.model.Customer;
import supplychainx.springboot.delivery.repository.CustomerRepository;
import supplychainx.springboot.delivery.service.CustomerMapper;
import supplychainx.springboot.delivery.service.ICustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponse(savedCustomer);
    }

    @Override
    public CustomerResponseDto update(CustomerRequestDto customerRequest, Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        customer.setName(customerRequest.getName());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());

        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return customerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponseDto> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse).collect(Collectors.toList());
    }
}
