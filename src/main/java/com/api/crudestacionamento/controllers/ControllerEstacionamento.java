package com.api.crudestacionamento.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crudestacionamento.services.ServicoEstacionamento;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estacionamento")
public class ControllerEstacionamento {
    
    final ServicoEstacionamento servicoEstacionamento;

    public ControllerEstacionamento(ServicoEstacionamento servicoEstacionamento) {
        this.servicoEstacionamento = servicoEstacionamento;
    }

}
