package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Person;
import br.com.les.amstore.repository.People;
import br.com.les.amstore.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    People people;

    @Override
    public List<Person> findAll() {
        return people.findAll();
    }

    @Override
    public Person findById(Long id) {
        return people.findById(id).get();
    }

    @Override
    public Person saveAndFlush(Person person) {
        return people.saveAndFlush(person);
    }
}
