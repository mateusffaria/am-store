package br.com.les.amstore.facade;

import br.com.les.amstore.domain.*;
import br.com.les.amstore.repository.*;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class FacadeImpl implements IFacade {
    private Map<String, JpaRepository> daos;

    public FacadeImpl(Games games, Platforms platforms, Genders genders, Languages languages, Publishers publishers) {
        daos = new HashMap<String, JpaRepository>();

        daos.put(Game.class.getName(), games);
        daos.put(Platform.class.getName(), platforms);
        daos.put(Gender.class.getName(), genders);
        daos.put(Language.class.getName(), languages);
        daos.put(Publisher.class.getName(), publishers);
    }

    @Override
    public String save(DomainEntity domainEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        String result = "";
        validateNullString(stringBuilder, domainEntity.validate());

        if(stringBuilder.length() == 0){
            JpaRepository dao = daos.get(domainEntity.getClass().getName());
            dao.save(domainEntity);
        } else {
            result = stringBuilder.toString();
        }


        return result;
    }

    @Override
    public String update(DomainEntity domainEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        String result = "";
        validateNullString(stringBuilder, domainEntity.validate());

        if(null == domainEntity.getId())
            stringBuilder.append("Não é possível atualizar um registro novo.");

        if(stringBuilder.length() == 0){
            JpaRepository dao = daos.get(domainEntity.getClass().getName());
            dao.save(domainEntity);
        } else {
            result = stringBuilder.toString();
        }


        return result;

    }

    @Override
    public String delete(DomainEntity domainEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        String result = "";

        if(null == domainEntity.getId())
            stringBuilder.append("Não é possível excluir um registro sem id.");

        if(stringBuilder.length() == 0){
            JpaRepository dao = daos.get(domainEntity.getClass().getName());
            dao.save(domainEntity);
        } else {
            result = "Falha na operação";
        }


        return result;
    }

    @Override
    public List<DomainEntity> read(DomainEntity domainEntity) {
        JpaRepository dao = daos.get(domainEntity.getClass().getName());

        List<DomainEntity> domainEntities = dao.findAll();

        return domainEntities;
    }

    @Override
    public DomainEntity readOne(DomainEntity domainEntity) {
        JpaRepository dao = daos.get(domainEntity.getClass().getName());

        DomainEntity domainEntity2 = (DomainEntity) dao.findById(domainEntity.getId()).get();

        return domainEntity2;
    }

    void validateNullString(StringBuilder stringBuilder, String message) {
        if(null != message)
            stringBuilder.append(message);
    }
}
