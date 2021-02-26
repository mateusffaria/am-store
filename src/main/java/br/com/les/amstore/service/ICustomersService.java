package br.com.les.amstore.service;

import br.com.les.amstore.domain.Customer;

import java.util.List;

public interface ICustomersService {
    public List<Customer> findAll();
    public Customer findById(Long id);
    public Customer saveAndFlush(Customer customer);

}
