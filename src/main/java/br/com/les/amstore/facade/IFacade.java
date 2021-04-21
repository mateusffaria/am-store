package br.com.les.amstore.facade;

import br.com.les.amstore.domain.DomainEntity;

import java.util.List;

public interface IFacade {
    public String save(DomainEntity domainEntity);
    public String update(DomainEntity domainEntity);
    public String delete(DomainEntity domainEntity);
    public List<DomainEntity> read(DomainEntity domainEntity);
}
