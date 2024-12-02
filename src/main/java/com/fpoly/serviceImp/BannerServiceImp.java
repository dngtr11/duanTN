package com.fpoly.serviceImp;

import com.fpoly.entity.Banner;
import com.fpoly.repository.BannerRepository;
import com.fpoly.servive.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannerServiceImp implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public Banner save(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public Banner update(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }

    @Override
    public Banner findById(Long id) {
        return bannerRepository.findById(id).get();
    }

    @Override
    public List<Banner> findByPageName(String pageName) {
        return bannerRepository.findByPageName("%"+pageName+"%");
    }

    @Override
    public Page<Banner> search(String param, Pageable pageable) {
        return bannerRepository.search("%"+param+"%", pageable);
    }
}
