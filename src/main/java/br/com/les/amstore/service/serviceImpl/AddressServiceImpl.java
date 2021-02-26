package br.com.les.amstore.service.serviceImpl;


import br.com.les.amstore.domain.Address;
import br.com.les.amstore.repository.Addresses;
import br.com.les.amstore.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    Addresses addresses;

    @Override
    public List<Address> findAll() {
        return addresses.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addresses.findById(id).get();
    }

    @Override
    public Address saveAndFlush(Address address) {
        return addresses.saveAndFlush(address);
    }
}
