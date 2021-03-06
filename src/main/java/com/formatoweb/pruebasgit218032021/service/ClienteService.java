package com.formatoweb.pruebasgit218032021.service;

import com.formatoweb.pruebasgit218032021.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> getClientes();
    Cliente saveClientes(Cliente cliente);
    Cliente clienteById(Long id);
    void deleteCliente(Long id);
}
