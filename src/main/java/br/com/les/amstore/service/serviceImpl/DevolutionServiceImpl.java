package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Devolution;
import br.com.les.amstore.repository.Devolutions;
import br.com.les.amstore.repository.Orders;
import br.com.les.amstore.repository.Statuses;
import br.com.les.amstore.service.IDevolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevolutionServiceImpl implements IDevolutionService {
    @Autowired
    Devolutions devolutions;

    @Autowired
    Statuses statuses;

    @Override
    public List<Devolution> findAll() {
        return devolutions.findAll();
    }

    @Override
    public Devolution findById(Long id) {
        return devolutions.findById(id).get();
    }

    @Override
    public Devolution saveAndFlush(Devolution object) {
        return devolutions.saveAndFlush(object);
    }

    @Override
    public Devolution sendDevolutionRequest(Devolution devolution) {
        devolution.getOrder().setStatus(statuses.findByStatus("EM PROCESSO DE TROCA"));

        this.saveAndFlush(devolution);
        return devolution;
    }
}
