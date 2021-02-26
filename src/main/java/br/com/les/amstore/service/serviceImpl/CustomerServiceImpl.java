package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.repository.Customers;
import br.com.les.amstore.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomersService {
    @Autowired
    Customers customers;

    @Override
    public List<Customer> findAll() {
        return customers.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customers.findById(id).get();
    }

    @Override
    public Customer saveAndFlush(Customer customer) {
        return customers.saveAndFlush(customer);
    }
}
