package br.com.les.amstore.service;

import br.com.les.amstore.domain.AddressType;

import java.util.List;

public interface IAddressTypeService {
    public List<AddressType> findAll();
    public AddressType findById(Long id);
    public AddressType saveAndFlush(AddressType addressType);
}
