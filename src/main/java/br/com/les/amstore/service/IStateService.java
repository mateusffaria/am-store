package br.com.les.amstore.service;

import br.com.les.amstore.domain.Person;
import br.com.les.amstore.domain.State;

import java.util.List;

public interface IStateService {
    public List<State> findAll();
    public State findById(Long id);
    public State saveAndFlush(State state);
}
