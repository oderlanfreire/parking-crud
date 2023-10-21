package com.api.crudestacionamento.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crudestacionamento.DTOs.EstacionamentoDto;
import com.api.crudestacionamento.models.ModeloEstacionamento;
import com.api.crudestacionamento.services.ServicoEstacionamento;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estacionamento")
public class ControllerEstacionamento {

    final ServicoEstacionamento servicoEstacionamento;

    public ControllerEstacionamento(ServicoEstacionamento servicoEstacionamento) {
        this.servicoEstacionamento = servicoEstacionamento;
    }

    @PostMapping
    public ResponseEntity<Object> saveEstacionamento(@RequestBody @Valid EstacionamentoDto estacionamentoDto){
        if (servicoEstacionamento.existsByLicensePlatCar(estacionamentoDto.getLicensePlatCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esta placa de carro já está em uso!");
        }

        if(servicoEstacionamento.existsByParkingSpotNumber(estacionamentoDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Está vaga já está reservada!");
        }

        if(servicoEstacionamento.existsByApartmentAndBlock(estacionamentoDto.getApartment(), estacionamentoDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Já existe uma vaga de estacionamento registrada para esse apartamento e bloco!");
        }



        var modeloEstacionamento = new ModeloEstacionamento();
        BeanUtils.copyProperties(estacionamentoDto, modeloEstacionamento);
        modeloEstacionamento.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoEstacionamento.save(modeloEstacionamento));
    }

    @GetMapping
    public ResponseEntity<List<ModeloEstacionamento>> getAllModeloEStacionamento(){
        return ResponseEntity.status(HttpStatus.OK).body(servicoEstacionamento.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ModeloEstacionamento> modeloEstacionamentoOptional = servicoEstacionamento.findById(id);
        if(!modeloEstacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de estacionamento não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(modeloEstacionamentoOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ModeloEstacionamento> modeloEstacionamentoOptional = servicoEstacionamento.findById(id);
        if (!modeloEstacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de estacionamento não encontrada!");
        }
        servicoEstacionamento.delete(modeloEstacionamentoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga de estacionamento deletada com sucesso!");
    }
}
