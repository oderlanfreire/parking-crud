package com.api.crudestacionamento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crudestacionamento.models.ModeloEstacionamento;


@Repository
public interface RepositorioEstacionamento extends JpaRepository<ModeloEstacionamento, UUID> {
    boolean existsByLicensePlatCar(String licensePlatCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);
}
