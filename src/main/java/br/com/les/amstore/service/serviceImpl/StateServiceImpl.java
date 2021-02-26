package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.State;
import br.com.les.amstore.repository.States;
import br.com.les.amstore.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements IStateService {

    @Autowired
    States states;

    @Override
    public List<State> findAll() {
        return states.findAll();
    }

    @Override
    public State findById(Long id) {
        return states.findById(id).get();
    }

    @Override
    public State saveAndFlush(State state) {
        return states.saveAndFlush(state);
    }
}
