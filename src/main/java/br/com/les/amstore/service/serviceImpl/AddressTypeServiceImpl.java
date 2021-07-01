package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.AddressType;
import br.com.les.amstore.repository.AddressesType;
import br.com.les.amstore.service.IAddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressTypeServiceImpl implements IAddressTypeService {

    @Autowired
    AddressesType addressesType;

    @Override
    public List<AddressType> findAll() {
        return addressesType.findAll();
    }

    @Override
    public AddressType findById(Long id) {
        return addressesType.findById(id).get();
    }

    @Override
    public AddressType saveAndFlush(AddressType addressType) {
        return addressesType.saveAndFlush(addressType);
    }
}
