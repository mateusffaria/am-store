package br.com.les.amstore.service;

import br.com.les.amstore.domain.City;

import java.util.List;

public interface ICityService {
    public List<City> findAll();
    public City findById(Long id);
    public City saveAndFlush(City city);
}