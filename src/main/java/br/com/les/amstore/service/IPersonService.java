package br.com.les.amstore.service;

import br.com.les.amstore.domain.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    public List<Person> findAll();
    public Person findById(Long id);
    public Person saveAndFlush(Person person);
    Optional<Person> findByEmail(String email);
}
