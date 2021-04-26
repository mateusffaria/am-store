package br.com.les.amstore.service.serviceImpl;

import br.com.les.amstore.domain.Banner;
import br.com.les.amstore.repository.Banners;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements IGenericService<Banner> {
    @Autowired
    Banners banners;

    @Override
    public List<Banner> findAll() {
        return banners.findAll();
    }

    @Override
    public Banner findById(Long id) {
        return banners.findById(id).get();
    }

    @Override
    public Banner saveAndFlush(Banner object) {
        return banners.saveAndFlush(object);
    }
}
