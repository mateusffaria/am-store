package br.com.les.amstore.service;

import br.com.les.amstore.domain.Devolution;

public interface IDevolutionService extends IGenericService<Devolution> {
    public Devolution sendDevolutionRequest(Devolution devolution);
}
