package br.com.les.amstore.service;

import br.com.les.amstore.domain.Game;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IGameService extends IGenericService<Game> {
    List<Game> findAllByActiveTrue();
}
