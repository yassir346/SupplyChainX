package supplychainx.springboot.delivery.service.Impl;

import supplychainx.springboot.delivery.dto.CustomerRequestDto;
import supplychainx.springboot.delivery.dto.CustomerResponseDto;
import supplychainx.springboot.delivery.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {
    @Override
    public CustomerResponseDto save(CustomerRequestDto customerRequest) {
        return null;
    }

    @Override
    public CustomerResponseDto update(CustomerRequestDto customerRequest, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CustomerResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<CustomerResponseDto> findAllCustomers() {
        return List.of();
    }
}
