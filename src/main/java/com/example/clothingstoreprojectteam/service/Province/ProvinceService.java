package com.example.clothingstoreprojectteam.service.Province;

import com.example.clothingstoreprojectteam.model.Province;
import com.example.clothingstoreprojectteam.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {

    @Autowired
    private IProvinceRepository iProvinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return iProvinceRepository.findAll();
    }

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return iProvinceRepository.findAll(pageable);
    }

    @Override
    public Province save(Province province) {
        return iProvinceRepository.save(province);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return iProvinceRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iProvinceRepository.deleteById(id);
    }
}
