package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Game;
import br.com.les.amstore.repository.Games;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements IGenericService<Game> {
    @Autowired
    Games games;

    @Override
    public List<Game> findAll() {
        return games.findAll();
    }

    @Override
    public Game findById(Long id) {
        return games.findById(id).get();
    }

    @Override
    public Game saveAndFlush(Game object) {
        return games.saveAndFlush(object);
    }
}
