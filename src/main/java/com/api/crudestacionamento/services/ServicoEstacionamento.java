package com.api.crudestacionamento.services;

import org.springframework.stereotype.Service;

import com.api.crudestacionamento.repositories.RepositorioEstacionamento;

@Service
public class ServicoEstacionamento {
    
    final RepositorioEstacionamento repositorioEstacionamento;

    public ServicoEstacionamento(RepositorioEstacionamento repositorioEstacionamento) {
        this.repositorioEstacionamento = repositorioEstacionamento;
    }
}
