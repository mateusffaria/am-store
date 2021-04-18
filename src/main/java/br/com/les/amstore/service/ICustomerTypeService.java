package br.com.les.amstore.service;

import br.com.les.amstore.domain.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    public List<CustomerType> findAll();
    public CustomerType findById(Long id);
    public CustomerType saveAndFlush(CustomerType customerType);
}
