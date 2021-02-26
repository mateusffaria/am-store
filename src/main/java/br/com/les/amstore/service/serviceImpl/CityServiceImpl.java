package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.City;
import br.com.les.amstore.repository.Cities;
import br.com.les.amstore.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    Cities cities;

    @Override
    public List<City> findAll() {
        return cities.findAll();
    }

    @Override
    public City findById(Long id) {
        return cities.findById(id).get();
    }

    @Override
    public City saveAndFlush(City city) {
        return cities.saveAndFlush(city);
    }
}
