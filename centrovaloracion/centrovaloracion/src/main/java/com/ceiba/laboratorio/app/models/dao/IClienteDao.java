package com.ceiba.laboratorio.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ceiba.laboratorio.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
