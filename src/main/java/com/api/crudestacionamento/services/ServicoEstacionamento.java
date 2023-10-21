package com.api.crudestacionamento.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.crudestacionamento.models.ModeloEstacionamento;
import com.api.crudestacionamento.repositories.RepositorioEstacionamento;

import jakarta.transaction.Transactional;

@Service
public class ServicoEstacionamento {
    
    final RepositorioEstacionamento repositorioEstacionamento;

    public ServicoEstacionamento(RepositorioEstacionamento repositorioEstacionamento) {
        this.repositorioEstacionamento = repositorioEstacionamento;
    }

    @Transactional
    public ModeloEstacionamento save(ModeloEstacionamento modeloEstacionamento) {
        return repositorioEstacionamento.save(modeloEstacionamento);
    }

    public boolean existsByLicensePlatCar(String licensePlatCar) {
        return repositorioEstacionamento.existsByLicensePlatCar(licensePlatCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return repositorioEstacionamento.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return repositorioEstacionamento.existsByApartmentAndBlock(apartment, block);
    }

    public List<ModeloEstacionamento> findAll() {
        return repositorioEstacionamento.findAll();
    }

    public Optional<ModeloEstacionamento> findById(UUID id) {
        return repositorioEstacionamento.findById(id);
    }

    @Transactional
    public void delete(ModeloEstacionamento modeloEstacionamento){
        repositorioEstacionamento.delete(modeloEstacionamento);
    }
}
