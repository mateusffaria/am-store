package br.com.les.amstore.service;

import br.com.les.amstore.domain.Devolution;

public interface IDevolutionService extends IGenericService<Devolution> {
    Devolution sendDevolutionRequest(Devolution devolution);
    Devolution updateDevolutionRequest(Devolution devolution, Double valueWallet);
}
