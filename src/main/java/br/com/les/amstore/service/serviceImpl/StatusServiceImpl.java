package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Status;
import br.com.les.amstore.repository.Statuses;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements IGenericService<Status> {
    @Autowired
    Statuses statuses;

    @Override
    public List<Status> findAll() {
        return statuses.findAll();
    }

    @Override
    public Status findById(Long id) {
        return statuses.findById(id).get();
    }

    @Override
    public Status saveAndFlush(Status object) {
        return statuses.saveAndFlush(object);
    }
}
