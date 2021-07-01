package br.com.les.amstore.service;

import br.com.les.amstore.domain.Address;

import java.util.List;

public interface IAddressService {
    public List<Address> findAll();
    public Address findById(Long id);
    public Address saveAndFlush(Address address);
}
