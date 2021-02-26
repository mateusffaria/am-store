package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.CustomerType;
import br.com.les.amstore.repository.CustomersType;
import br.com.les.amstore.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeServiceImpl implements ICustomerTypeService {

    @Autowired
    CustomersType customersType;

    @Override
    public List<CustomerType> findAll() {
        return customersType.findAll();
    }

    @Override
    public CustomerType findById(Long id) {
        return customersType.findById(id).get();
    }

    @Override
    public CustomerType saveAndFlush(CustomerType customerType) {
        return customersType.saveAndFlush(customerType);
    }
}
