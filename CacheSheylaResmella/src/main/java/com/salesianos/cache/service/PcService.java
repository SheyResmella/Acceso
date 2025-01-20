package com.salesianos.cache.service;

import com.salesianos.cache.model.Pc;
import com.salesianos.cache.repository.PcRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PcService {

    private final PcRepository pcRepository;

    public PcService(PcRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    @Cacheable(value = "pcs", key = "#id")
    public Pc getPcById(Long id) {
        return pcRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "pcs")
    public List<Pc> getAllPcs() {
        return pcRepository.findAll();
    }

    @CacheEvict(value = "pcs", allEntries = true)
    public Pc updatePc(Pc pc) {
        return pcRepository.save(pc);
    }

    @CacheEvict(value = "pcs", allEntries = true)
    public void deletePc(Long id) {
        pcRepository.deleteById(id);
    }

    @CacheEvict(value = "pcs", allEntries = true)
    public void clearCache() {
    	
    }
}